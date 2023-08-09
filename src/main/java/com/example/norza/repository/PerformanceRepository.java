package com.example.norza.repository;


import com.example.norza.domain.Performance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;


public interface PerformanceRepository extends JpaRepository<Performance, Id> {
    Performance findById(long id);
    Page<Performance> findAll(Pageable pageable);

    List<Performance> findByNameContaining(String content);

    List<Performance> findByContentContaining(String content);

    List<Performance> findByLocationContaining(String content);
}
