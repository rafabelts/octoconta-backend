package org.octoconta.octobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OctoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OctoBackendApplication.class, args);
    }

    @GetMapping("/api")
    public String index() {
        return "Octoconta API";
    }
}
