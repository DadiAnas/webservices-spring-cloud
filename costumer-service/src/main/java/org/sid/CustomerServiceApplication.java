package org.sid;

import org.sid.dao.CustomerRepository;
import org.sid.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            Stream.of("customer A", "customer B", "customer C", "customer D").forEach(customerName -> {
                customerRepository.save(new Customer(null, customerName));
            });
            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
