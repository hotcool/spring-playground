package com.example.controller;

import com.example.model.ClosedLineSegments;
import com.example.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class AreaController {

    @Autowired
    private MathService service;

    @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String calculateArea(ClosedLineSegments closedLineSegments) {
        return service.getAreaResult(closedLineSegments);
    }

}
