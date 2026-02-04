package com.example.nobs.cqrs;

public interface Command<I, O> {
    O execute(I input);
}
