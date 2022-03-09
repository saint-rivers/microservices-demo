package com.exmaple.users;

import com.exmaple.users.models.UserDto;
import com.exmaple.users.models.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = userService.registerUser(userRequest);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> viewUserDetails(@PathVariable String userId) {
        UserDto userDto = userService.findUserById(userId);
        return ResponseEntity.ok(userDto);
    }
}
