package com.example.calculator.Models;

import com.example.calculator.Collections.Stack;
import lombok.Data;

@Data
public class InfixToPostModel {

    private Stack stack;
    private String output = "";

}
