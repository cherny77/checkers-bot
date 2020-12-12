package checkers.bot.impl;

import checkers.bot.ai.CheckersBotAi;
import checkers.bot.ai.DifficultHeuristic;
import checkers.bot.ai.RandomHeuristic;
import checkers.bot.ai.SimpleHeuristic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class CheckersBotApplication {

    @Value("${server_url}")
    private String serverUrl;
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
            System.out.println(serverUrl);


            CheckersBot checkersBot1 = new CheckersBot("Name1", new CheckersBotAi(new SimpleHeuristic()), serverUrl);
            CheckersBot checkersBot2 = new CheckersBot("Name2", new CheckersBotAi(new DifficultHeuristic()), serverUrl);
            CompletableFuture.runAsync(() -> checkersBot1.play(restTemplate));
            CompletableFuture.runAsync(() -> checkersBot2.play(restTemplate));

        };
    }


}
