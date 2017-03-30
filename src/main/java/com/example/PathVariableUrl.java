package com.example;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableUrl {

    @RequestMapping(value = "/math/volume/{length}/{width}/{height}")
    public String calculateVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height){
        int result = length * width * height;
        return StringFormatter.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, result).getValue();
    }

}
