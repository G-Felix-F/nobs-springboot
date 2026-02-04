package com.example.nobs.cqrs;

public interface Query<I, O> {
    O execute(I input);
}
