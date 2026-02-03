package com.example.nobs;

import org.springframework.http.ResponseEntity;

public interface Query<I, O> {
    O execute(I input);
}
