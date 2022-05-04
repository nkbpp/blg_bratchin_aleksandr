package ru.quest.repository;

public interface Specification<T> {

    boolean specified(T t);
}
