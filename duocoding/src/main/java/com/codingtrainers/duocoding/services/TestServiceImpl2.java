package com.codingtrainers.DuoCoding.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty( name = "service", havingValue = "2")
public class TestServiceImpl2 implements TestService {

    @Override
    public String sayHello() {
        return this.getClass().getName();
    }
}

