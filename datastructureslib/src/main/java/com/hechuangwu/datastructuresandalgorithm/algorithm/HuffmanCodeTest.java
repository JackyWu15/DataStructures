package com.hechuangwu.datastructuresandalgorithm.algorithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cwh on 2019/11/14 0014.
 * 功能: 哈夫曼编码
 */
public class HuffmanCodeTest {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] bytes = content.getBytes();//字节数组
//        System.out.println(Arrays.toString(bytes)+">>"+content.length());//40
//        //编码
//        Map<Byte, String> table = HuffmanCode.createTable( bytes );//编码表
//        byte[] encode = HuffmanCode.encode( content.getBytes(),table);
//        System.out.println(encode.length);//17，压缩了57.5%
//
//        //解码
//        byte[] decode = HuffmanCode.decode( encode, table );
//        System.out.println( Arrays.toString(decode));


        //文件压缩测试
        String srcFile = "H://src.bmp";
        String dstFile = "H://src.zip";
        String unZipFile = "H://unzip.bmp";
        //压缩
        HuffmanCode.zipFile( srcFile,dstFile );
        //解压
        HuffmanCode.unZipFile( dstFile,unZipFile );

    }
}




class HuffmanCode{
    /**
     * 压缩文件
     * @param srcFile 源文件
     * @param dstFile 压缩文件
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        ObjectOutputStream out = null;
        //创建文件的输入流
        FileInputStream in = null;
        try {
            //读取文件
            in = new FileInputStream(srcFile);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);

            //编码
            byte[] huffmanBytes = huffmanZip(buffer);
            //写入目标文件
            out = new ObjectOutputStream(new FileOutputStream(dstFile));
            out.writeObject(huffmanBytes);


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                out.close();
                in.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void unZipFile(String zipFile, String dstFile){
        ObjectInputStream in = null;
        OutputStream out = null;
        try {
            //读取文件
            in = new ObjectInputStream(new FileInputStream(zipFile));
            byte[] huffmanBytes = (byte[])in.readObject();

            //解码
            byte[] bytes = decode(huffmanBytes,huffmanCodes);
            out = new FileOutputStream(dstFile);
            out.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                out.close();
                in.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

        }
    }
    private static byte[] huffmanZip(byte[] bytes) {
        Map<Byte, String> table = createTable( bytes );
        return encode( bytes,table );
    }




    /**
     *  根据哈夫曼表，将字符串转换成哈夫曼编码
     */
    public static byte[] encode(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //获取字节编码
        //从表中获取路径
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b: bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        System.out.println("码前"+stringBuilder.toString());

        //将字符串转换成字节数组
        //        if(stringBuilder.length() % 8 == 0) {
        //            len = stringBuilder.length() / 8;
        //        } else {
        //            len = stringBuilder.length() / 8 + 1;
        //        }
        int len = (stringBuilder.length()+7)/8;

        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if(i+8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }



    //---------------------------解码实现---------------------------------------//
    public static byte[] decode(byte[] huffmanBytes,Map<Byte, String> huffmanCodes) {
        //字节数组转二进制码
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //最后字节不需要补码
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append( byteToBitString( !flag, b ) );
        }
        System.out.println("码后"+stringBuilder.toString());

        //将key和value交换
        Map<String, Byte>  map = new HashMap();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; //指针
            boolean flag = true;
            Byte b = null;

            while(flag) {
                String key = stringBuilder.substring(i, i+count);//截取字符
                b = map.get(key);
                if(b == null) {
                    count++;
                }else {
                    //匹配到一个
                    flag = false;
                }
            }
            //加入
            list.add(b);
            //移动到下一个
            i += count;
        }
        //转换成字节数组
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数补高位
        if(flag) {
            temp |= 256;
        }
        //返回的是temp对应的二进制的补码
        String str = Integer.toBinaryString(temp);
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //---------------------------编码实现---------------------------------------//

    /**
     * 构建编码表
     */
    public static Map<Byte, String>  createTable(byte[] bytes){
        //节点数组
        List<Node> nodes = getNodes( bytes );
        //转换哈夫曼树
        Node huffmanTree = createHuffmanTree( nodes );
        //哈夫曼路径表
        Map<Byte, String> codes = getCodes( huffmanTree );
        return codes;

    }

    /**
     * 创建节点数组
     */
    private static List<Node> getNodes(byte[] bytes){
        //字符编码为key，次数为value
        Map<Byte,Integer> counts = new HashMap<>(  );
        //保存次数
        for(byte b:bytes){
            Integer count = counts.get( b );
            if(count==null){
                counts.put( b,1 );
            }else {
                counts.put( b,count+1 );
            }
        }

        //转换成Node
        List<Node> nodes = new ArrayList<>(  );
        for(Map.Entry<Byte, Integer> entry: counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建哈夫曼树
     */
    private static Node createHuffmanTree(List<Node> list){
        while (list.size()>1){
            Collections.sort( list );
            Node leftNode = list.get( 0 );
            Node rightNode = list.get( 1 );
            Node parent = new Node( null, leftNode.weight + rightNode.weight );
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove( leftNode );
            list.remove( rightNode );
            list.add(parent);
        }
        return list.get( 0 );
    }

    static Map<Byte, String> huffmanCodes = new HashMap();
    /**
     *  得到叶子节点的哈夫曼表
     */
    public static Map<Byte, String> getCodes(Node root) {
        if(root==null){
            return null;
        }


        StringBuilder path = new StringBuilder();
        //处理root的左子树
        getCodes(root.left, "0", path);
        //处理root的右子树
        getCodes(root.right, "1", path);
        return huffmanCodes;
    }

    private static void getCodes(Node node, String code, StringBuilder path) {
        StringBuilder stringBuilder2 = new StringBuilder(path);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if(node != null) {
            if(node.data == null) { //非叶子结点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }



}




class Node implements Comparable<Node>  {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }


}