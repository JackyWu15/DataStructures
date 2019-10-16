package com.hechuangwu.datastructures.linkedlist;

import java.util.Stack;

/**
 * Created by cwh on 2019/10/15 0015.
 * 功能: 单向链表
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        //构造节点
        HeroNode hero1 = new HeroNode( 1, "宋江", "及时雨" );
        HeroNode hero2 = new HeroNode( 2, "卢俊义", "玉麒麟" );
        HeroNode hero3 = new HeroNode( 3, "吴用", "智多星" );
        HeroNode hero4 = new HeroNode( 4, "林冲", "豹子头" );

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //按插入顺序
        //        singleLinkedList.add(hero1);
        //        singleLinkedList.add(hero4);
        //        singleLinkedList.add(hero2);
        //        singleLinkedList.add(hero3);

        //按角标顺序
        singleLinkedList.addIndex( hero1 );
        singleLinkedList.addIndex( hero4 );
        singleLinkedList.addIndex( hero2 );
        singleLinkedList.addIndex( hero3 );


        System.out.println("显示元素:");
        singleLinkedList.showList();


        System.out.println("更新元素:");
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update( newHeroNode );
        singleLinkedList.showList();

        System.out.println("链表反转：");
        singleLinkedList.reverse();
        singleLinkedList.showList();

        System.out.println("打印反转：");
        singleLinkedList.reversePrint();


        System.out.println("角标删除元素:");
        singleLinkedList.remove( 3 );
        singleLinkedList.showList();

        System.out.println("节点删除元素：");
        singleLinkedList.remove( newHeroNode );
        singleLinkedList.showList();

        System.out.println("链表有效个数:");
        System.out.println( singleLinkedList.size() );

        System.out.println("根据角标倒数查找：");
        HeroNode lastIndexNode = singleLinkedList.findLastIndexNode( 1 );
        System.out.println(lastIndexNode);



    }


}

class SingleLinkedList {
    private HeroNode head = new HeroNode( 0, "", "" );
    private HeroNode temp;

    /**
     *尾插法
     */
    public void add(HeroNode heroNode) {
        temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 根据角标排序
     */
    public void addIndex(HeroNode heroNode) {
        temp = head;
        boolean exist = false;//节点是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.number == heroNode.number) {
                exist = true;
                break;
            } else if (temp.next.number > heroNode.number) {
                break;
            }
            temp = temp.next;
        }
        if (exist) {
            System.out.printf( "准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.number );
            return;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 根据角标修改节点
     * @param heroNode
     */
    public void update(HeroNode heroNode){
        if(head.next==null){
            System.out.println("链表为空~");
            return;
        }

        temp = head;
        boolean exit = false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.number==heroNode.number){
                exit = true;
                break;
            }
            temp = temp.next;
        }

        if(!exit){
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.number);
            return;
        }
        heroNode.next = temp.next.next;
        temp.next = heroNode;
    }

    /**
     * 根据元素删除
     */
    public void remove(HeroNode heroNode){
        temp = head;
        boolean exit = false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.number==heroNode.number){
                exit = true;
                break;
            }
            temp = temp.next;
        }

        if(!exit){
            System.out.printf("没有找到 编号 %d 的节点\n", heroNode.number);
            return;
        }
        temp.next = temp.next.next;
    }
    /**
     * 根据角标删除
     */
    public void remove(int index){
        temp = head;
        boolean exit = false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.number==index){
                exit = true;
                break;
            }
            temp = temp.next;
        }

        if(!exit){
            System.out.printf("没有找到 编号 %d 的节点\n", index);
            return;
        }
        temp.next = temp.next.next;
    }

    /**
     * @return 有效个数
     */
    public int size(){
        int length = 0;
        if(head.next==null){
            return length;
        }
        temp = head.next;
        while (temp!=null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 角标倒数查找元素
     * @param index 角标
     * @return 元素
     */
    public HeroNode findLastIndexNode(int index){
        if(head.next==null){
            return null;
        }
        int size = size();
        if(index<0||index>=size){
            return null;
        }
        HeroNode indexNode = head.next;
        for (int i = 0; i < size-1-index; i++) {
            indexNode = indexNode.next;
        }
        return indexNode;
    }

    /**
     * 改变原有顺序，反转链表
     */
    public void reverse(){
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode reverseHead = new HeroNode( 0, "", "" );//新的链表头
        HeroNode next = null;
        temp = head.next;
        while (temp!=null){
            next = temp.next;//先保存下一个节点，移动时使用，否则遍历就断开了
            temp.next = reverseHead.next;//当前要取出的节点指向第一个节点
            reverseHead.next = temp;//头节点指向当前节点
            temp = next;//移动到原链表下个节点
        }
        head = reverseHead;
    }

    /**
     * 不改变原有顺序，反转链表显示
     */

    public void reversePrint(){
        if(head.next==null||head.next.next==null){
            return;
        }

        Stack<HeroNode> stack = new Stack<>();

        temp = head.next;
        while (temp!=null){
            stack.push( temp );
            temp = temp.next;
        }

       while (stack.size()>0){
           System.out.println(stack.pop());
       }
    }

    /**
     * 显示链表
     */
    public void showList() {
        if (head.next == null) {
            System.out.println( "链表为空" );
            return;
        }
        temp = head.next;
        while (temp != null) {
            //是否链表末端
            System.out.println( temp );
            temp = temp.next;
        }
    }
}


/**
 * 节点
 */
class HeroNode {
    int number;
    private String name;
    private String nickName;
    HeroNode next;

    HeroNode(int number, String name, String nickName) {
        this.number = number;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
