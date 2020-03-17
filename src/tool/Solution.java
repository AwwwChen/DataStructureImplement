package tool;

import java.util.*;

// {3, 19, 20, 31, 27, 18, 22, 5}
public class Solution {

    public static List<List<Integer>> FindPair(List<Integer> input, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        Collections.sort(input);
        int i = 0;
        int j = input.size() - 1;
        while (i < j) {
            if (input.get(i) + input.get(j) == sum) {
                List<Integer> list = new ArrayList<>();
                list.add(input.get(i));
                list.add(input.get(j));
                lists.add(list);
            } else if (input.get(i) + input.get(j) < sum && (j - 1 > i && !input.get(j - 1).equals(input.get(j)))) {
                i++;
            } else if (input.get(i) + input.get(j) > sum && (i + 1 < j && !input.get(i + 1).equals(input.get(i)))) {
                j--;
            }
        }
        return lists;
    }

    public static List<List<Integer>> FindPair2(List<Integer> input, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < input.size(); i ++) {
            if (map.containsKey(input.get(i))) {
                List<Integer> list = new ArrayList<>();
                list.add(input.get(i));
                list.add(map.get(input.get(i)));
                lists.add(list);
            } else {
                map.put(sum - input.get(i), input.get(i));
            }
        }
        return lists;
    }

    public static void main(String... args) {
        Stack
        List<Integer> list = new ArrayList<>();
        int[] l = new int[] {3, 19, 19, 20, 31, 27, 18, 22, 2};
        for (int i = 0; i < l.length; i ++)
            list.add(l[i]);
        System.out.println(FindPair(list, 22));
        System.out.println(FindPair2(list, 22));
    }
}
