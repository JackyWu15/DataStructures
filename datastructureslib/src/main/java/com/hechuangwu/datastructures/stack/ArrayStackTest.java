package com.hechuangwu.datastructures.stack;

import java.util.Scanner;

/**
 * Created by cwh on 2019/10/16 0016.
 * 功能: 数组栈
 */
public class ArrayStackTest {
    private static void arrayStackTest() {
        ArrayStack stack = new ArrayStack( 4 );
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner( System.in );

        while (loop) {
            System.out.println( "s: 表示显示栈" );
            System.out.println( "e: 退出程序" );
            System.out.println( "ps: 表示添加数据到栈(入栈)" );
            System.out.println( "pp: 表示从栈取出数据(出栈)" );
            System.out.println( "请输入你的选择" );
            key = scanner.next();
            switch (key) {
                case "s":
                    stack.showStack();
                    break;
                case "ps":
                    System.out.println( "请输入一个数" );
                    int value = scanner.nextInt();
                    stack.push( value );
                    break;
                case "pp":
                    try {
                        int res = stack.pop();
                        System.out.printf( "出栈的数据是 %d\n", res );
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println( "程序退出~~~" );
    }

    public static void calculateTest(String expression) {
        Calculator calculator = new Calculator();
        int index = 0;
        char ch;
        StringBuilder complexNum= new StringBuilder();//多位数拼接
        do {
            ch = expression.substring( index, index + 1 ).charAt( 0 );
            if (calculator.isOperator( ch )) {
                //操作符不为空,并且当前加入的操作符优先级比栈顶的小，即出栈栈顶2个数据，进行运算
                if (!calculator.operatorStack.isEmpty() && calculator.priority( ch ) <= calculator.priority( calculator.operatorStack.peek() )) {
                    int calculate = calculator.calculate(calculator.numberStack.pop() ,calculator.numberStack.pop() , calculator.operatorStack.pop() );//运算
                    calculator.numberStack.push( calculate );//将计算结果压入栈中
                }
                calculator.operatorStack.push( ch );//将操作符压入栈中
            } else {
                complexNum.append( ch );
                //最后一位了
                if (index == expression.length()-1) {
                    calculator.numberStack.push( Integer.parseInt( complexNum.toString() ) );
                } else if (calculator.isOperator( expression.substring( index + 1, index + 2 ).charAt( 0 ) )) {//下一个是操作符
                    calculator.numberStack.push( Integer.parseInt( complexNum.toString() ) );
                    complexNum.delete( 0, complexNum.length() );
                }
            }
            index++;//取下一个
        } while (index < expression.length());

        //操作符为空时运算结束
        while (!calculator.operatorStack.isEmpty()) {
            int calculate = calculator.calculate( calculator.numberStack.pop(), calculator.numberStack.pop(), calculator.operatorStack.pop() );//运算
            calculator.numberStack.push( calculate );//将计算结果压入栈中
        }

        int result = calculator.numberStack.pop();
        System.out.printf("表达式计算结果： %s = %d", expression, result);


    }

    public static void main(String[] args) {
        //        arrayStackTest();
        String expression = "7*2*2-5+1+10-5+3-4";
        calculateTest( expression );
    }

}

/**
 * 计算器类
 */
class Calculator {
    ArrayStack numberStack = new ArrayStack( 10 );
    ArrayStack operatorStack = new ArrayStack( 10 );

    /**
     * 假定表达式只有 +, - , * , /
     * @param operator 操作符
     */
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     */
    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /**
     * 进行运算
     */
    public int calculate(int num1, int num2, int oper) {
        int ret = 0;
        switch (oper) {
            case '+':
                ret = num1 + num2;
                break;
            case '-':
                ret = num2 - num1;// 注意顺序
                break;
            case '*':
                ret = num1 * num2;
                break;
            case '/':
                ret = num2 / num1;
                break;
            default:
                break;
        }
        return ret;
    }


}

class ArrayStack {
    private int capacity;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println( "栈已满" );
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException( "栈空，没有数据~" );
        }
        int value = stack[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException( "栈空，没有数据~" );
        }
        return stack[top];
    }

    public void showStack() {
        if (isEmpty()) {
            System.out.println( "栈空，没有数据~~" );
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf( "stack[%d]=%d\n", i, stack[i] );
        }
    }


}
