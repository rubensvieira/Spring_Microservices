package br.com.qcline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QcServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QcServerApplication.class, args);
	}

}
