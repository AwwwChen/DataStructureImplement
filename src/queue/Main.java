package queue;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int optCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue:\t" + testQueue(arrayQueue, optCount) + " s");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("LoopQueue:\t" + testQueue(loopQueue, optCount) + " s");
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        System.out.println("LinkedListQueue:\t" + testQueue(linkedListQueue, optCount) + " s");
    }

    // 测试Queue允许opCount次操作花费时间
    private static double testQueue(Queue<Integer> queue, int opCount) {
        Random rand = new Random();
        long time = System.nanoTime();
        for (int i = 0; i < opCount; i ++) {
            queue.enqueue(rand.nextInt());
        }
        for (int i = 0; i < opCount; i ++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - time) / 1000000000.0;
    }
}
