package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class StackImplTest {

    private StackImpl stack = new StackImpl();

    @Test
    public void clearTest(){

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.clear();
        Assert.assertEquals("[]", stack.toString());

    }

    @Test
    public void sizeTest(){

        stack.push("1");
        stack.push("2");
        stack.push("3");
        Assert.assertEquals(3, stack.size());

    }

    @Test
    public void iteratorTest(){

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("null");
        Iterator<Object> iter = stack.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iter.hasNext()){
            stringBuilder.append(iter.next());
        }
        Assert.assertEquals("nullCBA", stringBuilder.toString());

    }

    @Test
    public void pushTest(){

        stack.push("1");
        stack.push("2");
        stack.push("3");
        Assert.assertEquals("[1, 2, 3]", stack.toString());

    }

    @Test
    public void popTest(){

        stack.push("1");
        stack.push("2");
        stack.push("3");
        Assert.assertEquals("3", stack.pop());

    }

    @Test
    public void topTest(){

        stack.push("1");
        stack.push("2");
        Assert.assertEquals("2", stack.top());

    }

    @Test
    public void toStringTest(){

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("null");
        Assert.assertEquals("[A, B, C, null]", stack.toString());

    }



}
