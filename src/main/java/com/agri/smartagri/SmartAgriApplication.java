package com.agri.smartagri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Smart Agriculture Management System.
 * Serves both REST APIs under /api/** and the frontend Single-Page Application.
 */
@SpringBootApplication
public class SmartAgriApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartAgriApplication.class, args);
        System.out.println("==================================================================");
        System.out.println("🌱 Smart Agriculture Management System is running!");
        System.out.println("🌐 Open your browser and navigate to: http://localhost:8080");
        System.out.println("==================================================================");
    }
}
