package com.example.service;

import com.example.model.ClosedLineSegments;
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
        assertEquals("2 + 5 + 8 = 15", service.sum(valueMap));
    }

    @Test
    public void testArea(){
        ClosedLineSegments closedLineSegments = new ClosedLineSegments("circle", 8,0,0);

        assertEquals("Error! Incorrect input parameters. This endpoint only takes in type(circle, rectangle), radius, width and height!", service.getAreaResult(null));
        assertEquals("Area of a circle with a radius of 8 is 201.06193", service.getAreaResult(closedLineSegments));

        closedLineSegments.setRadius(0);
        closedLineSegments.setWidth(2);
        assertEquals("Invalid", service.getAreaResult(closedLineSegments));

        closedLineSegments.setType("square");
        assertEquals("Error! Incorrect input type. This endpoint only accepts circle / rectangle.", service.getAreaResult(closedLineSegments));

        closedLineSegments.setType("rectangle");
        closedLineSegments.setRadius(2);
        closedLineSegments.setWidth(0);
        assertEquals("Invalid", service.getAreaResult(closedLineSegments));

        closedLineSegments.setWidth(5);
        closedLineSegments.setHeight(7);
        assertEquals("Area of a 5x7 rectangle is 35", service.getAreaResult(closedLineSegments));
    }
}
