package tool;

public class Solution {
    public void rotate(int[] nums, int k) {
        int temp = nums[0];
        int t;
        int index = 0;
        for (int i = 0; i < nums.length; i ++) {
            t = temp;
            temp = nums[(index + k) % nums.length];
            nums[(index + k) % nums.length] = t;
            index = (index + k) % nums.length;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,4,5,6};
        solution.rotate(nums, 2);
    }
}
