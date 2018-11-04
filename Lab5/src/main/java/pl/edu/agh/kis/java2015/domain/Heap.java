package pl.edu.agh.kis.java2015.domain;

import pl.edu.agh.kis.java2015.domain.exceptions.NoElementsInHeap;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>>{
    private int heapSize = 0;
    private ArrayList<T> tab = new ArrayList<>();


    public void insert(T value) {
        int currentIndex = heapSize;
        tab.add(value);
        bringHeapCondition(currentIndex);
        heapSize++;
    }

    public void bringHeapCondition(int currentIndex) {
        int parentIndex = parentIndex(currentIndex);
        while (isChildGreaterThanParent(currentIndex, parentIndex) > 0) {
            swapElements(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex(currentIndex);
        }
    }


    public int isChildGreaterThanParent(int currentIndex, int parentIndex) {
        return tab.get(currentIndex).compareTo(tab.get(parentIndex));
    }

    public void swapElements(int currentIndex, int otherIndex) {
        T otherValue = tab.get(otherIndex);
        T currentValue = tab.get(currentIndex);
        tab.set(otherIndex, currentValue);
        tab.set(currentIndex, otherValue);
    }


    public int parentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }


    public int size() {
        return heapSize;
    }

    public T top() {
        return tab.get(0);
    }

    public T extractMax() throws NoElementsInHeap {
        if (heapSize > 0) {
            T max = tab.get(0);
            swapElements(0, heapSize - 1);
            tab.remove(heapSize - 1);
            heapSize -= 1;
            buildHeap();
            return max;
        } else throw new NoElementsInHeap("No elements in heap");
    }

    public void buildHeap() {
        for (int i = heapSize - 1; i > 0; --i) {
            bringHeapCondition(i);
        }
    }

    public void deleteMax() {
        if (heapSize > 0) {
            swapElements(0, heapSize - 1);
            heapSize -= 1;
            buildHeap();
        }
    }

    public void replace(T newItem) throws NoElementsInHeap {
        if (heapSize > 0) {
            tab.set(0, newItem);
            buildHeap();
        } else throw new NoElementsInHeap("No element to replace");
    }


    public void heapify(List<T> list) {
        tab.addAll(list);
        heapSize += list.size();
        buildHeap();
    }

    public void heapify(T[] array) {
        for (T x : array) {
            tab.add(x);
            heapSize++;
        }
        buildHeap();
    }

    public Heap<T> merge(Heap<T> secondHeap) {
        Heap<T> mergedHeap = new Heap<T>();
        for (T x : tab) {
            mergedHeap.tab.add(x);
            mergedHeap.heapSize++;
        }
        for (T x : secondHeap.tab) {
            mergedHeap.tab.add(x);
            mergedHeap.heapSize++;
        }
        mergedHeap.buildHeap();
        return mergedHeap;

    }

    public void meld(Heap<T> secondHeap) {
        for (T x : secondHeap.tab) {
            insert(x);
        }
    }

}
