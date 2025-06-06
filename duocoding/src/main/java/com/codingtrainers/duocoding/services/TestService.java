package com.codingtrainers.duocoding.services;//package com.codingtrainers.duocoding.services;
//
//public interface TestService {
//
//    String sayHello();
//}
import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> getAll() {
        return testRepository.findAll();
    }

    public Test getById(int id) {
        return testRepository.findById(id);
    }
}