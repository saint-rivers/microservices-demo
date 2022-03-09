package com.exmaple.users.models.base;

import org.modelmapper.ModelMapper;

public abstract class BaseEntity<T> {
    protected ModelMapper modelMapper = new ModelMapper();

    public abstract T toDto();
}
