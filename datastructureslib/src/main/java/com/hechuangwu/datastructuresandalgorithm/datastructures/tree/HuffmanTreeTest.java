package com.hechuangwu.datastructuresandalgorithm.datastructures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cwh on 2019/11/14 0014.
 * 功能: 哈夫曼树：(wpl)从根节点开始，树的带权路劲长度最小
 */
public class HuffmanTreeTest {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node root = HuffmanTree.createHuffmanTree( arr );
        root.preOrder();

    }



}


class HuffmanTree{
    public static Node createHuffmanTree(int[] array){
        //将元素构成Node
        List<Node> nodes = new ArrayList();
        for (int value : array) {
            nodes.add(new Node(value));
        }


        while (nodes.size()>1){
            Collections.sort( nodes );
            //取权值最小的节点
            Node leftNode = nodes.get( 0 );
            Node rightNode = nodes.get( 1 );
            //构建新二叉树
            Node parent = new Node( leftNode.value + rightNode.value );
            parent.left = leftNode;
            parent.right = rightNode;

            //删除数组元素
            nodes.remove( leftNode );
            nodes.remove( rightNode );

            nodes.add( parent );
        }

        return nodes.get( 0 );

    }
}


class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }

        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }
}

