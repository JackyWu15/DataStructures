package com.hechuangwu.datastructures.sparsearry;

/**
 * Created by cwh on 2019/10/14 0014.
 * 功能:稀疏数组
 */
public class SparseArray {
    private static final String TAG = "SparseArray";
    public static void main(String[] args) {
        System.out.println("--------------------------------二维数组转稀疏数组----------------------------------------------");
        //构造11*11的二维数组
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2]  = 1;
        chessArr1[2][3] = 2;

        for(int[] row:chessArr1){
            for(int data:row) {
                System.out.printf("%d\t", data );
            }
            System.out.println();
        }
        //遍历数组得到非0数据个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            int[] row = chessArr1[i];
            for (int j = 0; j <row.length ; j++) {
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("数据个数为："+sum);


        //创建对应的稀疏数组
        //行：非0个数+1，列：固定为3
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] =11;//原始行数
        sparseArr[0][1] =11;//原始列数
        sparseArr[0][2] =sum;//非0个数

        //给稀疏数组赋值
        int count =0;
        for (int i = 0; i < chessArr1.length; i++) {
            int[] row = chessArr1[i];
            for (int j = 0; j <row.length ; j++) {
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]= i;
                    sparseArr[count][1]= j;
                    sparseArr[count][2]= chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println("稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }


        System.out.println("--------------------------------稀疏数组转二维数组----------------------------------------------");
        //创建二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //给二维数组赋值
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出二维数组
        for(int[] row:chessArr2){
            for(int data:row) {
                System.out.printf("%d\t", data );
            }
            System.out.println();
        }

    }

}
