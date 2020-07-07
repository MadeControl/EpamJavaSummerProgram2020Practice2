package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;

public class ArrayImplTest {

    @Test
    public void clearTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.clear();
        Assert.assertEquals("[null, null, null]", arrayImpl.toString());

    }

    @Test
    public void sizeTest(){

        ArrayImpl arrayImpl = new ArrayImpl(3);
        Assert.assertEquals(3, arrayImpl.size());

    }

}
