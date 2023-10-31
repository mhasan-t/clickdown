package com.muhib.clickdown.services;

import com.muhib.clickdown.models.User;
import com.muhib.clickdown.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Helper {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User noon = new User("Muhib Al Hasan", "muhib@email.com", "1234");
//            userRepository.save(noon);
        };
    }
}
