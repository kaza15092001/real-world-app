package com.khoingyen.realworldapp.exception.custom;

import com.khoingyen.realworldapp.model.CustomError;

public class CustomNotFoundException extends BaseException{
    public CustomNotFoundException(CustomError error) {
        super(error);
    }
}
