package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private Object[] elementData;
    private int elementCount = 0;

    public StackImpl() {
        this.elementData = new Object[0];
    }

    public StackImpl(int initialCapacity) {
        this.elementData = new Object[initialCapacity];
    }

    @Override
    public void clear() {
        for (int i = 0; i < elementCount; i++)
            elementData[i] = null;

        elementCount = 0;
    }

    @Override
    public int size() {
        return elementCount;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int cursor = elementCount-1;

        @Override
        public boolean hasNext() {
            return cursor < 0;
        }

        @Override
        public Object next() {
            if (cursor <= 0){
                throw new NoSuchElementException();
            }
            return elementData[cursor--];
        }

    }

    @Override
    public void push(Object element) {
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = element;
    }

    @Override
    public Object pop() {
        if(elementCount > 0){
            Object object = elementData[elementCount-1];
            elementData[--elementCount] = null;
            return object;
        } else {
            return null;
        }
    }

    @Override
    public Object top() {
        if (elementCount == 0){
            return null;
        }
        return elementData[elementCount-1];
    }

    @Override
    public String toString() {
        if (elementData == null) {
            return "null";
        }
        int iMax = elementCount - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; ; i++) {
            stringBuilder.append(elementData[i]);
            if (i == iMax)
                return stringBuilder.append(']').toString();
            stringBuilder.append(", ");
        }
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push("1");
        stack.clear();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.size();
        stack.top();
        stack.pop();
        stack.iterator();

    }

    private void ensureCapacityHelper(int minCapacity) {
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((elementData.length < 10) ? 0 : oldCapacity);
        if (newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }

        Object[] oldElementData = elementData;
        elementData = new Object[newCapacity];
        System.arraycopy(oldElementData, 0, elementData, 0, oldElementData.length);
    }

}
