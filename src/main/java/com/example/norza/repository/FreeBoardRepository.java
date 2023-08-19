package com.example.norza.repository;

import com.example.norza.domain.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface FreeBoardRepository extends JpaRepository<FreeBoard,Id> {

    FreeBoard findById(long id);

    void deleteById(long id);
}
