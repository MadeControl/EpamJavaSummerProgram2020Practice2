package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Object[] queue;
    private int size = 0;

    public QueueImpl() {
        this.queue = new Object[]{};
    }

    public QueueImpl(int initialCapacity) {
        this.queue = new Object[initialCapacity];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            queue[i] = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            if (cursor < size){
                return queue[cursor++];
            }
            throw new NoSuchElementException();
        }

    }

    @Override
    public void enqueue(Object element) {
        if (element == null){
            throw new NullPointerException();
        }
        if (size >= queue.length) {
            grow();
        }
        queue[size++] = element;
    }

    @Override
    public Object dequeue() {
        if (size == 0){
            return null;
        } Object object = queue[0];
        Object[] newQueue = new Object[queue.length];
        System.arraycopy(queue, 1, newQueue, 0, --size);
        queue = newQueue;
        return object;
    }

    @Override
    public Object top() {
        if (size == 0){
            return null;
        }
        return queue[0];
    }

    @Override
    public String toString() {

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; ; i++) {
            stringBuilder.append(queue[i]);
            if (i == iMax)
                return stringBuilder.append(']').toString();
            stringBuilder.append(", ");
        }
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl(0);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.size();
        queue.top();
        queue.dequeue();
        queue.clear();
        queue.iterator();
    }

    private void grow() {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));

        Object[] oldQueue = queue;
        queue = new Object[newCapacity];
        System.arraycopy(oldQueue, 0, queue, 0, oldQueue.length);
    }

}
