package com.example.controller;

import com.sun.javafx.binding.StringFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PiController {

    @GetMapping("/math/pi")
    public String getPi() {
        return StringFormatter.format("%1$, .15f", Math.PI).getValue().trim();
    }
}
