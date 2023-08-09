package com.example.norza.repository;

import com.example.norza.domain.Festival;
import com.example.norza.domain.Performance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public interface FestivalRepository extends JpaRepository<Festival, Id> {
    Festival findById(long id);
    Page<Festival> findAll(Pageable pageable);

    List<Festival> findByNameContaining(String Name);

    List<Festival> findByContentContaining(String content);

    List<Festival> findByLocationContaining(String location);
}
