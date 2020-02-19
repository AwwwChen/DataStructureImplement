package array;

public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>(10);
        for(int i = 0; i < 10; i ++) {
            array.addLast(i);
        }
        System.out.println(array.toString());
        array.addLast(-1);
        array.addFirst(0);
        array.add(1, 20);
        System.out.println(array.toString());
        // [0, 20, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1]
        array.remove(2);
        array.removeElement(4);
        array.removeFirst();
        array.removeFirst();
        array.removeFirst();

        System.out.println(array.toString());


//        // 第一种创建数组的方式
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i ++) {
//            arr[i] = i;
//        }
//
//        // 第二种创建数组的方式
//        int[] scores = new int[] {100, 99, 66};
//        for (int i = 0; i < scores.length; i ++) {
//            System.out.println(scores[i]);
//        }
//        // for-each 语法遍历
//        scores[0] = 99;
//        for(int score : scores) {
//            System.out.println(score);
//        }
    }
}
