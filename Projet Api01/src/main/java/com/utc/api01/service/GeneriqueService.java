package com.utc.api01.service;

import java.util.List;

public interface GeneriqueService<T> {

    public void add(T u);

    public void update(T u);

    public List<T> list();

    public T getById(int id);
    
    public T getByCriteria(String criteria, String value);

    public void remove(int id);

}
