package br.dev.as.catalog;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
		System.setProperty("MYSQL_USER", dotenv.get("MYSQL_USER","admin"));
		System.setProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD","adminadmin"));

		SpringApplication.run(CatalogApplication.class, args);
	}

}
