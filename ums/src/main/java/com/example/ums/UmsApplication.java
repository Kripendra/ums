package com.example.ums;

import com.example.ums.Repository.UserRepository;
import com.example.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UmsApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args)   {
		SpringApplication.run(UmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		insertRecords(userRepository);
	}

	public static void insertRecords(UserRepository userRepository) {

		User user1 = new User();
		user1.setFirstName("Ram");
		user1.setLastName("Thapa");
		user1.setUsername("user1");
		user1.setEmail("user1@gmail.com");

		User user2= new User();
		user2.setFirstName("Shyam");
		user2.setLastName("Thapa");
		user2.setUsername("user2");
		user2.setEmail("user2@gmail.com");

		User user3 = new User();
		user3.setFirstName("Hari");
		user3.setLastName("Thapa");
		user3.setUsername("user3");
		user3.setEmail("user3@gmail.com");

		User user4 = new User();
		user4.setFirstName("Ram");
		user4.setLastName("Thapa");
		user4.setUsername("user4");
		user4.setEmail("user4@gmail.com");

		User user5 = new User();
		user5.setFirstName("Sita");
		user5.setLastName("Thapa");
		user5.setUsername("user5");
		user5.setEmail("user5@gmail.com");

		userRepository.saveAll(List.of(user1, user2, user3, user4, user5));

	}

}
