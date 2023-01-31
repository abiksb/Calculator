package com.example.calculator.Controllers;

import com.example.calculator.Services.CalculatorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

@Controller
public class CalculatorControllerMVC {

    @Autowired
    private CalculatorString calculatorString;

    @GetMapping(value = {"", "/", "/calculator"} )
    public String getCalculator(){
        return "calculator.html";
    }


    @PostMapping("/response")
    public String calculate(@RequestParam("expression") String expression, Model model){

    double result = calculatorString.calculate(expression, 0);

    model.addAttribute("result", String.valueOf(result) );

    return "response.html";
    }


}
