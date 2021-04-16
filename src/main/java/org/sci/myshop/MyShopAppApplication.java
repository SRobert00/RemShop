package org.sci.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.sci.myshop")
public class MyShopAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyShopAppApplication.class, args);
	}

}
