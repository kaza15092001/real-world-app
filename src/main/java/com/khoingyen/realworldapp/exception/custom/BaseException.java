package com.khoingyen.realworldapp.exception.custom;

import com.khoingyen.realworldapp.model.CustomError;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public abstract class BaseException extends Exception{
    private Map<String, CustomError> errors;

    public BaseException(CustomError error) {
        this.errors = new HashMap<>();
        this.errors.put("errors", error);
    }
}
