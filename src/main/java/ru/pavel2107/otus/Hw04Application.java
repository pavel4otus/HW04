package ru.pavel2107.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.pavel2107.otus.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties( ApplicationProperties.class)
public class Hw04Application {

    public static void main(String[] args) {
       SpringApplication.run( Hw04Application.class, args);
    }

}
