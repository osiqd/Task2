package com.example.iterator;

public interface Iterator {
    boolean hasNext(int x);
    Object next();
    Object preview();
    Object previous();
}

