package com.utc.api01.dao;

import java.util.List;

public interface GeneriqueDao<T> {

    public void add(T c);

    public void update(T c);

    public List<T> list();

    public T getById(int id);

    public void remove(int id);

}
