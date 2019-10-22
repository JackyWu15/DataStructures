package com.hechuangwu.datastructuresandalgorithm.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cwh on 2019/10/21 0021.
 * 功能: 查找算法
 */
public class SearchTest {
    public static void main(String[] args) {
        int array[] = {3, 20, 9, -1, 10, 10, 10, 10, 10, -2};
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        //        int value = seqSearch(arr, -11);
        //        int value = binarySearch( array, 0, array.length - 1, 10 );
        //        List<Integer> value = binarySearchAll( array, 0, array.length - 1, 10 );
//        int value = insertValueSearch( arr, 0, arr.length - 1, 89 );
        int value = fibonacciSearch( arr, 89 );
        System.out.println( value );
    }


    /**
     * 斐波那契查找：根据斐波那契规律，查找黄金分割点，查找数组需要有序
     */
    public static int fibonacciSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放mid值
        int f[] = fibonacci(); //获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf( a, f[k] );
        //实际上需求使用a数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        // 使用while来循环处理，找到我们的数 key
        while (low <= high) { // 只要这个条件满足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) { // 我们应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else { //找到
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    /**
     * 构建斐波那契数组
     */
    public static int[] fibonacci() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 20; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 插值查找：数组保证有序情况下，自适应中位数，当数据量很大，数组的值分布比较均匀，此查找速度比较快，而不均匀情况下，采用二分查找更好
     */
    public static int insertValueSearch(int[] array, int left, int right, int value) {
        System.out.println( "二分查找被调用~" );
        //没找到
        if (left > right || value < array[0] || value > array[array.length - 1]) {
            return -1;
        }
        //        int midIndex = (left + right) / 2;

        int midIndex = left + (right - left) * (value - array[left]) / (array[right] - array[left]);
        int midValue = array[midIndex];
        //在左边
        if (value < midValue) {
            return insertValueSearch( array, left, midIndex - 1, value );
        }
        //在右边
        else if (value > midValue) {
            return insertValueSearch( array, midIndex + 1, right, value );
        }
        //等于中间值
        else {
            return midIndex;
        }
    }

    /**
     * 二分查找添加所有值
     */
    public static List<Integer> binarySearchAll(int[] array, int left, int right, int value) {
        //没找到
        if (left > right) {
            return null;
        }
        int midIndex = (left + right) / 2;
        int midValue = array[midIndex];
        //在左边
        if (value < midValue) {
            return binarySearchAll( array, left, midIndex - 1, value );
        }
        //在右边
        else if (value > midValue) {
            return binarySearchAll( array, midIndex + 1, right, value );
        }
        //等于中间值
        else {
            ArrayList<Integer> indexList = new ArrayList();
            indexList.add( midIndex );
            int tempIndex = midIndex - 1;
            while (tempIndex >= 0 && array[tempIndex] == value) {
                indexList.add( tempIndex );
                tempIndex--;
            }


            tempIndex = midIndex + 1;
            while (tempIndex <= array.length - 1 && array[tempIndex] == value) {
                indexList.add( tempIndex );
                tempIndex++;
            }
            return indexList;
        }

    }

    /**
     * 二分查找：递归平分数组
     */
    public static int binarySearch(int[] array, int left, int right, int value) {
        //没找到
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) / 2;
        int midValue = array[midIndex];
        //在左边
        if (value < midValue) {
            return binarySearch( array, left, midIndex - 1, value );
        }
        //在右边
        else if (value > midValue) {
            return binarySearch( array, midIndex + 1, right, value );
        }
        //等于中间值
        else {
            return midIndex;
        }
    }

    /**
     * 线性查找：普通比对查找返回
     */
    public static int seqSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
