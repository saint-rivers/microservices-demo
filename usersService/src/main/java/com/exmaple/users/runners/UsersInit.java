package com.exmaple.users.runners;

import com.exmaple.users.models.Role;
import com.exmaple.users.models.User;
import com.exmaple.users.models.enums.UserRole;
import com.exmaple.users.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Component
@Slf4j
@Order(2)
public class UsersInit implements CommandLineRunner {

    private final UserRepository userRepository;

    public UsersInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        User admin = new User(
                UUID.randomUUID(),
                "Dayan",
                "Eam",
                "rocaviusii@gmail.com",
                "asd",
                false,
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>() {{
                    add(new Role(1, UserRole.ADMIN.name));
                }});
        userRepository.save(admin);
//        log.info("Initiated admin: {}", userRepository.findById(admin.getId()).orElseThrow());
    }
}
