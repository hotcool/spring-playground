package com.example.service;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MathService {

    public String calculateOperation(String operation, int x, int y) {
        String result;
        if (null == operation) {
            return x + y + "";
        }

        switch (operation) {
            case "add":
                result = x + y + "";
                break;
            case "subtract":
                result = x - y + "";
                break;
            case "multiply":
                result = x * y + "";
                break;
            case "divide":
                result = x / y + "";
                break;
            default:
                result = "Error! Invalid input operation! Please use add, subtract, multiply, and divide!";
                break;
        }
        return result;
    }

    public String sum(MultiValueMap<String, String> valueMap) {
        String result;

        List<String> values = null;
        List<String> formattedValues = null;
        List<Integer> integers = null;
        if (null != valueMap)
            values = valueMap.get("n");
        if (null != values)
            formattedValues = values.stream().filter(s -> stringToInt(s)).collect(Collectors.toList());
        if (null != formattedValues) {
            integers = formattedValues.stream().map(Integer::parseInt).collect(Collectors.toList());
        }
        if (null != integers && integers.size() > 0) {
            result = StringFormatter.format("%d", integers.stream().mapToInt(Integer::intValue).sum()).getValue();
        } else {
            result = "Error! Invalid input integer!";
        }
        return result;
    }

    private boolean stringToInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
