package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/response")
public class ResponseController {

    @Autowired
    private ResponseService responseService;
    @GetMapping("/")
    public List<Response> getResponses() { return responseService.getAllResponses();
    }
}
