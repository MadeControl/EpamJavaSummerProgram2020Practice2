package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static com.epam.rd.java.basic.practice2.ArrayImpl.getString;

public class QueueImpl implements Queue {

    private int front = 0;
    private int rear = 0;

    private final int capacity;

    private int size = 0;

    private Object[] array;

    public QueueImpl() {
        this.capacity = 10;
        array = new Object[capacity];
    }

    public QueueImpl(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
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
                return array[cursor++];
            }
            throw new NoSuchElementException();
        }

    }

    @Override
    public void enqueue(Object element) {

        if (size >= array.length) {
            grow();
        }
        if (capacity != rear) {
            array[rear] = element;
            rear++;
        }
        ++size;
    }

    @Override
    public Object dequeue() {

        if (array == null || array.length <= 0){
            return null;
        }
        Object head = array[0];
        if (front != rear) {
            if (rear - 1 >= 0){
                System.arraycopy(array, 1, array, 0, rear - 1);
            }
            if (rear < capacity){
                array[rear] = null;
            }
            rear--;
        }
        --size;
        return head;
    }

    @Override
    public Object top() {
        if (front != rear)
            return array[0];

        return null;
    }

    @Override
    public String toString() {

        return getString(size, array);
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.size();
        queue.top();
        queue.dequeue();
        queue.iterator();
        queue.clear();
    }

    private void grow() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + 3;

        Object[] oldQueue = array;
        array = new Object[newCapacity];
        System.arraycopy(oldQueue, 0, array, 0, oldQueue.length);
    }

}
