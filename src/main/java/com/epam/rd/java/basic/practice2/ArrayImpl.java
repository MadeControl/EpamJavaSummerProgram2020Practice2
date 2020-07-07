package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    Object[] objectBase = new Object[0];

    public ArrayImpl() {
    }

    public ArrayImpl(int size) {
        this.objectBase = new Object[size];
    }

    @Override
    public void clear() {
        objectBase = new Object[0];
    }

	@Override
    public int size() {
        return objectBase.length;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        private int iteratorIndex = 0;

        @Override
        public boolean hasNext() {
            return objectBase.length >= iteratorIndex+1;
        }

        @Override
        public Object next() {
            return objectBase[iteratorIndex++];
        }

    }
	
	@Override
    public void add(Object element) {
        Object[] oldObjectBase = objectBase;
        objectBase = new Object[objectBase.length + 1];
        for(int i = 0; i < objectBase.length-1; i++){
            objectBase[i] = oldObjectBase[i];
        }
        objectBase[objectBase.length-1] = element;

    }

	@Override
    public void set(int index, Object element) {
        if(index < objectBase.length){
            objectBase[index] = element;
        } else {
            throw new NoSuchElementException();
        }
    }

	@Override
    public Object get(int index) {
        if(index < objectBase.length){
            return objectBase[index];
        } else {
            throw new NoSuchElementException();
        }
    }

	@Override
    public int indexOf(Object element) {
        for(int i = 0; i < objectBase.length; i++){
            if(element.equals(objectBase[i])){
                return i;
            }
        } return -1;
    }

	@Override
    public void remove(int index) {
        if(index < objectBase.length){
            Object[] oldObjectBase = objectBase;
            objectBase = new Object[objectBase.length - 1];
            int j = 0;
            for(int i = 0; i < objectBase.length; i++){
                if(i == index){
                    j++;
                }
                objectBase[i] = oldObjectBase[j];
                j++;
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        if (objectBase == null)
            return "null";
        int iMax = objectBase.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; ; i++) {
            stringBuilder.append(objectBase[i]);
            if (i == iMax)
                return stringBuilder.append(']').toString();
            stringBuilder.append(", ");
        }
    }

    public static void main(String[] args) {
        ArrayImpl arrayImpl = new ArrayImpl();

        arrayImpl.add("SomeObject");
        arrayImpl.clear();
        arrayImpl.get(0);
        arrayImpl.indexOf("SomeObject");
        arrayImpl.iterator();
        arrayImpl.remove(0);
        arrayImpl.set(0, "AnotherObject");
        arrayImpl.size();
        arrayImpl.toString();
    }

}
