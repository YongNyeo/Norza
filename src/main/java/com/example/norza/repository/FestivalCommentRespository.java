package com.example.norza.repository;

import com.example.norza.domain.Festival;
import com.example.norza.domain.FestivalComment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface FestivalCommentRespository extends JpaRepository<FestivalComment, Id> {
    List<FestivalComment> findAllByFestival(Festival festival);
    FestivalComment findById(Long id);

    Long deleteById(Long commentId);
}
