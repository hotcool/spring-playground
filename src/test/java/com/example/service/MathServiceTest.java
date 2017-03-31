package com.example.service;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;

public class MathServiceTest {

    private MathService service = new MathService();

    @Test
    public void testCal() {
        assertEquals("30 + 5 = 35", service.calculateOperation(null, 30, 5));
        assertEquals("4 + 6 = 10", service.calculateOperation("add", 4, 6));
        assertEquals("4 - 6 = -2", service.calculateOperation("subtract", 4, 6));
        assertEquals("4 * 6 = 24", service.calculateOperation("multiply", 4, 6));
        assertEquals("30 / 5 = 6", service.calculateOperation("divide", 30, 5));
        assertEquals("Error! Invalid input operation! Please use add, subtract, multiply, and divide!", service.calculateOperation("", 5, 30));
        assertEquals("Error! Invalid input operation! Please use add, subtract, multiply, and divide!", service.calculateOperation("mod", 5, 30));
    }

    @Test
    public void testSum() {
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("n", "");
        valueMap.add("n", "2");
        valueMap.add("n", "5");
        valueMap.add("n", "8");
        valueMap.add("n", "a");
        valueMap.add("p", "5");
        valueMap.add("q", "6");

        assertEquals("Error! Invalid input integer!", service.sum(null));
        assertEquals("15", service.sum(valueMap));
    }
}
