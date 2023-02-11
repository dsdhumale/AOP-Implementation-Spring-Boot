package com.bridgelabz.AddressBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AddressBookApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to address book system");
		SpringApplication.run(AddressBookApplication.class, args);
		log.info("Logging started.............");
		System.out.println("Started......");
	}
}
