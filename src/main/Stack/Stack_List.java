/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Stack_List
 * Author:   猫熊小才天
 * Date:     2018/11/27 13:24
 * Description: 栈——链表实现方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package main.Stack;


import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈栈——链表实现方法——链式栈〉
 *
 * @author 猫熊小才天
 * @create 2018/11/27
 * @since 1.0.0
 */
public class Stack_List<T> implements Stack<T> {
    private List<T> list; // 用容器实现
    Stack_List(){
        list = new ArrayList<T>();
    }
    //弹栈
    public T pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return list.remove(list.size()-1);
    }
    //压栈
    public void push(T element){
        list.add(element);
    }
    //判断是否为空
    public boolean isEmpty(){
        return list.size() == 0;
    }
    //返回栈顶元素
    public T peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return list.get(list.size()-1);
    }

}