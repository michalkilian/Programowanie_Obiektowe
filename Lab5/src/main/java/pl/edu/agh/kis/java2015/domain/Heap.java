package pl.edu.agh.kis.java2015.domain;

import pl.edu.agh.kis.java2015.domain.exceptions.NoElementsInHeap;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private int heapSize = 0;
    private ArrayList<Double> tab = new ArrayList<>();


    public void insert(double value) {
        int currentIndex = heapSize;
        tab.add(value);
        bringHeapCondition(currentIndex);
        heapSize++;
    }

    public void bringHeapCondition(int currentIndex) {
        int parentIndex = parentIndex(currentIndex);
        while (isChildGreaterThanParent(currentIndex, parentIndex)) {
            swapElements(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex(currentIndex);
        }
    }


    public boolean isChildGreaterThanParent(int currentIndex, int parentIndex) {
        return tab.get(currentIndex) > tab.get(parentIndex);
    }

    public void swapElements(int currentIndex, int otherIndex) {
        Double otherValue = tab.get(otherIndex);
        Double currentValue = tab.get(currentIndex);
        tab.set(otherIndex, currentValue);
        tab.set(currentIndex, otherValue);
    }


    public int parentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }


    public int size() {
        return heapSize;
    }

    public Double top() {
        return tab.get(0);
    }

    public Double extractMax() throws NoElementsInHeap {
        if (heapSize > 0) {
            Double max = tab.get(0);
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

    public void replace(double newItem) throws NoElementsInHeap {
        if (heapSize > 0) {
            tab.set(0, newItem);
            buildHeap();
        } else throw new NoElementsInHeap("No element to replace");
    }


    public void heapify(List<Double> list) {
        tab.addAll(list);
        heapSize += list.size();
        buildHeap();
    }

    public void heapify(double[] array) {
        for (double x : array) {
            tab.add(x);
            heapSize++;
        }
        buildHeap();
    }

    public Heap merge(Heap secondHeap) {
        Heap mergedHeap = new Heap();
        for (double x : tab) {
            mergedHeap.tab.add(x);
            mergedHeap.heapSize++;
        }
        for (double x : secondHeap.tab) {
            mergedHeap.tab.add(x);
            mergedHeap.heapSize++;
        }
        mergedHeap.buildHeap();
        return mergedHeap;

    }

    public void meld(Heap secondHeap) {
        for (double x : secondHeap.tab) {
            insert(x);
        }
    }

}
