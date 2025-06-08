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

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new RuntimeException("Test not found"));
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    public String deleteTestById(Long id) {
        if (!testRepository.existsById(id)) {
            throw new RuntimeException("Test not found");
        }
        testRepository.deleteById(id);
        return "Test eliminado con éxito";
    }
}
