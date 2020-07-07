package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;

public class ArrayImplTest {

    @Test
    public void clearTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.clear();
        Assert.assertEquals("[]", arrayImpl.toString());

    }

    @Test
    public void sizeTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        Assert.assertEquals(3, arrayImpl.size());

    }

    @Test
    public void removeTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.set(0, "1");
        arrayImpl.set(2, "3");
        arrayImpl.remove(1);
        Assert.assertEquals("[1, 3]", arrayImpl.toString());

    }

    @Test
    public void setTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.set(0, "1");
        Assert.assertEquals("[1, null, null]", arrayImpl.toString());

    }

    @Test
    public void toStringTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        Assert.assertEquals("[null, null, null]", arrayImpl.toString());

    }

    @Test
    public void indexOfTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.set(2, "1");
        Assert.assertEquals(2, arrayImpl.indexOf("1"));

    }

    @Test
    public void getTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.set(1, "GET");
        Assert.assertEquals("GET", arrayImpl.get(1));

    }

    @Test
    public void addTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.add("ADD");
        Assert.assertEquals("[null, null, null, ADD]", arrayImpl.toString());

    }

}
