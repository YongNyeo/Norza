package com.example.norza.service;

import com.example.norza.domain.Performance;
import com.example.norza.domain.PerformanceComment;
import com.example.norza.repository.PerformanceCommentRespository;
import com.example.norza.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceCommentService {
    private final PerformanceCommentRespository commentRespository;
    private final PerformanceRepository repository;
    public Long save(PerformanceComment comment,long id) {
        Performance performance = repository.findById(id);
        comment.setPerformance(performance);
        commentRespository.save(comment);
        return comment.getId();
    }

    public List<PerformanceComment>findAllById(long performance_id) { //게시글의 id
        Performance performance = repository.findById(performance_id);
        List<PerformanceComment> commentList = commentRespository.findAllByPerformance(performance);
        return commentList;
    }

    public PerformanceComment findCommentById(Long id) {
        return commentRespository.findById(id);
    }


    public Long delete(Long commentId) {
        return commentRespository.deleteById(commentId);
    }
}
