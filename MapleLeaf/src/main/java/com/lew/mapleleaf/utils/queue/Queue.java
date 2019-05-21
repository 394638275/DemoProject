package com.lew.mapleleaf.utils.queue;

public interface Queue<E> {
    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E element();

    E peek();
}
