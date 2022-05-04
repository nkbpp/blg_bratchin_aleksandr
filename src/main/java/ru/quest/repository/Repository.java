package ru.quest.repository;

import java.util.List;

public interface Repository<T> {

    List<T> query(Specification<T> specification);

    void add(T t);

    void remove(T t);

    void update(T t);
}
