package com.sfbest.mico.order;

import java.security.Principal;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class OrderApplication {
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(OrderApplication.class);

	@GetMapping("/query/{osn}")
	public String queryOrder(@PathVariable("osn") String osn) {
		return osn;
	}

	@PostMapping("/create/{user}/{productSn}")
	public String createOrder(@PathVariable("user") String userId, @PathVariable("productSn") String productSn) {
		return "ok";
	}

	@GetMapping("/getPrinciple")
	public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal,
			Authentication authentication) {
		logger.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
		logger.info(oAuth2Authentication.toString());
		logger.info("principal.toString() " + principal.toString());
		logger.info("principal.getName() " + principal.getName());
		logger.info("authentication: " + authentication.getAuthorities().toString());

		return oAuth2Authentication;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
