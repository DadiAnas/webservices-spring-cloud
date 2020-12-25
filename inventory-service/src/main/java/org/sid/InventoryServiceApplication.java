package org.sid;

import org.sid.dao.ProductRepository;
import org.sid.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            Stream.of("product A", "product B", "product C", "product D").forEach(productName -> {
                productRepository.save(new Product(null,productName+" Designation",Math.random()*120));
            });
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
