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

    public List<Response> getAllResponses() {return responseRepository.getResponses();
    }
}
