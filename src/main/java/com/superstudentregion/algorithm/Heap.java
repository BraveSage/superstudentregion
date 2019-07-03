package com.superstudentregion.algorithm;

import java.util.Comparator;

/**
 * Create by StarkZhidian on 2019/7/1
 * Email: 1532033525@qq.com
 */
public class Heap<T> {
    int capacity;

    private int rootIndex;
    private Object datas[];
    private int size;
    private Comparator<T> comparator;

    public Heap(int maxCapacity) {
        this(maxCapacity, null);
    }

    public Heap(int maxCapacity, Comparator<T> comparator) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        capacity = maxCapacity;
        datas = new Object[capacity];
        this.comparator = comparator;
    }

    public void insertEle(T ele) {
        if (ele == null) {
            throw new NullPointerException();
        }
        if (size < capacity) {
            datas[size] = ele;
            shiftUp(size++);
        } else {
            if (ele instanceof Comparable && ((Comparable) ele).compareTo(datas[rootIndex]) > 0) {
                datas[rootIndex] = ele;
                shiftDown(rootIndex);
            }
        }
    }

    public void display() {
        for (Object t : datas) {
            System.out.println(t);
        }
    }

    private void display1(int index) {
        if (index < 0 || index >= size) {
            return ;
        }
        System.out.println(datas[index]);
        display1((index << 1) + 1);
        display1((index << 1) + 2);
    }

    private void shiftUp(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalStateException();
        }
        int currentIndex = index;
        int parentIndex;
        T parent;
        boolean needSwap;
        // 利用元素自带比较器比较
        while (currentIndex != 0) {
            needSwap = false;
            parentIndex = (((currentIndex) & 1) == 1 ? currentIndex - 1 : currentIndex - 2) >> 1;
            if (datas[currentIndex] instanceof Comparable) {
                if (((Comparable) datas[currentIndex]).compareTo(datas[parentIndex]) < 0) {
                    needSwap = true;
                }
            } else if (comparator != null && comparator.compare((T) datas[currentIndex], (T) datas[parentIndex]) < 0) {
                needSwap = true;
            }
            if (needSwap) {
                parent = (T) datas[parentIndex];
                datas[parentIndex] = datas[currentIndex];
                datas[currentIndex] = parent;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    private void shiftDown(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalStateException();
        }
        int minValueIndex = index;
        int leftChildIndex;
        int rightChildIndex;
        if ((leftChildIndex = (index << 1) + 1) < size) {
            minValueIndex = getMinValueIndex(minValueIndex, leftChildIndex);
        }
        if ((rightChildIndex = leftChildIndex + 1) < size) {
            minValueIndex = getMinValueIndex(minValueIndex, rightChildIndex);
        }
        if (minValueIndex != index) {
            swapIndexValue(minValueIndex, index);
            shiftDown(minValueIndex);
        }
    }

    private int getMinValueIndex(int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= size || index2 >= size) {
            throw new IllegalStateException();
        }
        if (datas[index1] instanceof Comparable) {
            return ((Comparable) datas[index1]).compareTo(datas[index2]) < 0 ? index1 : index2;
        } else {
            return comparator.compare((T) datas[index1], (T) datas[index2]) < 0 ? index1 : index2;
        }
    }

    private void swapIndexValue(int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= size || index2 >= size) {
            throw new IllegalStateException();
        }
        Object t = datas[index1];
        datas[index1] = datas[index2];
        datas[index2] = t;
    }

    public static void main(String[] args) {
        Heap heap = new Heap<Integer>(1000);
        for (int i = 0; i < 10000; i++) {
            heap.insertEle(i);
        }
        heap.display();
    }
}
