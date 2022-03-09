package com.exmaple.users.models.base;

import org.modelmapper.ModelMapper;

public abstract class BaseRequest<T> {
    protected ModelMapper modelMapper = new ModelMapper();

    public abstract T toEntity();
}
