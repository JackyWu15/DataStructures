package com.hechuangwu.datastructuresandalgorithm.datastructures.linkedlist;

/**
 * Created by cwh on 2019/10/16 0016.
 * 功能: 双向链表
 */
public class DoubleLinkedListTest {

    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.showList();

        System.out.println("修改链表");
        HeroNode2 newHeroNode = new HeroNode2(2, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.showList();

        System.out.println("删除元素");
        HeroNode2 removeNode = new HeroNode2(4   , "公孙胜", "入云龙");
        doubleLinkedList.remove( removeNode );
        doubleLinkedList.showList();
    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2( 0, "", "" );
    private HeroNode2 temp;

    public void add(HeroNode2 heroNode2){
        temp = head;
        while (temp.next!=null){
            temp = temp.next;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;

    }

    public void update(HeroNode2 heroNode2){
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
            if(temp.next.number==heroNode2.number){
                exit = true;
                break;
            }
            temp = temp.next;
        }

        if(!exit){
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode2.number);
            return;
        }

        if(temp.next.next!=null){
            heroNode2.next = temp.next.next;
            temp.next.next.pre = heroNode2;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;

    }

    /**
     * 根据元素删除
     */
    public void remove(HeroNode2 heroNode2){
        temp = head;
        boolean exit = false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.number==heroNode2.number){
                exit = true;
                break;
            }
            temp = temp.next;
        }

        if(!exit){
            System.out.printf("没有找到 编号 %d 的节点\n", heroNode2.number);
            return;
        }

        temp.next = temp.next.next;
        if(temp.next!=null){
            temp = temp.next.pre;
        }
    }

    public void showList(){
        if (head.next == null) {
            System.out.println( "链表为空" );
            return;
        }
        temp = head.next;
        //是否链表末端
        while (temp != null) {
            System.out.println( temp );
            temp = temp.next;
        }

    }
}

class HeroNode2{
    int number;
    private String name;
    private String nickName;
    HeroNode2 next;
    HeroNode2 pre;
    HeroNode2(int number, String name, String nickName) {
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
