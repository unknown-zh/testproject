package com.springbatch.springbatchtest.test;

import com.springbatch.springbatchtest.test.StructureTest.ListNode;
import net.minidev.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

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
     * @Param: array 一个数组
     * @return: void 原地排序可以直接在外面获取
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void straightSelectSortForArray(int[] array) {
        Assert.assertFalse(array == null);
        //小于俩个不需要排序
        int length = array.length;
        if (length < 2) return;
        //开始比较排序区间和未排序区间
        for (int i = 1; i < length; i++) {
            int value = array[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                //这里不能有等于，有等于就不是稳定的排序算法了
                if (array[j] > value) {
                    array[j + 1] = array[j];  // 数据移动
                } else {
                    break;
                }
            }
            array[j + 1] = value; // 插入数据
        }
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
        Assert.assertFalse(nums == null);
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


    public ListNode bubbleSortForLinkedByZhmt(ListNode listNode) {
        if (listNode.next == null) return null;
        ListNode head = listNode;

        while (listNode.next != null) {
            boolean isFlag = false;
            while (listNode.next != null) {
                if (listNode.val > listNode.next.val) {
                    ListNode temp = listNode.next;
                    listNode.next = temp.next;
                    temp.next = listNode;
                    listNode = temp;
                    isFlag = true;
                }
                if (listNode.next == null) return head;
                ListNode temp = listNode;
                if (head.next.val == temp.val) {
                    head = temp;
                }
                listNode = listNode.next;
            }
            if (isFlag == false) return head;
        }
        return head;
    }

    //单链表冒泡排序
    public void bubbleSortListForLinkedByWeb(ListNode node) {
        ListNode cur = node;
        ListNode tmp = null;
        while (cur != tmp) {
            while (cur.next != tmp) {
                if (cur.val > cur.next.val) {
                    int temp = cur.val;
                    cur.val = cur.next.val;
                    cur.next.val = temp;
                }
                cur = cur.next;
            }
            tmp = cur;
            cur = node;
        }
    }


    /**
     * @Description: 选择排序
     * <p>
     * 排序思想：分成两个部分：排序区间和未排序区间，讲
     * @Param: int[] array
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/14
     */
    public void selectionSort(int[] array) {
        //小于俩个不需要排序
        int length = array.length;
        if (length < 2) return;
        int min;

        for (int i = 0; i < length; i++) {
            min = array[i];
            for (int j = length - 1; j >= 0; j--) {
                if (array[j] > min) {
                    array[i] = array[j];
                    array[j] = min;
                }
            }
        }
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
    public static void mergeSort1(Object[] src, Object[] dest, int low, int high, int off) {
        //分治思想，先分解问题，再合并问题
        int length = high - low;

        // 递归终止条件
        if (high >= low) return;

        int destLow = low;
        int destHigh = high;
        low += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort1(dest, src, low, mid, -off);
        mergeSort1(dest, src, mid, high, -off);

        if (((Comparable) src[mid - 1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable) src[p]).compareTo(src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    /**
     * Tuning parameter: list size at or below which insertion sort will be
     * used in preference to mergesort.
     * To be removed in a future release.
     */
    private static final int INSERTIONSORT_THRESHOLD = 7;

    /**
     * Src is the source array that starts at index 0
     * Dest is the (possibly larger) array destination with a possible offset
     * low is the index in dest to start sorting
     * high is the end index in dest to end sorting
     * off is the offset to generate corresponding low, high in src
     * To be removed in a future release.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void mergeSort(int[] src, int[] dest, int low, int high, int off) {
        Assert.assertFalse(src == null || dest == null);
        int length = high - low;

        //这是个插入排序，当元素小于7个就进行插入排序
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--) {
                    int t = dest[j];
                    dest[j] = dest[j - 1];
                    dest[j - 1] = t;
                }
            return;
        }

        int destLow = low;
        int destHigh = high;
        low += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && src[p] <=src[q])
            dest[i] = src[p++];
            else
            dest[i] = src[q++];
        }
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
     * 快速排序
     * @param array
     */
    public static void quickSort(int[] array) {
        int len;
        if(array == null
                || (len = array.length) == 0
                || len == 1) {
            return ;
        }
        sort(array, 0, len - 1);
    }

    /**
     * 快排核心算法，递归实现
     * @param array
     * @param left
     * @param right
     */
    public static void sort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    /**
     * Sorts the specified portion of the specified array using a binary
     * insertion sort.  This is the best method for sorting small numbers
     * of elements.  It requires O(n log n) compares, but O(n^2) data
     * movement (worst case).
     * <p>
     * If the initial part of the specified range is already sorted,
     * this method can take advantage of it: the method assumes that the
     * elements from index {@code lo}, inclusive, to {@code start},
     * exclusive are already sorted.
     *
     * @param a     the array in which a range is to be sorted
     * @param lo    the index of the first element in the range to be sorted
     * @param hi    the index after the last element in the range to be sorted
     * @param start the index of the first element in the range that is
     *              not already known to be sorted ({@code lo <= start <= hi})
     */
    @SuppressWarnings({"fallthrough", "rawtypes", "unchecked"})
    private static void binarySort(Object[] a, int lo, int hi, int start) {
        assert lo <= start && start <= hi;
        if (start == lo)
            start++;
        for (; start < hi; start++) {
            Comparable pivot = (Comparable) a[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            int left = lo;
            int right = start;
            assert left <= right;
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot.compareTo(a[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left;  // The number of elements to move
            // Switch is just an optimization for arraycopy in default case
            switch (n) {
                case 2:
                    a[left + 2] = a[left + 1];
                case 1:
                    a[left + 1] = a[left];
                    break;
                default:
                    System.arraycopy(a, left, a, left + 1, n);
            }
            a[left] = pivot;
        }
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

//        int[] nums1 = structureTest.getRandomArrays(5, 0);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));
//        straightSelectSortForArray(nums1);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));

//        int[] nums1 = structureTest.getRandomArrays(5, 0);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));
//        selectionSort(nums1);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));
//        int[] nums1 = structureTest.getRandomArrays(50, 0);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));
//        mergeSort(nums1, new int[nums1.length], 0, nums1.length, 0);
//        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));


        int[] nums1 = structureTest.getRandomArrays(50, 0);
        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));
        quickSort(nums1);
        System.out.println(JSONArray.toJSONString(Collections.singletonList(nums1)));
        straightSelectSortForArray(null);

    }


    @Test
    public void testForLinked() {
        StructureTest structureTest = new StructureTest();

        ListNode singleLinked = structureTest.getRandomSingleLinked(null, 5, 0);
        structureTest.getListNodeLoop(singleLinked);
        bubbleSortListForLinkedByWeb(singleLinked);
//        ListNode listNode = bubbleSortForLinkedByZhmt(singleLinked);
        structureTest.getListNodeLoop(singleLinked);

    }


}
