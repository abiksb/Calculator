package com.example.calculator.Unit.Controllers;

import org.junit.jupiter.api.*;
import com.example.calculator.Controllers.CalculatorControllerREST;
import com.example.calculator.Services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CalculatorControllerREST.class)
public class CalculatorControllerRESTTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CalculatorString calculatorString;

    static AtomicInteger counterID = new AtomicInteger();

    @Test
    void shouldReturn200Status() throws Exception {

    mockMvc.getDispatcherServlet();
        mockMvc.perform(MockMvcRequestBuilders
                .get("/calculator/calculateREST")
                .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue() ) )
                .andExpect(jsonPath("$.id", is(counterID.incrementAndGet() ) ) )
                .andExpect(jsonPath("$.inputExpression", is("0") ) )
                .andExpect(jsonPath("$.result", is(0.0) ) );
    }

    @DisplayName("Should return calculation result with one argument passed")
    @Test
    void shouldReturnCalculationResultWithOneArgument() throws Exception {

        when(calculatorString.calculate("2_2", 0.0)).thenReturn(4.0);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/calculator/calculateREST?expression=2_2")
                .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk() )
                .andExpect(jsonPath("$", notNullValue() ) )
                .andExpect(jsonPath("$.id", is(counterID.incrementAndGet() ) ) )
                .andExpect(jsonPath("$.inputExpression", is("2_2") ) )
                .andExpect(jsonPath("$.result", is(4.0) ) );
    }

    @DisplayName("Should return calculation result with two arguments passed")
    @Test
    void shouldReturnCalculationResultWithTwoArguments() throws Exception {

        when(calculatorString.calculate("2*r+2", 5.0) ).thenReturn(12.0);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/calculator/calculateREST?expression=2*r+2&previousResult=5.0")
                .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk() )
                .andExpect(jsonPath("$", notNullValue() ) )
                .andExpect(jsonPath("$.id", is(counterID.incrementAndGet() ) ) )
                .andExpect(jsonPath("$.inputExpression", is("2*r+2") ) )
                .andExpect(jsonPath("$.result", is(12.0) ) );
    }
}
