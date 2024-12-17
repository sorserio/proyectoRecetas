package com.sabropedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "sabropedia.demo")
@EnableJpaRepositories("com.sabropedia.repositories")
@EntityScan("com.sabropedia.models")
public class SabropediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SabropediaApplication.class, args);
    }

}
