package com.example.norza.service;

import com.example.norza.domain.Festival;
import com.example.norza.domain.FestivalComment;
import com.example.norza.repository.FestivalCommentRespository;
import com.example.norza.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalCommentService {
    private final FestivalCommentRespository commentRespository;
    private final FestivalRepository repository;
    public Long save(FestivalComment comment,long id) {
        Festival festival = repository.findById(id);
        comment.setFestival(festival);
        commentRespository.save(comment);
        return comment.getId();
    }
    public List<FestivalComment>findAllById(long festival_id) { //게시글의 id
        Festival festival = repository.findById(festival_id);
        List<FestivalComment> commentList = commentRespository.findAllByFestival(festival);
        return commentList;
    }

    public FestivalComment findCommentById(Long id) {
        return commentRespository.findById(id);
    }

    public Long delete(Long commentId) {
        return commentRespository.deleteById(commentId);
    }
}
