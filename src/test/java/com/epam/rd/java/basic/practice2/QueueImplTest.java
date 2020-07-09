package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;

public class QueueImplTest {

    private QueueImpl queue = new QueueImpl();

    @Test
    public void clearTest(){

        queue.clear();
        Assert.assertEquals("[]", queue.toString());

    }

    @Test
    public void sizeTest(){

        queue.enqueue("1");
        Assert.assertEquals(1, queue.size());

    }

    @Test
    public void enqueueTest(){

        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        Assert.assertEquals("[1, 2, 3]", queue.toString());

    }

    @Test
    public void dequeueTest(){

        queue.enqueue("1");
        Assert.assertEquals("1", queue.dequeue());

    }

    @Test
    public void topTest(){

        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        Assert.assertEquals("1", queue.top());

    }

    @Test
    public void toStringTest(){

        Assert.assertEquals("[]", queue.toString());

    }

    @Test
    public void iteratorTest(){

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("null");
        Iterator<Object> iter = queue.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iter.hasNext()){
            stringBuilder.append(iter.next());
        }
        Assert.assertEquals("ABCnull", stringBuilder.toString());

    }





}
