package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class ListImplTest {

    @Test
    public void clearTest(){

        ListImpl list =  new ListImpl();
        list.clear();
        Assert.assertEquals("[]", list.toString());

    }

    @Test
    public void sizeTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        Assert.assertEquals(3, list.size());

    }

    @Test
    public void addFirstTest(){

        ListImpl list =  new ListImpl();
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        Assert.assertEquals("[3, 2, 1]", list.toString());

    }

    @Test
    public void addLastTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        Assert.assertEquals("[1, 2, 3]", list.toString());

    }

    @Test
    public void removeFirstTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        list.removeFirst();
        Assert.assertEquals("[2, 3]", list.toString());

    }

    @Test
    public void removeLastTest(){

        ListImpl list =  new ListImpl();
        list.addFirst("1");
        list.removeLast();
        Assert.assertEquals("[]", list.toString());

    }

    @Test
    public void removeExistElementTestTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        Assert.assertTrue(list.remove("3"));

    }

    @Test
    public void removeNotExistElementTest(){

        ListImpl list =  new ListImpl();
        Assert.assertFalse(list.remove("4"));

    }

    @Test
    public void getFirstTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        Assert.assertEquals("1", list.getFirst());

    }

    @Test
    public void getLastTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        Assert.assertEquals("3", list.getLast());

    }

    @Test
    public void searchExistElementTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        Assert.assertEquals("3", list.search("3"));

    }

    @Test
    public void searchNotExistElementTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        Assert.assertNull(list.search("4"));

    }

    @Test
    public void toStringTest(){

        ListImpl list =  new ListImpl();
        list.addLast("1");
        list.addLast("2");
        Assert.assertEquals("[1, 2]", list.toString());

    }

    @Test
    public void iteratorTest(){

        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast("null");
        Iterator<Object> iter = list.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iter.hasNext()){
            stringBuilder.append(iter.next());
        }
        Assert.assertEquals("ABCnull", stringBuilder.toString());

    }

}
