package com.exmaple.users.runners;

import com.exmaple.users.models.Role;
import com.exmaple.users.models.enums.UserRole;
import com.exmaple.users.role.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Order(1)
public class RolesInitRunner implements CommandLineRunner {
    final private RoleRepository roleRepository;

    public RolesInitRunner(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        List<Role> roles = new ArrayList<>() {{
            add(new Role(UserRole.USER.id, UserRole.USER.name));
            add(new Role(UserRole.ADMIN.id, UserRole.ADMIN.name));
        }};
        roleRepository.saveAll(roles);
        log.info("Initialized {} roles", roleRepository.count());
    }
}
