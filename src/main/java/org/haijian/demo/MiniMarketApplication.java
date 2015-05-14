package org.haijian.demo;

import org.haijian.demo.data.Product;
import org.haijian.demo.data.Purchase;
import org.haijian.demo.data.User;
import org.haijian.demo.repository.ProductRepository;
import org.haijian.demo.repository.PurchaseRepository;
import org.haijian.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MiniMarketApplication {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private PurchaseRepository purchaseRepo;
	
    public static void main(String[] args) {
        SpringApplication.run(MiniMarketApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner initializeData(){
    	return args->{
    		userRepo.deleteAll();
    		User user1 = new User("haijian", "Haijian Wang");
			userRepo.save(user1);
    		User user2 = new User("sami", "Sami Viitanen");
			userRepo.save(user2);
    		User user3 = new User("risto", "Risto Yrjänä");
			userRepo.save(user3);
    		System.out.println("\nUsers");
    		System.out.println("--------------------------------------");
    		userRepo.findAll().forEach(System.out::println);
    		System.out.println(userRepo.findOneByUserId("haijian"));
    		
    		productRepo.deleteAll();
    		Product product1 = new Product("Apple", 1, 10);
			productRepo.save(product1);
    		Product product2 = new Product("Cola", 2, 20);
			productRepo.save(product2);
    		System.out.println("\nProducts");
    		System.out.println("--------------------------------------");
    		productRepo.findAll().forEach(System.out::println);
    		System.out.println(productRepo.findOneByName("Apple"));
    		System.out.println("");
    		
    		purchaseRepo.deleteAll();
    		purchaseRepo.save(new Purchase(user1, product1, 3));
    		purchaseRepo.save(new Purchase(user2, product1, 2));
    		purchaseRepo.save(new Purchase(user3, product1, 1));
    		purchaseRepo.save(new Purchase(user1, product2, 2));
    		System.out.println("\nPurchases");
    		System.out.println("--------------------------------------");
    		purchaseRepo.findAll().forEach(System.out::println);
    	};
    }
}
