package com.hechuangwu.datastructuresandalgorithm.datastructures.hashtable;

import java.util.Scanner;

/**
 * Created by cwh on 2019/11/2 0002.
 * 功能: 哈希表
 */
public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable( 7 );
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.get(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
    /**
     * 哈希表
     */
     class HashTable{
       private EmpLinkedList[] mEmpLinkedLists;
        private int capacity;

         HashTable(int capacity) {
             this.capacity = capacity;
            mEmpLinkedLists = new EmpLinkedList[capacity];
             for (int i = 0; i <capacity ; i++) {
                 mEmpLinkedLists[i] = new EmpLinkedList();
             }
        }

        /**
         * 添加元素
         * @param emp 元素
         */
        void add(Emp emp){
            int insertIndex = hashInsert( emp.id );
            mEmpLinkedLists[insertIndex].add( emp );
        }

       void get(int id){
            int insertIndex = hashInsert( id );
            Emp emp = mEmpLinkedLists[insertIndex].get( id );
            if(emp==null){
                System.out.println("在哈希表中，没有找到该雇员~");
            }else{
                System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (insertIndex + 1), id);
            }
        }
        /**
         * 散列方式，计算插入哪条链表
         * @param id id
         * @return 角标
         */
        private int hashInsert(int id){
            return id%capacity;
        }

        /**
         * 遍历
         */
        void list(){
            for (int i = 0; i <mEmpLinkedLists.length ; i++) {
               mEmpLinkedLists[i].list( i );
            }
        }


    }
    /**
     * 链表节点
     */
    class Emp {
        int id;
        private String name;
         Emp next;

         Emp(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Emp{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * 链表
     */
    class EmpLinkedList {
        private Emp head;

        /**
         *  添加元素
         * @param emp 元素
         */
        void add(Emp emp){
           if(head==null){
                head = emp;
                return;
           }
           Emp temp = head;
           while (temp.next!=null){
              temp = temp.next;
           }
           temp.next = emp;
        }

        Emp get(int id){
           if(head==null){
               return null;
           }
           Emp temp = head;
          while (true){
              if(temp.id==id){
                  return temp;
              }
              if(temp.next ==null){
                    return null;
              }
              temp = temp.next;
          }

        }
        /**
         * 遍历
         */
        void list(int insertIndex){
           if(head==null){
               System.out.println("第"+insertIndex+"条链表为空");
                return;
           }
            Emp temp = head;
           while (true){
               System.out.println("第"+insertIndex+"条链表为"+temp);
               if(temp.next==null){
                   break;
               }
               temp = temp.next;
           }

        }

    }
