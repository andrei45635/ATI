package com.example.ati.repo;

import com.example.ati.domain.Entity;

import java.util.List;

public interface IRepo<ID, E extends Entity<Integer>> {
    List<E> findAll();
    E save(E e);
}
