package com.isharekh.domain.cores;

import com.isharekh.domain.models.videos.Video;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 3/15/2018
*/
public interface EntityService<T> {
    List<T> getAll();

    void save(T t);

    List<T> getAllTShort();

    T getById(Long id);

    void delete(Long id);

    void update(T t,Long id);
}
