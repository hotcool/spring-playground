package com.example.controller;

import com.example.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathService service;

    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "operation", required = false) String operation,
                            @RequestParam("x") int x,
                            @RequestParam("y") int y) {
        return service.calculateOperation(operation, x, y);
    }

    @PostMapping("/sum")
    public String sum(@RequestParam MultiValueMap<String, String> valueMap) {
        return service.sum(valueMap);
    }

}
