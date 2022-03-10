package com.exmaple.users.user;

import com.exmaple.users.client.MailClient;
import com.exmaple.users.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, Mappable<User, UserRequest, UserDto> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailClient mailClient;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, MailClient mailClient) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.mailClient = mailClient;
    }

    @Override
    public UserDto findUsersById(String id) {
        User user = userRepository.findById(UUID.fromString(id)).orElseThrow();
        return toDto(user);
    }

    @Override
    public UserDto registerUser(UserRequest userRequest) {
        User user = userRepository.save(toEntity(userRequest));
        mailClient.sendMail(new ConfirmRequest("rocaviusii@gmail.com", "123"));
        return toDto(user);
    }

    @Override
    public Page<UserDto> findUsersByPage(int page, int size) {
        return userRepository
                .findAll(PageRequest.of(page, size))
                .map(this::toDto);
    }

    @Override
    public Page<UserDto> findEnabledUsersByPage(int page, int size) {
        return userRepository
                .findAllByIsEnabled(true, PageRequest.of(page, size))
                .map(this::toDto);
    }

    @Override
    public UserDto updateUser(String id, UserRequest userRequest) {
        User originalData = userRepository.findById(UUID.fromString(id)).orElseThrow();
        User updatedUser = toEntity(userRequest);
        updatedUser.setId(originalData.getId());
        updatedUser.setDateCreated(originalData.getDateCreated());

        return toDto(userRepository.save(updatedUser));
    }

    @Override
    public void deactivateAccount(String id) {
        User user = userRepository.findById(UUID.fromString(id)).orElseThrow();
        user.setIsEnabled(false);
        userRepository.save(user);
    }

    // ================ mappings  ================

    @Override
    public User toEntity(UserRequest request) {
        return modelMapper.map(request, User.class);
    }

    @Override
    public UserDto toDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }
}
