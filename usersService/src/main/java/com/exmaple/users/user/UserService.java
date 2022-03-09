package com.exmaple.users.user;

import com.exmaple.users.models.UserDto;
import com.exmaple.users.models.UserRequest;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDto findUsersById(String id);

    UserDto registerUser(UserRequest userRequest);

    Page<UserDto> findUsersByPage(int page, int size);

    Page<UserDto> findEnabledUsersByPage(int page, int size);

    UserDto updateUser(String id, UserRequest userRequest);

    void deactivateAccount(String id);
}
