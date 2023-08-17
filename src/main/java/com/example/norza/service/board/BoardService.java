package com.example.norza.service.board;

import com.example.norza.domain.SessionUser;

import java.util.List;

public interface BoardService<T,E> {
    public void save(T t, SessionUser sessionUser);
    public List<T> findAll();

    public T findById(long id);

    public void update(long id,E e);

    public void delete(T t);
}
