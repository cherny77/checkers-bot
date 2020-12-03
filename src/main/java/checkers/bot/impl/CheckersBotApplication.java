package checkers.bot.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CheckersBotApplication {

	private static final String SERVER_URL = "http://localhost:8081";
	private static final Logger log = LoggerFactory.getLogger(CheckersBotApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CheckersBotApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
//			Quote quote = restTemplate.getForObject(
//					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//			log.info(quote.toString());
		};
	}



}
