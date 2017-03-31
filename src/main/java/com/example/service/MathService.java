package com.example.service;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MathService {

    public String calculateOperation(String operation, int x, int y) {
        int result;
        String format = null;
        if (null == operation) {
            result = x + y;
            format = StringFormatter.format("%d + %d = %d", x, y, result).getValue();
            return format;
        }

        switch (operation) {
            case "add":
                result = x + y;
                format = StringFormatter.format("%d + %d = %d", x, y, result).getValue();
                break;
            case "subtract":
                result = x - y;
                format = StringFormatter.format("%d - %d = %d", x, y, result).getValue();
                break;
            case "multiply":
                result = x * y;
                format = StringFormatter.format("%d * %d = %d", x, y, result).getValue();
                break;
            case "divide":
                result = x / y;
                format = StringFormatter.format("%d / %d = %d", x, y, result).getValue();
                break;
            default:
                format = "Error! Invalid input operation! Please use add, subtract, multiply, and divide!";
                break;
        }
        return format;
    }

    public String sum(MultiValueMap<String, String> valueMap) {
        String result;

        //work around for a bug in MultiValueMap. If you declare a MultiValueMap<String, Integer>, it can contain value of List<String> rather than List<Integer>
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

        //format the result
        //multiple ns
        if (null != integers && integers.size() > 1) {
            Iterator<Integer> iterator = integers.iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext()) {
                Integer currentInt = iterator.next();
                if (null != currentInt) {
                    sb.append(currentInt + " + ");
                }
            }
            sb.setLength(sb.length() - 2);
            result = sb.append("= " + result).toString();
        }
        //single n, I do not know. Put "4 = 4"? or just "4"?
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
