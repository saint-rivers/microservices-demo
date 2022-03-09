package com.exmaple.users.user;

import com.exmaple.users.models.UserDto;
import com.exmaple.users.models.UserRequest;

public interface UserService {

    UserDto findUserById(String id);

    UserDto registerUser(UserRequest userRequest);
}
