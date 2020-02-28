package map;

import tool.FileOperation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        System.out.println("BSTMap: " + testMap(bstMap) + " s");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        System.out.println("LinkedListMap: " + testMap(linkedListMap) + " s");
    }

    private static double testMap(Map<String, Integer> map1) {
        long startTime = System.nanoTime();

        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        for (String word : word1) {
            if (map1.contains(word)) {
                map1.set(word, map1.get(word) + 1);
            } else {
                map1.add(word, 1);
            }
        }
        System.out.println("Total different words: " + map1.getSize());
        System.out.println("Frequency of \"pride\": " + map1.get("pride"));
        System.out.println("Frequency of \"prejudice\": " + map1.get("prejudice"));

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void readFileTest() {
        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        Map<String, Integer> map1 = new BSTMap<>();
        for (String word : word1) {
            if (map1.contains(word)) {
                map1.set(word, map1.get(word) + 1);
            } else {
                map1.add(word, 1);
            }
        }
        System.out.println("Total different words: " + map1.getSize());
        System.out.println("Frequency of \"pride\": " + map1.get("pride"));
        System.out.println("Frequency of \"prejudice\": " + map1.get("prejudice"));
    }
}
