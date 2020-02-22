package linkedList;

public class Solution {
    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println(" Call: remove " + val + " in " + head);
        if (head == null) {
            System.out.print(depthString);
            System.out.println(" Return:" + head);
            return null;
        }
        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println(" After remove " + val + " : " + res);

        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println(" Return:" + ret);
        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < depth; i ++) {
            result.append("--");
        }
        return result.toString();
    }

     public static void main(String[] args) {
         int[] nums = {1, 2, 3, 4, 5 ,6};
         ListNode head = new ListNode(nums);
         System.out.println(new Solution().removeElements(head, 6, 1));
     }


}
 class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        this.val = x;
        this.next = null;
    }
    ListNode(int[] arr) {
        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1; i < arr.length; i ++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + " ");
            cur = cur.next;
        }
        return res.toString();
    }
}
