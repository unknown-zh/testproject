package com.springbatch.springbatchtest.test;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.h2.util.CurrentTimestamp;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Random;

import com.springbatch.springbatchtest.test.StructureTest.ListNode;
import com.springbatch.springbatchtest.test.StructureTest.DeptTree;

/**
 * @ClassName SortedFunction.java
 * @PackagePath com.springbatch.springbatchtest.test
 * @Author Dr. zhang
 * @CreateTime 2022/3/14
 * @Description 基础排序算法联系
 */
public class SortFunction {

    /**
     * @Description: 直接排序，
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void straightSelectSortForArray() {

    }

    public void straightSelectSortForLinked() {

    }

    /**
     * @Description: 冒泡排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public static void bubbleSortForArrayByZhmt(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        else if (n > 10000) {
            bubbleSortForArrayByWeb(nums);
            return;
        }
        int arrayIndex = 0;
        //冒泡
        while (arrayIndex < n - 1) {
            if (nums[arrayIndex] > nums[arrayIndex + 1]) {
                nums[arrayIndex] = nums[arrayIndex] + nums[arrayIndex + 1];
                nums[arrayIndex + 1] = nums[arrayIndex] - nums[arrayIndex + 1];
                nums[arrayIndex] = nums[arrayIndex] - nums[arrayIndex + 1];
            }
            arrayIndex++;
        }
        //检查
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                bubbleSortForArrayByZhmt(nums);
                break;
            }
        }
    }

    public static void bubbleSortForArrayByWeb(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) { // 交换
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
    }


    public void bubbleSortForLinkedByZhmt(ListNode listNode) {
        if (listNode.next == null) return;
        ListNode preNode;
        ListNode curNode;
        ListNode nextNode;

        while(listNode.next != null){
            while(listNode.next != null){

            }
        }

    }

    //单链表冒泡排序
    public ListNode bubbleSortListForLinkedByWeb(ListNode node) {
        System.out.println("blue start...");
        //当前节点
        ListNode cur = node.next;
        //下一个节点
        ListNode next = cur.next;
        //前一个节点,两节点n1,n2交换位置时需要前一个节点的next指向n2节点
        ListNode upperNode = node;
        //一次排序的最后一个节点
        ListNode lastNode = null;
        int i = 1;
        //当第二个节点为当次排序的最后一个节点时，整体排序结束
        while (node.next != lastNode) {
            //当下一个节点为一次次排序的最后一个节点时，本次排序结束。
            if (next == lastNode) {
                lastNode = cur;
                cur = node.next;
                next = cur.next;
                upperNode = node;
            } else if (next.val < cur.val) {
                cur.next = next.next;
                next.next = cur;
                upperNode.next = next;
                upperNode = next;
            } else {
                upperNode = cur;
                cur = cur.next;
            }
            next = cur.next;
            System.out.println("第" + i++ + "次排列结果:");
            StructureTest structureTest = new StructureTest();
            structureTest.printList(node);
        }
        System.out.println("blue end...");
        return node;
    }


    /**
     * @Description: 选择排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void selectionSort() {

    }


    /**
     * @Description: 希尔排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void shellSort() {

    }


    /**
     * @Description: 归并排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void mergeSort() {

    }


    /**
     * @Description: 快速排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void quickSort() {

    }


    /**
     * @Description: 桶排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void bucketSort() {

    }


    /**
     * @Description: 计数排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void countingSort() {

    }


    /**
     * @Description: 基数排序
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void radixSort() {

    }


    /**
     * @Description: 二分查找（折半查找）
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public int binarySearch(int[] nums, int target) {
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


    /**
     * @Description: 跳表查询, 联系跳表是如何查找的
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void skipListSearch() {

    }


    @Test
    public void testForArray() {
        StructureTest structureTest = new StructureTest();
//        int[] nums1 = structureTest.getRandomArrays(20000);
//        Long time1 = System.currentTimeMillis();
//        bubbleSortForArrayByWeb(nums1);
//        Long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));
        //递归，数据太多的时候，容易栈溢出
//        int[] nums2 = structureTest.getRandomArrays(20000);
//        Long time3 = System.currentTimeMillis();
//        bubbleSortForArrayByZhmt(nums2);
//        Long time4 = System.currentTimeMillis();
//        System.out.println(time4 - time3);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums2)));
    }


    @Test
    public void testForLinked() {
        StructureTest structureTest = new StructureTest();
        ListNode singleLinked = structureTest.getRandomSingleLinked(null, 3);
        bubbleSortListForLinkedByWeb(singleLinked);

    }


}
