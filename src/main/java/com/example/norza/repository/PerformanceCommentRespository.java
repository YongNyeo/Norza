package com.example.norza.repository;

import com.example.norza.domain.Performance;
import com.example.norza.domain.PerformanceComment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface PerformanceCommentRespository extends JpaRepository<PerformanceComment, Id> {
    List<PerformanceComment> findAllByPerformance(Performance performance);
    PerformanceComment findById(Long id);

    Long deleteById(Long commentId);
}
