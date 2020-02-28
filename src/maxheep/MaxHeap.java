package maxheep;

import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // 用户传来一个数组 进行heapify操作
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void add(E e) {
        data.addLast(e);
        // 传入要sift up的索引
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // 交换k和k父亲节点 “上浮”
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 查看堆中最大的元素
    public E findMax() {
        if (data.getSize() == 0) return null;
        return data.get(0);
    }

    // 提取堆中最大的元素
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast(); // 删除堆中最大的元素
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            // j存放左右孩子中大的节点的下标
            int j = leftChild(k);
            if (rightChild(k) < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }

            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    // 返回父亲节点在数组中的索引
    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回右孩子节点在数组中的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回右孩子节点在数组中的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 取出堆中最大的元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {
        int n = 100000;
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i ++)
            heap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = heap.extractMax();
        }

        boolean flag = false;
        for (int i = 1; i < n; i ++) {
            if (arr[i] > arr[i - 1]) {
                flag = true;
            }
        }
        if (flag) System.out.println("Error.");
        else System.out.println("Correct.");
    }
}
