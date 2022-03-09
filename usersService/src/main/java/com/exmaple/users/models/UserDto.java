package com.exmaple.users.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto  {
    private String id;
    private String firstname;
    private String lastname;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime lastUpdated;
}
