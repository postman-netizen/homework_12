package org.example.java_pro_homework8;

import org.example.java_pro_homework8.services.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JavaProHomework8Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaProHomework8Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AdminService adminService, PasswordEncoder passwordEncoder) {
        return args -> {
            adminService.addAdmin("111", passwordEncoder.encode("222"), "ADMIN");
        };
    }
}
