package com.company;

import com.company.model.User;
import com.company.model.UserRole;
import com.company.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Library API",
                description = "library",
                version = "v1"))
@RequiredArgsConstructor
public class LibraryApplication implements CommandLineRunner {
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(User.builder()
                        .userRole(UserRole.ADMIN)
                        .username("leman")
                        . password("123")
                        . build());

     userService.createUser(User.builder()
             .userRole(UserRole.USER)
                        .username("kenan")
                        . password("1234")
                        . build());
}
}
