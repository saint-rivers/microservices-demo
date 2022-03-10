package com.exmaple.users.user;

import com.exmaple.users.models.UserDto;
import com.exmaple.users.models.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRequest userRequest) {
        UserDto userDto = userService.registerUser(userRequest);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> viewUserDetails(@PathVariable("id") String userId) {
        UserDto userDto = userService.findUsersById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> listUsers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "false", required = false) Boolean all
    ) {
        if (all) {
            return ResponseEntity.ok(userService.findUsersByPage(page, size));
        }
        return ResponseEntity.ok(userService.findEnabledUsersByPage(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserDetails(@PathVariable("id") String userId, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") String userId){
        userService.deactivateAccount(userId);
        return ResponseEntity.ok().build();
    }
}
