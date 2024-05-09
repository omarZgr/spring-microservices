package com.application;

import com.application.model.Inventory;
import com.application.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryService01Application {

	public static void main(String[] args) {
		SpringApplication.run(InventoryService01Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository)
	{
		return args -> {
			Inventory inventory1 = new Inventory() ;

			inventory1.setSkuCode("ihpone_13");
			inventory1.setQuantity(4);

			Inventory inventory2 = new Inventory() ;

			inventory2.setSkuCode("S Ultra 24");
			inventory2.setQuantity(0);

			inventoryRepository.save(inventory1)  ;
			inventoryRepository.save(inventory2)  ;


		} ;
	}
}
