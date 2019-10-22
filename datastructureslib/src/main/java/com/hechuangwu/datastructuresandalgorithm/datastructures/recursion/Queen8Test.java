package com.hechuangwu.datastructuresandalgorithm.datastructures.recursion;

/**
 * Created by cwh on 2019/10/17 0017.
 * 功能:八皇后问题
 */
public class Queen8Test {
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.place();
        System.out.printf("一共有%d解法，", queen8.count);
        System.out.printf("一共判断冲突的次数为%d次", queen8.conflict);
    }
}

class Queen8 {
    int max = 8;//皇后数量
    int[] array = new int[max];//角标表示行，数据表示列
    int count = 0;//解决方案数量
    int conflict = 0;//冲突判断了几次

    public void place(){
        check( 0 );
    }
    public void check(int n){
        //第八个皇后摆放完毕
        if(n==max){
            print();
            return;
        }
        //如果冲突列递增，列每递增1次，将进入递归，要么达到第八个皇后摆放完毕并打印，要么达不到，进行回溯
        //假设当前未到第8个皇后，但递增到最大还是冲突，那么说明这一次递归结束，将回溯到上一次的递归，将列递增，继续递归
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if(!conflict(n)){
                check( n+1 );//递归摆放皇后
            }
        }
    }

    /**
     * 判断是否当前摆放的皇后与之前的冲突
     * @param n 当前要摆放第几个换后
     */
    public boolean conflict(int n){
        conflict++;
        for (int i = 0; i < n ; i++) {
            if(array[i]==array[n]//列冲突
                    ||Math.abs( n-i )==Math.abs( array[n]-array[i] ))//斜线冲突:行坐标等于列坐标
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 打印皇后摆放位置
     */
    public void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}