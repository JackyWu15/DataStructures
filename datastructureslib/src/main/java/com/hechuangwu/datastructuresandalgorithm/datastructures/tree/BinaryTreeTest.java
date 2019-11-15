package com.hechuangwu.datastructuresandalgorithm.datastructures.tree;


/**
 * Created by cwh on 2019/11/2 0002.
 * 功能:
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode( 1, "宋江" );
        HeroNode node2 = new HeroNode( 2, "吴用" );
        HeroNode node3 = new HeroNode( 3, "卢俊义" );
        HeroNode node4 = new HeroNode( 4, "林冲" );
        HeroNode node5 = new HeroNode( 5, "关胜" );

        root.setLeft( node2 );
        root.setRight( node3 );
        node3.setRight( node4 );
        node3.setLeft( node5 );
        binaryTree.setRoot( root );

//        System.out.println( "遍历" );
//        binaryTree.preOrder();
//        binaryTree.midOrder();
//        binaryTree.postOrder();
//        binaryTree.preOrderSearch( 4 );
//
//        binaryTree.midOrderSearch( 5 );
//
//        binaryTree.preOrderSearch( 2 );


        binaryTree.delete( 3 );

        binaryTree.postOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //删除结点
    public void delete(int number) {
        if(root != null) {
            if(root.getNumber() == number) {
                root = null;
            } else {
                root.delete( number );
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println( "二叉树为空" );
        }
    }


    public void midOrder() {
        if (root != null) {
            root.midOrder();
        } else {
            System.out.println( "二叉树为空" );
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println( "二叉树为空" );
        }
    }

    public void preOrderSearch(int number){
        if (root != null) {
            HeroNode heroNode = root.preOrderSearch( number );
            System.out.println(heroNode);
        } else {
            System.out.println( "没有该元素" );
        }
    }
    public void midOrderSearch(int number){
        if (root != null) {
            HeroNode heroNode = root.midOrderSearch( number );
            System.out.println(heroNode);
        } else {
            System.out.println( "没有该元素" );
        }
    }

    public void postOrderSearch(int number){
        if (root != null) {
            HeroNode heroNode = root.postOrderSearch( number );
            System.out.println(heroNode);
        }else {
            System.out.println( "没有该元素" );
        }
    }
}

/**
 * 二叉树节点
 */
class HeroNode {
    int number;
    String name;
    HeroNode left;
    HeroNode right;

    public HeroNode(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        //输出父节点
        System.out.println( this );
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println( this );
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println( this );
    }

    public HeroNode preOrderSearch(int number){
        if(this.number==number){
           return this;
        }
        HeroNode temp  = null;
        if (this.left != null) {
            temp = left.preOrderSearch( number );
        }
        if(temp!=null){
            return temp;
        }
        if (this.right != null) {
           temp =  right.preOrderSearch( number );
        }
        return temp;
    }


    public HeroNode midOrderSearch(int number){
        HeroNode temp  = null;
        if (this.left != null) {
            temp = left.preOrderSearch( number );
        }
        if(temp!=null){
            return temp;
        }
        if(this.number==number){
            return this;
        }

        if (this.right != null) {
            temp =  right.preOrderSearch( number );
        }
        return temp;
    }
    public HeroNode postOrderSearch(int number) {
        HeroNode temp = null;
        if (this.left != null) {
            temp = left.preOrderSearch( number );
        }
        if (temp != null) {
            return temp;
        }
        if (this.right != null) {
            temp =  right.preOrderSearch( number );
        }
        if (temp != null) {
            return temp;
        }

        if(temp.number==this.number){
            return this;
        }
        return temp;
    }

    public void delete(int number){
        if(this.left!=null&&this.left.number==number){
            this.left = null;
            return;
        }
        if(this.right!=null&&this.right.number==number){
            this.right = null;
            return;
        }

        if(this.left!=null){
            this.left.delete( number );
        }

        if(this.right!=null){
            this.right.delete( number );
        }



    }
}




















