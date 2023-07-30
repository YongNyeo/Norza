package com.example.norza.repository;


import com.example.norza.domain.Performance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;


public interface PerformanceRepository extends JpaRepository<Performance, Id> {
    Performance findById(long id);
    Page<Performance> findAll(Pageable pageable);

}
