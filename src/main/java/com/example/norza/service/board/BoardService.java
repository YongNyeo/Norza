package com.example.norza.service.board;

import com.example.norza.domain.SessionUser;

import java.util.List;

public interface BoardService<T, E> {
    void save(T t, SessionUser sessionUser);

    List<T> findAll();

    T findById(long id);

    void update(long id, E e);

    void delete(long id);
}
