package com.panel.wg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class WgPanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(WgPanelApplication.class, args);
	}

}
