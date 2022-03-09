package com.exmaple.users.models.base;

import org.springframework.http.ResponseEntity;

public interface BaseResponse<T> {
    default ResponseEntity<?> toOkResponse(Object obj) {
        return ResponseEntity.ok().body(obj);
    }

    default ResponseEntity<?> toFailedResponse(Object obj) {
        return ResponseEntity.notFound().build();
    }
}
