package com.hechuangwu.datastructuresandalgorithm.datastructures.tree;

/**
 * Created by cwh on 2019/11/4 0004.
 * 功能: 数组二叉树遍历
 */
public class ArrayBinaryTreeTest {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        //创建一个 ArrBinaryTree
        ArrayBinaryTree arrBinaryTree = new ArrayBinaryTree(arr);
        arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
    }
}

class ArrayBinaryTree{

    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }
    public void preOrder() {
        this.preOrder(0);
    }

    public void preOrder(int index){
        if(array==null||array.length==0){
            System.out.println("数组为空");
        }
        System.out.println("当前元素为"+array[index]);
        //左递归
        if(index*2+1<array.length){
            preOrder( index*2+1 );
        }

        //右递归
        if(index*2+2<array.length){
            preOrder( index*2+2 );
        }
    }
}
