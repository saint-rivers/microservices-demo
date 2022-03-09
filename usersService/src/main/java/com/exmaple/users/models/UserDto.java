package com.exmaple.users.models;

import com.exmaple.users.models.base.BaseResponse;
import lombok.Data;

@Data
public class UserDto  {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
