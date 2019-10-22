package com.hechuangwu.datastructuresandalgorithm.datastructures.queue;

import java.util.Scanner;

/**
 * Created by cwh on 2019/10/15 0015.
 * 功能: 环形队列,队列也可以用链表实现
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue( 3 );
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.get();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.getFirst();
                        System.out.printf("队列头的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }


}

class ArrayQueue {
    private int capacity;//最大容量
    private int front;//第一个元素
    private int rear;//最后一个角标
    private int[] arr;//存放数据的数组

    //构造队列
    ArrayQueue(int capacity) {
        //+1预留一个空间作为判断
        this.capacity = capacity+1;
        arr = new int[this.capacity ];
    }

    //队列是否满
    private boolean isFull() {
        return (rear+1)%capacity ==front;
    }

    //队列是否空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据
    public void add(int element) {
        if (isFull()) {
            System.out.println( "队列已满" );
            return;
        }
        arr[rear] = element;//存值
        rear = (rear+1)%capacity;//后移
    }

    //获取数据
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException( "队列空，不能取数据" );
        }
        int value = arr[front];
        front = (front+1)%capacity;
        return value;
    }

    //获取第一个数据
    public int getFirst() {
        if (isEmpty()) {
            throw new RuntimeException( "队列空的，没有数据~~" );
        }
        return arr[front ];
    }

    //显示数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println( "队列空的，没有数据~~" );
            return;
        }

        for (int i = front; i < front+size(); i++) {
            System.out.printf( "arr[%d]=%d\n", i%capacity, arr[i%capacity] );
        }
    }

    //队列有效数据个数
    public int size(){
        return (rear+capacity-front)%capacity;
    }


}

