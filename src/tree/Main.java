package tree;

import map.BSTMap;
import tool.FileOperation;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 20000000;
        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            testData.add(i);
        }

//        long startTime = System.nanoTime();
//        BSTMap<Integer, Integer> bst = new BSTMap<>();
//        for (Integer x : testData)
//            bst.add(x, null);
//        long endTime = System.nanoTime();
//        System.out.println((endTime - startTime) / 1000000000.0);

        long startTime = System.nanoTime();
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (Integer x : testData)
            avlTree.add(x, null);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000.0);

        startTime = System.nanoTime();
        RBTree<Integer, Integer> rbTree = new RBTree<>();
        for (Integer x : testData)
            rbTree.add(x, null);
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000.0);

    }
    private static double testMap(AVLTree<String, Integer> avlTree) {
        long startTime = System.nanoTime();

        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        for (String word : word1) {
            if (avlTree.contains(word)) {
                avlTree.set(word, avlTree.get(word) + 1);
            } else {
                avlTree.add(word, 1);
            }
        }
        System.out.println("Total different words: " + avlTree.getSize());
        System.out.println("Frequency of \"pride\": " + avlTree.get("pride"));
        System.out.println("Frequency of \"prejudice\": " + avlTree.get("prejudice"));

        System.out.println("is BST? " + avlTree.isBST());
        System.out.println("is balanced tree? " + avlTree.isBalanced());

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}
