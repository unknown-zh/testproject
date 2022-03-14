package com.springbatch.springbatchtest.test;

import java.lang.reflect.Array;

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
    public void bubbleSort() {

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

    //数据输入主方法
    public static void main(String[] args) {

    }
}
