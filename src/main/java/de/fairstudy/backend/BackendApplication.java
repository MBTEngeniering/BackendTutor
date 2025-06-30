package de.fairstudy.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Startup time: " + formatter.format(new Date(System.currentTimeMillis())));
//        System.out.println("Spring Boot version: " + SpringApplication.class.getPackage().getImplementationVersion());
    }
}
