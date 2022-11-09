package com.prog2.interfaces;

public interface List<T> {

    boolean isEmpty();
    int getLength();
    T get(int index);
    List prepend(T element);
    List append(T element);
    List insert(int index, T element);
    List remove(int index);
    int indexOf(T element);


}
