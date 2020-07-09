package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListImpl implements List {

    private Node<Object> first;
    private Node<Object> last;
    private int size;

    @Override
    public  void clear() {
        for (Node<Object> x = first; x != null; ) {
            Node<Object> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        first = last = null;
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
            return cursor != size;
        }

        @Override
        public Object next() {
            if(cursor == 0 && size == 0){
                throw new NoSuchElementException();
            }
            Node<Object> tempNode = first;
            for(int i = 0; i < cursor; i++){
                tempNode = tempNode.next;
            } cursor++;
            return tempNode.item;
        }

    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void addFirst(Object element) {
        linkFirst(element);
    }

    @Override
    public void addLast(Object element) {
        linkLast(element);
    }

    @Override
    public void removeFirst() {
        final Node<Object> f = first;
        if (f == null){
            throw new NoSuchElementException();
        }
        unlinkFirst(f);
    }

    @Override
    public void removeLast() {
        final Node<Object> l = last;
        if (l == null){
            throw new NoSuchElementException();
        }
        unlinkLast(l);
    }

    @Override
    public Object getFirst() {
        final Node<Object> f = first;
        if (f == null){
            throw new NoSuchElementException();
        }
        return f.item;
    }

    @Override
    public Object getLast() {
        final Node<Object> l = last;
        if (l == null){
            throw new NoSuchElementException();
        }
        return l.item;
    }

    @Override
    public Object search(Object element) {
        for(Node<Object> x = first; x != null; x = x.next){
            if(element.equals(x.item)){
                return  x.item;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            for (Node<Object> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<Object> x = first; x != null; x = x.next) {
                if (element.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        Object[] array = this.toArray();
        if (array == null)
            return "null";

        int iMax = array.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void main(String[] args) {

        ListImpl listImpl = new ListImpl();
        listImpl.addFirst("First");
        listImpl.addLast("something1");
        listImpl.addLast("something2");
        listImpl.addLast("something3");
        listImpl.addLast("Last");
        listImpl.size();
        listImpl.getFirst();
        listImpl.getLast();
        listImpl.removeFirst();
        listImpl.removeLast();
        listImpl.search("something4");
        listImpl.remove("something2");
        listImpl.clear();

    }

    private void linkFirst(Object e) {
        final Node<Object> f = first;
        final Node<Object> newNode = new Node<>(e, f);
        first = newNode;
        if (f == null){
            last = newNode;
        }
        size++;
    }

    private void linkLast(Object e) {
        final Node<Object> l = last;
        final Node<Object> newNode = new Node<>(e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    private void unlinkFirst(Node<Object> f) {
        final Node<Object> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null){
            last = null;
        }
        size--;
    }

    private void unlinkLast(Node<Object> l) {
        final Node<Object> prev = findPrevNode(last.item);
        l.item = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
    }

    private Node<Object> findPrevNode(Object x){
        Node<Object> curNode = first;
        Node<Object> prevNode = null;
        while (!x.equals(curNode)){
            prevNode = curNode;
            curNode = curNode.next;
        } return  prevNode;
    }

    private void unlink(Node<Object> x) {
        final Node<Object> next = x.next;
        final Node<Object> prev = findPrevNode(x);

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            x.next = null;
        }

        x.item = null;
        size--;
    }

    private Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<Object> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

}
