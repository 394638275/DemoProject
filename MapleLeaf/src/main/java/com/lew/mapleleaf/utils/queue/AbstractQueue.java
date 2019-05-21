package com.lew.mapleleaf.utils.queue;

import java.util.NoSuchElementException;

public abstract class AbstractQueue<E> implements Queue<E> {

    @Override
    public boolean add(E e) {
        if (offer(e)) {
            return true;
        } else {
            throw new IllegalStateException("Queue full");
        }
    }

    @Override
    public E remove() {
        E x = poll();
        if (x != null) {
            return x;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E element() {
        E x = peek();
        if (x != null) {
            return x;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void clear() {
        while (poll() != null)
            ;
    }
}
