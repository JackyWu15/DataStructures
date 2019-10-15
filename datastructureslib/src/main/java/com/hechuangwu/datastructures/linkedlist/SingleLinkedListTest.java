package com.hechuangwu.datastructures.linkedlist;

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
        //显示元素
        singleLinkedList.showList();

        System.out.println();
        //更新元素
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update( newHeroNode );
        singleLinkedList.showList();

        System.out.println();
        //删除元素
        singleLinkedList.remove( 3 );
        singleLinkedList.showList();

        System.out.println();
        singleLinkedList.remove( newHeroNode );
        singleLinkedList.showList();

    }


}

class SingleLinkedList {
    private HeroNode head = new HeroNode( 0, "", "" );
    private HeroNode temp;

    public HeroNode getHead() {
        return head;
    }

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

            if (temp.index == heroNode.index) {
                exist = true;
                break;
            } else if (temp.next.index > heroNode.index) {
                break;
            }
            temp = temp.next;
        }
        if (exist) {
            System.out.printf( "准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.index );
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
            if(temp.next.index==heroNode.index){
                exit = true;
                break;
            }
            temp = temp.next;
        }

        if(!exit){
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.index);
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
            if(temp.next.index==heroNode.index){
                exit = true;
                break;
            }
            temp = temp.next;
        }

        if(!exit){
            System.out.printf("没有找到 编号 %d 的节点\n", heroNode.index);
            return;
        }
        temp.next = temp.next.next;
    }

    public void remove(int index){
        temp = head;
        boolean exit = false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.index==index){
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
     * 显示链表
     */
    public void showList() {
        if (head.next == null) {
            System.out.println( "链表为空" );
            return;
        }
        temp = head.next;
        while (true) {
            //是否链表末端
            if (temp == null) {
                break;
            }
            System.out.println( temp );
            temp = temp.next;
        }
    }
}


/**
 * 节点
 */
class HeroNode {
    public int index;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int index, String name, String nickName) {
        this.index = index;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
