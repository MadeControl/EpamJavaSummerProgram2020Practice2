package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private Node<Object> first;
    private Node<Object> last;
    private int size;

    public ListImpl() {
    }

    @Override
    public  void clear() {
        Node<Object> temp1Node = first;
        Node<Object> temp2Node = first.next;
        for (int i = 0; i < size-1; i++) {
            temp1Node.item = null;
            temp1Node.next = null;
            temp1Node = temp2Node;
            temp2Node = temp2Node.next;
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

        private Node<Object> lastReturned;
        private Node<Object> next = first;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            lastReturned = next;
            if(cursor < size){
                next = next.next;
            }
            cursor++;
            return lastReturned.item;
        }

        @Override
        public void remove() {

            Node<Object> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned){
                next = lastNext;
            }
            else {
                cursor--;
            }
            lastReturned = null;

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

        int iMax = size - 1;
        if (iMax == -1) {
            return "[]";
        }
        Object[] array = this.toArray();

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
        f.next = null;
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
            if (curNode.next != null){
                curNode = curNode.next;
            } else {
                break;
            }
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
        for (Node<Object> x = first; x != null; x = x.next){
            result[i++] = x.item;
        }
        return result;
    }

}
