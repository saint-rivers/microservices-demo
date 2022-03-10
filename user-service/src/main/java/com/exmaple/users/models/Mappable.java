package com.exmaple.users.models;

public interface Mappable<E, R, D> {
    E toEntity(R request);
    D toDto(E entity);
}
