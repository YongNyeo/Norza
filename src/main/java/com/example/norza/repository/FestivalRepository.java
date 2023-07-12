package com.example.norza.repository;

import com.example.norza.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.ArrayList;

public interface FestivalRepository extends JpaRepository<Festival, Id> {
    Festival findById(long id);
}
