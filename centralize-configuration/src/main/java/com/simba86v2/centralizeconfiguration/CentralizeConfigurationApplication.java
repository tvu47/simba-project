package com.simba86v2.centralizeconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CentralizeConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralizeConfigurationApplication.class, args);
	}

}
