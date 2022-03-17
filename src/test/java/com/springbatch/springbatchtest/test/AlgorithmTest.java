package com.springbatch.springbatchtest.test;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.springbatch.springbatchtest.test.StructureTest.ListNode;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;

import java.util.concurrent.CyclicBarrier;

/**
 * 算法练习
 */
@SpringBootTest
@Log4j2
public class AlgorithmTest {

    //递归
    public int test(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return test(n - 1) + test(n - 2);
    }

    public long subArrayRanges(int[] nums) {
        //SORT
        int[] ints = Arrays.stream(nums).sorted().toArray();
        //COMPUTE
        Long compute = 0L;
        for (int a : ints) {
            for (int b : ints) {
                if (b < a) {
                    compute += Math.abs(a - b);
                } else {
                    compute += Math.abs(b - a);
                }
//                System.out.println("b:"+b);
//                System.out.println("a:"+a);
//                System.out.println("Math.abs(b-a):"+Math.abs(b-a));
//                System.out.println("compute:"+compute);
//                System.out.println("---------------------------");
            }
        }
        return compute;
    }

    //  回文数
    public boolean isPalindrome(int x) {
        //特殊情况
        if (x == 0) return true;
        if (x < 0) return false;
        if (x % 10 == 0) return false;
        //计算
        char[] a = String.valueOf(x).toCharArray();
        int i = 0;
        int j = a.length - 1;

        while (i <= j) {
            if (a[i] != a[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0)
            return false;
        int cur = 0;
        int num = x;
        while (num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }

    //  二分查找
    public int search(int[] nums, int target) {
        int mid = nums.length / 2;
        int low = 0;
        int high = nums.length - 1;
        return search1(nums, target, low, high, mid);
    }

    public int search1(int[] nums, int target, int low, int high, int mid) {
        if (nums[mid] > target) {
            high = mid - 1;
            return search1(nums, target, low, high, mid);
        } else if (nums[mid] < target) {
            low = mid + 1;
            return search1(nums, target, low, high, mid);
        } else if (nums[mid] == target) {
            return mid;
        }
        return -1;
    }

    //数组最大值
    public Map getArrayMaxAndIndex(int[] aar) {
        HashMap map = new HashMap<String, Integer>();
        if (aar.length > 0) {
            int aar_Max = aar[0], aar_index = 0;
            for (int i = 0; i < aar.length; i++) {
                if (aar[i] > aar_Max) {//比较后赋值
                    aar_Max = aar[i];
                    aar_index = i;
                }
                map.put("max", aar_Max);
                map.put("index", aar_index);
            }
        }
        return map;
    }

    //  罗马数字转整数
    public int enumNum(char romeNum) {
        switch (romeNum) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public int romanToInt(String s) {
        int number = 0;
        int preNumber = 0;
        char[] chars = s.toCharArray();
        int[] intNum = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            intNum[i] = enumNum(chars[i]);
        }

        for (int i = 0; i < intNum.length; i++) {
            preNumber = intNum[i];
            int num = i + 1 == intNum.length ? 0 : intNum[i + 1];
            if (preNumber < num) {
                number -= preNumber;
            } else {
                number += preNumber;
            }
        }
        return number;
    }

    public int romanToInt1(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    //  有效的括号
    public boolean isValid(String s) {
        Stack<Integer> left = new Stack<>();
        int index = -1;
        char[] chars = s.toCharArray();
        if (chars.length % 2 == 1) {
            return false;
        }
        for (char a : chars) {
            if (Integer.valueOf(a) == 40 || Integer.valueOf(a) == 91 || Integer.valueOf(a) == 123) {
                left.push(Integer.valueOf(a));
                index++;
            } else {
                if (index < 0) {
                    return false;
                }
                if ((Integer) left.get(index) != (Integer.valueOf(a) - 1) && (Integer) left.get(index) == 40) {
                    return false;
                } else if ((Integer) left.get(index) != (Integer.valueOf(a) - 2) && (Integer) left.get(index) > 40) {
                    return false;
                }
                left.remove(index);
                index--;
            }
        }
        if (!left.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isValid1(String s) {
        Stack<Character> left = new Stack<>();
        if (s.length() == 0) {
            return true;
        }
        if (s.length() >> 1 == 1) {
            return false;
        }
        for (char a : s.toCharArray()) {
            if (a == '(') {
                left.push(')');
            } else if (a == '[') {
                left.push(']');
            } else if (a == '{') {
                left.push('}');
            } else if (left.empty() || a != left.pop()) {
                return false;
            }
        }
        return left.isEmpty();
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public int removeDuplicates(int[] nums) {
        ArrayList<Integer> index = new ArrayList<>();
        int min = nums[0] - 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                nums[i - 1] = min;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != min) index.add(i);
        }
        for (int i = 0; i < index.size(); i++) {
            nums[i] = nums[index.get(i)];
        }

        return index.size();
    }

    public int removeElement(int[] nums, int val) {
        int serchIndex = 0;
        int arrayIndex = 0;
        int maxIndex = nums.length;
        while (serchIndex < maxIndex) {
            if (nums[serchIndex] != val) {
                nums[arrayIndex] = nums[serchIndex];
                arrayIndex++;
            }
            serchIndex++;
        }
        return arrayIndex;
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
//        if (needle.isEmpty()) {
//            return 0;
//        }
//        int needleLength = needle.length();
//        int haystackLength = haystack.length();
//        if (needleLength > haystackLength) {
//            return -1;
//        } else if (needleLength == haystackLength) {
//            if (needle.equals(haystack)) {
//                return 0;
//            } else {
//                return -1;
//            }
//        }
//        final char[] haystackChars = haystack.toCharArray();
//        final char[] needleChars = needle.toCharArray();
//        int haystackIndex = 0;
//        int needleIndex = 0;
//        int haystackInnerIndex = 0;
//        while (haystackIndex < haystackLength) {
//            if (needleChars[needleIndex] == haystackChars[haystackIndex]) {
//                haystackInnerIndex = haystackIndex;
//                while (needleIndex < needleLength) {
//                    if (haystackInnerIndex < haystackLength && needleChars[needleIndex] == haystackChars[haystackInnerIndex]) {
//                        needleIndex++;
//                        haystackInnerIndex++;
//                    } else {
//                        needleIndex = 0;
//                        haystackInnerIndex = 0;
//                        break;
//                    }
//                }
//                if(needleIndex == needleLength){
//                    return haystackIndex;
//                }
//                haystackIndex++;
//                continue;
//            }
//            haystackIndex++;
//        }
//        return -1;
    }

    //二分查找最经典的例子
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


    @Test
    public void test() {
//        int[] a = {-1, 0, 3, 5, 9, 12};
//        log.info("数组范围为：{}", subArrayRanges(a));
//        System.out.println(isPalindrome2(123321));
//        System.out.println(search(a, 9));
//        System.out.println(romanToInt("MCMXCIV"));
//        System.out.println(isValid("([)]"));

//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(4);
//
//        ListNode l2 = new ListNode(1);
//        l2.next = new ListNode(3);
//        l2.next.next = new ListNode(4);
//        getListNodeLoop(mergeTwoLists(l1, l2));

//        int[] a = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        System.out.println(removeDuplicates(a));
//        System.out.println(JsonMapper.toJson(a));

//        int[] a = {0, 1, 2, 2, 3, 0, 4, 2};
//        System.out.println(JsonMapper.toJson(this.removeElement(a, 2)));
//        System.out.println(JsonMapper.toJson(a));

//        System.out.println(strStr("mississippi", "issip"));
        int[] a = {1, 3, 5, 6};
        System.out.println(JSONValue.toJSONString(this.searchInsert(a, 2)));
        System.out.println(JSONValue.toJSONString(a));
//        ArrayList
    }


    static class Base {
        public static String s = "Super Class ";

        public Base() {
            System.out.println("1");
        }
    }

    public static class Derived extends AlgorithmTest.Base {
        public Derived() {
            super();
            System.out.println("2");
        }

        public static void main(String[] args) {
            Derived d = new Derived();
            System.out.println(s);
        }
    }

    public static class Test1 {
        public static String output = "";

        public static void foo(int i) {
            try {
                if (i == 1) {
                    throw new Exception();
                }
                output += "1";
            } catch (Exception e) {
                output += "2";
                return;
            } finally {
                output += "3";
            }
            output += "4";
        }

        public static void main(String[] args) {
            foo(0);
            foo(1);
            System.out.println(Test1.output);
        }
    }

    public static class Test2 {
        public static void main(String[] args) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; i >= 0; j--) {
                    if (i == j) break;
                    for (int k = 0; k >= 0; k++) {
                        if (j == k) break;
                        for (int m = 3; m >= 0; m--) {
                            if (k == m) break;
                            System.out.println(i + "" + j + "" + k + "" + m);
                        }
                    }
                }
            }
        }

    }

    public static class Test3 implements Runnable {
        public static CyclicBarrier barrier = new CyclicBarrier(3);

        public void run() {
            System.out.println("B");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread thread1 = new Thread(new Test3());
            Thread thread2 = new Thread(new Test3());
            thread1.start();
            thread2.start();
            System.out.println("A");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("C");
        }

    }
}
