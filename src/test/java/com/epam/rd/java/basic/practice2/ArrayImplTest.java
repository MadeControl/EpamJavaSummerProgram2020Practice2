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

}
