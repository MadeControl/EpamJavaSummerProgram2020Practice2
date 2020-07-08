package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private static int DEFAULT_CAPACITY = 10;
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

        private int iteratorIndex = 0;

        @Override
        public boolean hasNext() {
            return elementData.length >= iteratorIndex+1;
        }

        @Override
        public Object next() {
            return elementData[iteratorIndex++];
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
        if (elementData == null)
            return "null";
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
        arrayImpl.add("SomeObject");
        arrayImpl.clear();
        arrayImpl.add("SomeObject");
        arrayImpl.get(0);
        arrayImpl.indexOf("SomeObject");
        arrayImpl.iterator();
        arrayImpl.remove(0);
        arrayImpl.add("SomeObject");
        arrayImpl.set(0, "AnotherObject");
        arrayImpl.size();
        arrayImpl.toString();
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
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        Object[] oldElementData = elementData;
        elementData = new Object[newCapacity];
        System.arraycopy(oldElementData, 0, elementData, 0, oldElementData.length);
    }
}
