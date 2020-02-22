package queue;

public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E element;
        public Node next;

        public Node() {
            element = null;
            next = null;
        }

        public Node(E e) {
            element = e;
            next = null;
        }

        public Node(E e, Node next) {
            element = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            // 队列为空 head和tail同时指向新节点
            tail = new Node(e);
            head = tail;
        } else {
            // 队列不为空 tail指向新节点 head不变
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.element;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot getFront from an empty queue.");
        }
        return head.element;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node cur = head;
        while (cur != null) {
            res.append(cur.element);
            if (cur.next != null) {
                res.append(" -> ");
            }
            cur = cur.next;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i ++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
