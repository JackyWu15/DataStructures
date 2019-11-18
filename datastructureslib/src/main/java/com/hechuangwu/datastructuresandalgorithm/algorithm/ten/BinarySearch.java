package com.hechuangwu.datastructuresandalgorithm.algorithm.ten;

/**
 * Created by cwh on 2019/11/18 0018.
 * 功能: 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 100);
        System.out.println("index=" + index);
    }


    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length -1;
        while (left<=right){
            int mid = (left + right) / 2;
            if(array[mid]==target){
                return mid;
            }else if(array[mid]>target){
                right = mid-1;
            }else {
                left = mid +1;
            }
        }
        return -1;
    }

}
