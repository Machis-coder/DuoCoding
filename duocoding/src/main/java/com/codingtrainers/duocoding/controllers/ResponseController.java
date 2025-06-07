package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/response")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @GetMapping("/")
    public List<Response> getAllResponses() {
        return responseService.getAllResponses();
    }

    @GetMapping("/{id}")
    public Response getResponseById(@PathVariable Long id) {
        return responseService.getResponseById(id);
    }

    @PostMapping("/")
    public Response createResponse(@RequestBody Response response) {
        return responseService.createResponse(response);
    }

    @PutMapping("/")
    public Response updateResponse(@RequestBody Response response) {
        return responseService.updateResponse(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResponseById(@PathVariable Long id) {
        String message = responseService.deleteResponseById(id);
        return ResponseEntity.ok(message);
    }
}
