package pl.edu.agh.kis.java2015.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.kis.java2015.domain.exceptions.NoElementsInHeap;

import java.util.ArrayList;
import java.util.LinkedList;

public class HeapTest {

    private Heap<Integer> heap;

    @Before
    public void makeHeap() {
        this.heap = new Heap<>();
    }


    @Test
    public void insert0intoNewHeap_topIs0() {


        heap.insert(0);

        assertEquals("size should be 1", 1, heap.size());
        assertEquals(0, heap.top(), 0.001);
        assertEquals(1, heap.size());
    }

    @Test
    public void insert0AndThen2intoNewHeap_topIs2() {


        heap.insert(0);
        heap.insert(2);

        assertEquals("size should be 2", 2, heap.size());
        assertEquals(2, heap.top(), 0.001);
    }

    @Test
    public void insert0And2And3And5And6intoNewHeap_topIs6() {


        heap.insert(0);
        heap.insert(2);
        heap.insert(3);
        heap.insert(5);
        heap.insert(6);

        assertEquals(6, heap.top(), 0.001);
    }

    @Test
    public void insert3andExtractMax_maxIs3() {


        heap.insert(3);

        try {
            assertEquals(3, heap.extractMax(), 0.001);
        } catch (NoElementsInHeap noElementsInHeap) {
            noElementsInHeap.printStackTrace();
        }
    }

    @Test
    public void insert3And3And2And4AndExtractMax_maxIs4() {


        heap.insert(3);
        heap.insert(3);
        heap.insert(2);
        heap.insert(4);

        try {
            assertEquals(4, heap.extractMax(), 0.001);
        } catch (NoElementsInHeap noElementsInHeap) {
            noElementsInHeap.printStackTrace();
        }
    }

    @Test(expected = NoElementsInHeap.class)
    public void extractEmptyHeapThrowException() throws NoElementsInHeap {

        heap.extractMax();

    }

    @Test
    public void deleteMaxFrom3_4_3_2Heap_maxIs3() {

        heap.insert(3);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.deleteMax();
        assertEquals(3, heap.top(), 0.001);
    }

    @Test
    public void replace3_4_3_2HeapWith7_maxIs7() throws NoElementsInHeap {
        heap.insert(3);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.replace(7);
        assertEquals(7, heap.top(), 0.001);
    }

    @Test
    public void replace3_7_4_2HeapWith2_maxIs2() throws NoElementsInHeap {

        heap.insert(3);
        heap.insert(7);
        heap.insert(4);
        heap.insert(2);
        heap.replace(2);
        assertEquals(4, heap.top(), 0.001);
    }

    @Test(expected = NoElementsInHeap.class)
    public void replaceEmptyHeapThrowException() throws NoElementsInHeap {

        heap.replace(0);

    }

    @Test
    public void makeHeapFromArray() {

        Integer[] array = new Integer[]{3, 4, 3, 2};
        heap.heapify(array);
        assertEquals(4, heap.top(), 0.001);
    }

    @Test
    public void makeHeapFromLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(4);
        list.add(3);
        list.add(2);
        heap.heapify(list);
        assertEquals(4, heap.top(), 0.001);
    }

    @Test
    public void makeHeapFromArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(3);
        list.add(2);
        heap.heapify(list);
        assertEquals(4, heap.top(), 0.001);
    }

    @Test
    public void mergeTwoHeaps_maxFromFirst(){
        Heap<Integer> secondHeap = new Heap<>();

        heap.insert(3);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);

        secondHeap.insert(1);
        secondHeap.insert(3);
        secondHeap.insert(2);

        Heap mergedHeap = heap.merge(secondHeap);
        assertEquals(4, mergedHeap.top());
    }
    @Test
    public void mergeTwoHeaps_maxFromSecond(){
        Heap<Integer> secondHeap = new Heap<>();

        heap.insert(3);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);

        secondHeap.insert(1);
        secondHeap.insert(6);
        secondHeap.insert(2);

        Heap mergedHeap = heap.merge(secondHeap);
        assertEquals(6, mergedHeap.top());
    }

    @Test
    public void meldTwoHeaps_maxFromSecond(){
        Heap<Integer> secondHeap = new Heap<>();

        heap.insert(3);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);

        secondHeap.insert(1);
        secondHeap.insert(6);
        secondHeap.insert(2);

        heap.meld(secondHeap);
        assertEquals(6, heap.top(), 0.001);

    }

    @Test
    public void stringHeapTest(){
        Heap<String> heap = new Heap<>();
        heap.insert("a");
        heap.insert("d");
        heap.insert("c");
        heap.insert("b");

        assertEquals("d", heap.top());
    }

}
