package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.Test;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}

