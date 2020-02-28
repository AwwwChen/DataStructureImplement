package maxheep;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 10000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i ++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("Without heapify: " + testHeap(testData, false) + " s");
        System.out.println("With heapify: " + testHeap(testData, true) + " s");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> heap;
        if (isHeapify) {
            heap = new MaxHeap<>(testData);
        } else {
            heap = new MaxHeap<>();
            for (int i = 0; i < testData.length; i ++) {
                heap.add(testData[i]);
            }
        }
        long endTime = System.nanoTime();

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i ++) {
            arr[i] = heap.extractMax();
        }
        boolean flag = false;
        for (int i = 1; i < testData.length; i ++) {
            if (arr[i] > arr[i - 1]) {
                flag = true;
            }
        }
        if (flag) System.out.println("Error.");
        else System.out.println("Correct.");

        return (endTime - startTime) / 1000000000.0;
    }
}
