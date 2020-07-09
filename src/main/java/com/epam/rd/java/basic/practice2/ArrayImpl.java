package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size = 0;

    public ArrayImpl() {
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

	@Override
    public int size() {
        return size;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return size > cursor;
        }

        @Override
        public Object next() {
            if (cursor < size){
                return elementData[cursor++];
            }
            throw new NoSuchElementException();
        }

    }

	@Override
    public void add(Object element) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = element;
    }

	@Override
    public void set(int index, Object element) {
        if(index < size){
            elementData[index] = element;
        } else {
            throw new NoSuchElementException();
        }
    }

	@Override
    public Object get(int index) {
        if(index < size){
            return elementData[index];
        } else {
            throw new NoSuchElementException();
        }
    }

	@Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (element.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        if(index < size){

            int numMoved = size - index - 1;
            if (numMoved > 0){
                System.arraycopy(elementData, index+1, elementData, index, numMoved);
            }
            elementData[--size] = null; // clear to let GC do its work

        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {

        return getString(size, elementData);
    }

    static String getString(int size, Object[] elementData) {
        int iMax = size - 1;
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
        ArrayImpl arrayImpl = new ArrayImpl();
        arrayImpl.add("Something");
        arrayImpl.clear();
        arrayImpl.add("AnyObject");
        arrayImpl.get(0);
        arrayImpl.indexOf("SomeObject");
        arrayImpl.iterator();
        arrayImpl.remove(0);
        arrayImpl.add("Object");
        arrayImpl.set(0, "AnotherObject");
        arrayImpl.size();
    }


    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        Object[] oldElementData = elementData;
        elementData = new Object[newCapacity];
        System.arraycopy(oldElementData, 0, elementData, 0, oldElementData.length);
    }
}
