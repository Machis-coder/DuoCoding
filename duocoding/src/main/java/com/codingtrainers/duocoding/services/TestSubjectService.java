package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.entities.TestSubject;
import com.codingtrainers.duocoding.repositories.TestSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestSubjectService {
    @Autowired
    TestSubjectRepository testSubjectRepository;

    public List<TestSubject> getALlTestSubject(){return testSubjectRepository.findAll();}

    public TestSubject getTestSubjectById(Long id){
        return testSubjectRepository.findById(id).orElseThrow(() -> new RuntimeException(" TestSubject Not Found"));
    }

    public  TestSubject createTestSubject(TestSubject testSubject){ return  testSubjectRepository.save(testSubject);}

    public TestSubject updateTestSubject(TestSubject testSubject){ return testSubjectRepository.save(testSubject);}

    public String deleteTestSubject(Long id){
        if(!testSubjectRepository.existsById(id)){
            throw new RuntimeException("TestSubject not found");
        }
        testSubjectRepository.deleteById(id);
        return "TestSubject removed successfully";
    }


}
