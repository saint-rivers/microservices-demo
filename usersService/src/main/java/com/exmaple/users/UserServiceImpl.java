package com.exmaple.users;

import com.exmaple.users.models.User;
import com.exmaple.users.models.UserDto;
import com.exmaple.users.models.UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findUserById(String id) {
        Optional<User> user = userRepository.findById(UUID.fromString(id));
        if (user.isEmpty()){
            throw new NullPointerException("User not found");
        }
        return user.get().toDto();
    }

    @Override
    public UserDto registerUser(UserRequest userRequest) {
        return userRepository.save(userRequest.toEntity()).toDto();
    }
}
