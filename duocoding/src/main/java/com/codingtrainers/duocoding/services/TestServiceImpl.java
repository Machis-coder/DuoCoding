package com.codingtrainers.duocoding.services;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty( name = "service", havingValue = "1", matchIfMissing = true)
public class TestServiceImpl implements TestService {

    @Override
    public String sayHello() {
        return this.getClass().getName();
    }
}
