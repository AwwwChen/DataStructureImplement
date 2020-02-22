package linkedList;

import stack.ArrayStack;
import stack.Stack;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        LinkedList<Integer> linkedList = new LinkedList<>();
//        for(int i = 0; i < 10; i ++) {
//            linkedList.addFirst(i);
//            System.out.println(linkedList);
//        }
//        linkedList.add(2, 666);
//        System.out.println(linkedList);
//        linkedList.remove(2);
//        linkedList.removeFirst();
//        linkedList.removeLast();
//        System.out.println(linkedList);

//        int opCount = 10000000;
//        ArrayStack<Integer> arrayStack = new ArrayStack<>();
//        System.out.println(testStack(arrayStack, opCount));
//        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
//        System.out.println(testStack(linkedListStack, opCount));

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(arr));

    }

    // 测试Stack允许opCount次操作花费时间
    private static double testStack(Stack<Integer> stack, int opCount) {
        Random rand = new Random();
        long time = System.nanoTime();
        for (int i = 0; i < opCount; i ++) {
            stack.push(rand.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i ++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - time) / 1000000000.0;
    }

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    // 计算arr[l...n]这个区间内所有数字的和
    private static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }

}
