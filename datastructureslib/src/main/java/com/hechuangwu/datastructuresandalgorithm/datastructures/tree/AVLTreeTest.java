package com.hechuangwu.datastructuresandalgorithm.datastructures.tree;

/**
 * Created by cwh on 2019/11/15 0015.
 * 功能: 平衡二叉树：解决二叉树成为链表的问题(123456)，空树或左右两个子树高度差不超过1
 */
public class AVLTreeTest {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.midOrder();

        //平衡处理
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8
    }




    static class AVLTree {
        private Node root;

        public Node getRoot() {
            return root;
        }

        // 添加结点的方法
        public void add(Node node) {
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }
        // 中序遍历
        public void midOrder() {
            if (root != null) {
                root.midOrder();
            } else {
                System.out.println("二叉排序树为空，不能遍历");
            }
        }

    }







    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }



        /**
         * 返回左子树的高度
         */
        public int leftHeight() {
            if (left == null) {
                return 0;
            }
            return left.height();
        }

        /**
         * 返回右子树的高度
         */
        public int rightHeight() {
            if (right == null) {
                return 0;
            }
            return right.height();
        }

        /**
         * 当前节点左右树最大高度的值
         */
        public int height(){
            return Math.max( left==null?0:left.height(),right==null?0:right.height() )+1;
        }


        /**
         * 左旋转方法
         */
        private void leftRotate(){
            //创建新的结点，以当前根结点的值
            Node newNode = new Node(value);
            //把新的结点的左子树设置成当前结点的左子树
            newNode.left = left;
            //把新的结点的右子树设置成带你过去结点的右子树的左子树
            newNode.right = right.left;
            //把当前结点的值替换成右子结点的值
            value = right.value;
            //把当前结点的右子树设置成当前结点右子树的右子树
            right = right.right;
            //把当前结点的左子树(左子结点)设置成新的结点
            left = newNode;
        }

        /**
         * 右旋转
         */
        private void rightRotate() {
            Node newNode = new Node(value);
            newNode.right = right;
            newNode.left = left.right;
            value = left.value;
            left = left.left;
            right = newNode;
        }


        /**
         *  添加节点
         */
        public void add(Node node) {
            if (node == null) {
                return;
            }

            if (node.value < this.value) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add( node );
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add( node );
                }
            }

            //左旋
            if(rightHeight()-leftHeight()>1){
                //右子树的左子树高度大于右子树要先做右旋
                if(right != null && right.leftHeight() > right.rightHeight()) {
                    right.rightRotate();
                }
                leftRotate();
                return;
            }

            //右旋转
            if(leftHeight() - rightHeight() > 1) {
                //如果它的左子树的右子树高度大于它的左子树的高度
                if(left != null && left.rightHeight() > left.leftHeight()) {
                    left.leftRotate();
                }
                rightRotate();
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


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

    }
}
