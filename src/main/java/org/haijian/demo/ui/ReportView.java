package org.haijian.demo.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.haijian.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.event.UIEvents.PollEvent;
import com.vaadin.event.UIEvents.PollListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "report")
public class ReportView extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 308174982835641863L;

	private Chart userChart;
	private Chart productChart;
    private HorizontalLayout chartsLayout;
    
    @Autowired
    private PurchaseService purchaseService;
    
	@Override
	public void enter(ViewChangeEvent event) {
		UI.getCurrent().setPollInterval(500);
    	setSizeFull();
        initCharts();
        addComponents(chartsLayout);
        UI.getCurrent().addPollListener(new PollListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -3668910650160635190L;

			@Override
			public void poll(PollEvent event) {
				updateChartData(productChart, getDataSeriesForProduct(), "Product");
				updateChartData(userChart, getDataSeriesForUser(), "User");
			}
		});
	}

	
	private void initCharts() {
        chartsLayout = new HorizontalLayout();
        chartsLayout.setSizeFull();
        productChart = createPieChart();
        chartsLayout.addComponent(productChart);
        userChart = createColumnChart();
        chartsLayout.addComponent(userChart);
    }

    private Chart createPieChart() {
        Chart chart = new Chart(ChartType.PIE);
        chart.setSizeFull();
        Configuration conf = chart.getConfiguration();
        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setTooltip(new Tooltip());
        plotOptions.getTooltip().setEnabled(false);
        plotOptions.setAnimation(false);
        Labels labels = new Labels();
        labels.setEnabled(true);
        labels.setFormatter("''+ this.point.name +': '+ this.percentage.toFixed(2) +' %'");
        plotOptions.setDataLabels(labels);
        conf.setPlotOptions(plotOptions);
        return chart;
    }

    private Chart createColumnChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        chart.setSizeFull();
        Configuration conf = chart.getConfiguration();
        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        conf.setPlotOptions(plotOptions);
        Legend legend = new Legend();
        legend.setEnabled(false);
        conf.setLegend(legend);

        return chart;
    }

    private void updateChartData(Chart chart, DataSeries series, String title) {
        Configuration conf = chart.getConfiguration();
        XAxis xAxis = conf.getxAxis();
        String oldTitle = conf.getTitle().getText();
        String newTitle = title;
        conf.setTitle(newTitle);
        DataSeries oldSeries = null;
        if (!conf.getSeries().isEmpty()) {
            oldSeries = (DataSeries) conf.getSeries().get(0);
        }
        Collection<String> categories = new ArrayList<String>();
        series.getData().forEach(item->categories.add(item.getName()));
        if (oldSeries == null
                || !series.toString().equals(oldSeries.toString())
                || !newTitle.equals(oldTitle)) {
            conf.setSeries(series);
            xAxis.setCategories(categories.toArray(new String[] {}));
            chart.drawChart();
        }
    }
    
    private DataSeries getDataSeriesForProduct(){
    	DataSeries series = new DataSeries();
    	series.setName("Sales");
    	purchaseService.getAllPurchasesGroupByProduct().forEach((p, d)->series.add(new DataSeriesItem(p.getName(), d)));
    	return series;
    }
    
    private DataSeries getDataSeriesForUser(){
    	DataSeries series = new DataSeries();
    	series.setName("Spending");
    	purchaseService.getAllPurchasesGroupByUser().forEach((u, d)->series.add(new DataSeriesItem(u.getName(), d)));
    	return series;
    }
    
}
