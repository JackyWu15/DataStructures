package com.hechuangwu.datastructuresandalgorithm.algorithm;

/**
 * 内部排序：将需要处理的数据都加载到内存中，进行排序
 * 1，插入排序：直接排序
 *              希尔排序
 * 2，选择排序：简单选择排序
 *              希尔排序
 * 3，交换排序：冒泡排序
 *              快速排序
 * 4，归并排序：
 * 5，基数排序：
 * 外部排序：数据量过大，借助外部存储排序，比如文件等
 *
 * 算法时间复杂度由小到大依次为：常数阶Ο(1)＜对数阶Ο(log2n)＜线性阶Ο(n)＜线性对数阶Ο(nlog2n)＜平方阶Ο(n2)＜立方阶Ο(n3)＜k次方阶Ο(nk) ＜指数阶Ο(2n)
 *
 *
 * Created by cwh on 2019/10/17 0017.
 * 功能:算法
 */
public class Algorithm {
    public static void main(String[] args) {
        //常数阶Ο(1)：只要是没有循环等复杂结构，无论代码有多长，单个变量多大，即使有几万几十万行，都可以用O(1)来表示它的时间复杂度
        int i = 1;
        long j = 200000000000000000L;
        i++;
        ++j;
        long m = i + j;

        //对数阶Ο(log2n)：假定执行次数为x，那下面程序次数为，2的x次方 = n，x = log2n
        int k = 1;
        int n = 1024;
        while (k < n) {
            k = k * 2;
        }

        //线性阶Ο(n): 直接和n相关，n为几便执行几次
        for (int l = 0; l < n; l++) {
            j = i;
            j++;
        }

        //线性对数阶Ο(nlog2n)：在线性阶中套入对数阶
        for (int l = 0; l < n; l++) {
            i = 1;
            while (i < n) {
                i = i * 2;
            }
        }


        //平方阶Ο(n2)＜立方阶Ο(n3)＜k次方阶Ο(nk): 双层for循环，n*n = n2 或n*m，即Ο(n*m)
        for (int l = 0; l < n; l++) {
            for (int o = 0; o < n; o++) {
                j = i;
                j++;
            }
        }




    }
}
