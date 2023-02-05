package com.example.calculator.Unit.Controllers;

import com.example.calculator.Controllers.CalculatorControllerMVC;
import com.example.calculator.Services.CalculatorString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CalculatorControllerMVC.class)
public class CalculatorControllerMVCTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CalculatorString calculatorString;

    @DisplayName("Should get and return 200 status")
    @Test
    void shouldGetAndReturn200Status() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/")
                .contentType(MediaType.TEXT_HTML) )
                .andExpect(status().isOk() )
                .andExpect(view().name("calculator.html") );
    }

    @DisplayName("Should post and return 200 status")
    @Test
    void shouldPostAndReturn200Status() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .post("/response")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("expression", "") )
                .andExpect(status().isOk() )
                .andExpect(view().name("response.html") )
                .andExpect(model().attribute("result", is("0.0") ) );
    }


    @DisplayName("Should post and return calculation result")
    @Test
    void shouldPostAndReturnCalculationResult() throws Exception {

        when(calculatorString.calculate("2_2", 0.0) ).thenReturn(4.0);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/response")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("expression", "2_2") )
                .andExpect(model().attribute("result", is("4.0") ) );
    }
}