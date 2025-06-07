package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    public Response getResponseById(Long id) {
        return responseRepository.findById(id).orElseThrow(() -> new RuntimeException("Response not found"));
    }

    public Response createResponse(Response response) {
        return responseRepository.save(response);
    }

    public Response updateResponse(Response response) {
        if (!responseRepository.existsById(response.getId())) {
            throw new RuntimeException("Response not found");
        }
        return responseRepository.save(response);
    }

    public String deleteResponseById(Long id) {
        if (!responseRepository.existsById(id)) {
            throw new RuntimeException("Response not found");
        }
        responseRepository.deleteById(id);
        return "Response deleted successfully";
    }
}
