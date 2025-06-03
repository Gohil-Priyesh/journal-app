package com.priyesh.myFirstProject;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyFirstProjectApplication {

	public static void main(String[] args) {

		// Load environment variables from .env
		Dotenv dotenv = Dotenv.load();

		// Set MongoDB URI and DB name from .env into system properties
		System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGODB_URI"));
		System.setProperty("spring.data.mongodb.database", dotenv.get("MONGODB_DBNAME"));
		System.setProperty("server.port", dotenv.get("SERVER_PORT"));


		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

}
