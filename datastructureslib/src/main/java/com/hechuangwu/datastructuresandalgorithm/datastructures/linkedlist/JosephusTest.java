package com.hechuangwu.datastructuresandalgorithm.datastructures.linkedlist;

/**
 * Created by cwh on 2019/10/16 0016.
 * 功能:
 */
public class JosephusTest {
    public static void main(String[] args) {
        JosephusLinkedList josephusLinkedList = new JosephusLinkedList();
        int childrenSize = 5;
        josephusLinkedList.addChildren( childrenSize );
        josephusLinkedList.showChildren();

        josephusLinkedList.popChildren( 1,2,childrenSize );
    }


}

class JosephusLinkedList{
    private Child first = null;
    private Child temp = null;

    /**
     * 构造环形
     * @param size 小孩数量
     */
    public void addChildren(int size){
        if(size<1){
            System.out.println("数量不正确");
            return;
        }

        for (int i = 1; i <= size; i++) {
            Child child = new Child( i );
            if(i==1){
                first = child;
                first.next = first;
                temp = first;
            }else {
                temp.next = child;
                child.next = first;
                temp = child;
            }
        }
    }

    /**
     * 显示小孩编号
     */
    public void showChildren(){
        if(first==null){
            System.out.println("没有小孩");
            return;
        }
        temp = first;
        while (true){
            System.out.println(temp.number);
            if(temp.next==first){
                break;
            }
            temp = temp.next;
        }
    }

    /**
     *  小孩出圈
     * @param start 从第几个小孩开始数数
     * @param count 数几下
     * @param size 最初小孩数量
     */
    public void popChildren(int start, int count, int size){
        if(first==null||start<1||start>size){
            System.out.println("参数输入有误， 请重新输入");
            return;
        }

        temp = first;
        //temp移动到first的上一个节点
        while (temp.next!=first){
            temp = temp.next;
        }

        //tem和first移动报数的位置start-1(自身报数1次）
        for (int i = 0; i < start-1; i++) {
            temp = temp.next;
            first = first.next;
        }

        //相等说明只剩一个节点
        while (temp != first) {
            //移动到被数到的节点
            for (int i = 0; i < count - 1; i++) {
                temp = temp.next;
                first = first.next;
            }
            //出圈被数到的节点
            System.out.printf("出圈的小孩编号%d \n", first.number);
            first = first.next;
            temp.next = first;
        }

        System.out.printf("最后留在圈中的小孩编号%d \n", first.number);
    }
}

class Child{
     int number;
     Child next;

     Child(int number) {
        this.number = number;
    }

}
