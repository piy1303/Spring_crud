package CRUD_PROJECT.SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {

		/*					WHAT TO DO -->

			1. end point listen (/api/students POST)

     		2. business logic

    		3.intract with DB to store

    		4.response back to client (postman)

		 */

		SpringApplication.run(SpringBootDemoApplication.class, args);

		System.out.println("Hello World");


	}

}
