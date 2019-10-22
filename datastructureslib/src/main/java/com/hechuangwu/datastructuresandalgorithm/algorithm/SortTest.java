package com.hechuangwu.datastructuresandalgorithm.algorithm;

import java.util.Arrays;

/**
 * Created by cwh on 2019/10/17 0017.
 * 功能: 排序算法
 */
public class SortTest {
    public static void main(String[] args) {
        //        int array[] = {3, 20, 9, -1, 10, -2};
        int array[] = {3, 20, 9, 1, 10, 2};
        //测试优化
        //        int array[] = {1, 2, 3, 4, 5, 6};

//        int[] arr1 = new int[100000];//10w
//        for (int i = 0; i < arr1.length; i++) {
//            arr1[i] = (int) (Math.random() * 8000000);
//        }
        int[] arr2 = new int[1000000];//100w
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * 8000000);
        }

        //测试排序时间
        System.out.println( System.currentTimeMillis() );
        //以下100w扛不住
        //bubbleSort( arr1 );//20多秒
        //selectSort( arr1 );//3到4秒
        //insertSort( arr1 );//1到2秒


        //以下100w时间测试
//        shellSort( arr2 );//685毫秒
//        mergeSort(arr2, 0, arr2.length - 1, new int[arr2.length]);//273毫秒
        quickSort(arr2, 0, arr2.length-1);//203毫秒
//        radixSort( arr2 );//165毫秒，不能排负数,海量数据内存溢出
        System.out.println( System.currentTimeMillis() );

    }

    /**
     * 基数排序：通过位数进行分类，再重拍，经典的空间换时间方式，需要开辟多个数值，所以占用内存很大，海量数据排序时，容易造成 OutOfMemoryError
     */
    public static void radixSort(int[] array) {
        //1. 得到数组中最大的数的位数
        int max = array[0]; //假设第一数就是最大数
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();


        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        int[][] bucket = new int[10][array.length];
        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];
        //这里我们使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < array.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = array[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(即第k个一维数组), 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        array[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
                bucketElementCounts[k] = 0;

            }
            //            System.out.println("第"+(i+1)+"轮，对个位的排序处理 array =" + Arrays.toString(array));

        }

    }

    /**
     * 快速排序：中轴分开，从左边找小/大于中间值的和右边大/小中间值的进行交换，依次递归
     */
    public static void quickSort(int[] array, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = array[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让比pivot 值小放到左边
        //比pivot 值大放到右边
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (array[l] < pivot) {
                l++;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (array[r] > pivot) {
                r--;
            }
            //说明两边已交换完毕
            if (l >= r) {
                break;
            }

            //交换
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if (array[l] == pivot) {
                r--;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (array[r] == pivot) {
                l++;
            }
        }

        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //左边的进行递归排序
        if (left < r) {
            quickSort( array, left, r );
        }
        //右边进行递归排序
        if (right > l) {
            quickSort( array, l, right );
        }
    }

    /**
     * 归并排序：将数组从中间分开，分别进行分解，再合并，通过对比左右的元素，填充到temp数值中
     */
    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort( array, left, mid, temp );
            //向右递归进行分解
            mergeSort( array, mid + 1, right, temp );
            //合并
            merge( array, left, mid, right, temp );

        }
    }

    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; //初始化j, 右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到 temp数组
            //然后 t++, i++
            if (array[i] <= array[j]) {
                temp[t] = array[i];
                t += 1;
                i += 1;
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = array[j];
                t += 1;
                j += 1;
            }
        }

        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = array[i];
            t += 1;
            i += 1;
        }

        while (j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = array[j];
            t += 1;
            j += 1;
        }


        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left; //
        //第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0  right = 7
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

    /**
     * 希尔排序：对插入排序的改进
     */
    public static void shellSort(int[] array) {
        // 增量gap, 并逐步的缩小增量
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[j];
                if (array[j] < array[j - gap]) {
                    while (j - gap >= 0 && temp < array[j - gap]) {
                        //移动
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    array[j] = temp;
                }

            }
        }
    }

    /**
     * 插入排序：从第2个开始取，和前面的进行比较，如果大/小于就插在其前面
     */
    public static void insertSort(int[] array) {
        int insertValue = 0;
        int insertIndex = 0;
        for (int i = 1; i < array.length; i++) {
            insertIndex = i - 1;//插入的角标
            insertValue = array[i];//要插入的值
            //比前一个数据小
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];//将大的往前挪
                insertIndex--;//往后移动指针，如果指针到达0角标，或没有比它小的了，就跳出循环
            }

            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertValue;//将值插入
            }
            //            System.out.println( "第" + i + "轮插入" );
            //            System.out.println( Arrays.toString( array ) );
        }

    }


    /**
     * 选择排序：假定第1个最大/小，和每个比较进行交换，角标依次递增
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            //取到相对最小/大
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            //            System.out.printf( "第%d次排序", i + 1 );
            //            System.out.println( Arrays.toString( array ) );
            //将取到的值和一开始的调换
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }

    /**
     * 冒泡排序：和下一个比较，每次循环会确定一个最大/小的往前排，所以循环次数递减
     */
    public static void bubbleSort(int[] array) {
        int temp;
        boolean finish = true;//优化，是否提前排序完成
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    finish = false;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.printf( "第%d次排序", i + 1 );
            System.out.println( Arrays.toString( array ) );
            if (finish) {
                break;
            } else {
                finish = true;
            }
        }
    }


}
