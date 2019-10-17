package com.hechuangwu.datastructures.recursion;

/**
 * Created by cwh on 2019/10/17 0017.
 * 功能: 迷宫递归问题
 */
public class MazeTest {
    public static void main(String[] args) {
        //创建地图
        int[][] map = new int[8][7];
        //最外圈围成墙设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[4][3] = 1;
        map[4][4] = 1;
//        map[4][5] = 1;

        System.out.println("地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //开始走迷宫
        setWay(map, 1, 1);

        System.out.println("小球走过，并标识过的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 迷宫策略(0可以走1墙2走过3所有方向都走不通
     * @param map 地图
     * @param i 上下方向
     * @param j 左右方向
     * @return 是否走通
     */
    public static boolean setWay(int[][] map,int i,int j){
        //走到这个点，说明出了迷宫
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j] = 2;
                //判断下一个位置是否可走，按下-右-上-左 策略走
                if(setWay( map,i+1,j )){
                    return true;
                }else if(setWay( map,i,j+1 )){
                    return true;
                }else if(setWay( map,i-1,j )){
                    return true;
                }else if(setWay( map,i,j-1 )){
                    return true;
                }else {
                    map[i][j]=3;
                    return false;
                }
            }else {
                return false;
            }
        }

    }


}


