package com.example.calculator;

import lombok.Data;

@Data
public class InfixToPostModel {

    private Stack stack;
    private String output = "";

}
