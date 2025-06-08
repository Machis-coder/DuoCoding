package com.codingtrainers.duocoding.services;



import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.repositories.TestQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQuestionService {
    @Autowired
     private TestQuestionRepository testQuestionRepository;

   public  List<TestQuestion> getALlTestQuestions(){return testQuestionRepository.findAll();}

   public TestQuestion getTestQuestionById(Long id){
       return testQuestionRepository.findById(id).orElseThrow(() -> new RuntimeException(" TestQuestion Not Found"));
   }

   public  TestQuestion createTestQuestion(TestQuestion testQuestion){ return  testQuestionRepository.save(testQuestion);}

   public TestQuestion updateTestQuestion(TestQuestion testQuestion){ return testQuestionRepository.save(testQuestion);}

   public String deleteTestQuestion(Long id){
       if(!testQuestionRepository.existsById(id)){
           throw new RuntimeException("TestQuestion not found");
       }
         testQuestionRepository.deleteById(id);
         return "TestQuestion removed successfully";
   }
}
