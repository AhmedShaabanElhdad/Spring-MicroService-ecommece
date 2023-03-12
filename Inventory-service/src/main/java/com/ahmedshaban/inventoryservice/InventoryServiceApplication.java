package com.ahmedshaban.inventoryservice;

import com.ahmedshaban.inventoryservice.model.Inventory;
import com.ahmedshaban.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	// use Luiqiebase - flyway for database migration
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSku("346");
			inventory.setQuality(5);

			Inventory inventory1 = new Inventory();
			inventory1.setSku("345");
			inventory1.setQuality(5);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
