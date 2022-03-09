package com.exmaple.users.user;

import com.exmaple.users.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Page<User> findAllByIsEnabled(Boolean isEnabled, Pageable pageable);
}
