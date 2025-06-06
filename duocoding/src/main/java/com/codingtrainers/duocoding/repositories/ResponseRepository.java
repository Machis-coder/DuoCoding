package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.QuestionType;
import com.codingtrainers.duocoding.entities.Response;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseRepository {
    private List<Response> list;
    private QuestionType[] types = QuestionType.values();

    @PostConstruct
    public void init (){
        list = new ArrayList<Response>();
        for (int i = 0; i < 50; i++) {
            Question question = new Question(
                    i,
                    types[i % types.length],
                    "Esta es la pregunta " + i,
                    "La respuesta es " + i);
            Response response = new Response(
                    i,
                    "desccripcion "+i,
                    i,
                    question);
            list.add(response);
        }
    }

    public List<Response> getResponses() {return list;}
}
