package com.example.norza.repository;

import com.example.norza.domain.Festival;
import com.example.norza.domain.Performance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.awt.print.Pageable;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Id> {
    Performance findById(long id);

    Page<Performance> findAllByOrderByEndDateAsc(PageRequest pageRequest);


}
