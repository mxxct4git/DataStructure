/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DoubleLinkedList
 * Author:   猫熊小才天
 * Date:     2018/11/27 13:56
 * Description: 双向链表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package main.List;

import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈双向链表〉
 *
 * @author 猫熊小才天
 * @create 2018/11/27
 * @since 1.0.0
 */
public class DoubleLinkedList<T> {

    private class DoubleNode<T> {
        public T data;//数据域
        public DoubleNode<T> prev;//前驱结点
        public DoubleNode<T> next;//后继结点

        public DoubleNode() {}

        public DoubleNode(DoubleNode<T> prev, T data, DoubleNode<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private DoubleNode<T> header = null;
    private DoubleNode<T> tail = null;
    private int size;

    /**
     * 判断双向链表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 链表长度
     */
    public int size() {
        return size;
    }

    // 创建空的双向链表
    public void DoubleLinkedList(){
        header = null;
        tail = header;
    }

    /**
     * 头插法
     * @param data
     */
    public void addHead(T data){
        // 创建新节点，让新节点的next指向原来的head
        // 并以新节点作为新的head
        header = new DoubleNode<T>(null,data,header);
        // 如果插入之前是空链表
        if(tail == null){
            tail = header;
        }
        size++;
    }

    /**
     * 尾插法
     * @param data
     */
    public void addTail(T data){
        // 如果是空链表
        if(header == null){
            header = new DoubleNode<T>(null,data,null);
            // 只有一个节点，header、tail都指向该节点
            tail = header;
        }else {
            // 创建新节点，新节点的pre指向原tail节点
            DoubleNode<T> tmp = new DoubleNode<T>(tail,data,null);
            // 让尾节点的next指向新增的节点
            tail.next = tmp;
            // 以新节点作为新的尾节点
            tail = tmp;
        }
        size++;
    }

    /**
     * 根据索引index获取指定位置的节点
     * @param index
     * @return
     */
    public DoubleNode<T> getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        // 双向链表可以根据下标大小选择从前往后或者从后往前查
        // 假设第一个node的下标是0
        if (index <= size / 2) {
            // 从header节点开始
            DoubleNode<T> current = header;
            for (int i = 0; i <= size / 2 && current != null; i++, current = current.next) {
                if (i == index) {
                    return current;
                }
            }
        } else {
            // 从tail节点开始搜索
            DoubleNode<T> current = tail;
            for (int i = size - 1; i > size / 2 && current != null; i++, current = current.prev) {
                if (i == index) {
                    return current;
                }
            }
        }
        return null;
    }

    /**
     * 查找链式线性表中指定元素的索引
     * @param element
     * @return
     */
    public int locate(T element) {
        // 从头结点开始搜索
        DoubleNode<T> current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if ((current.data + "").equals((element + ""))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 向线性链表的指定位置插入一个元素
     * @param element
     * @param index
     */
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        // 如果还是空链表
        if (header == null) {
            addTail(element);
        } else {
            // 当index为0时，也就是在链表头处插入
            if (index == 0) {
                addHead(element);
            } else {
                // 获取插入点的前一个节点
                DoubleNode<T> prev = getNodeByIndex(index - 1);
                // 获取插入点的节点
                DoubleNode<T> next = prev.next;
                // 让新节点的next引用指向next节点，prev引用指向prev节点
                DoubleNode<T> newNode = new DoubleNode<T>(prev, element, next);
                // 让prev的next节点指向新节点
                prev.next = newNode;
                // 让prev的下一个节点的prev指向新节点
                next.prev = newNode;
                size++;
            }
        }
    }

    /**
     * 删除链式线性表中指定索引处的元素
     * @param index
     * @return
     */
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        DoubleNode<T> del = null;
        // 如果被删除的是header节点
        if (index == 0) {
            del = header;
            header = header.next;
            // 释放新的header节点的prev引用
            header.prev = null;
        } else {
            // 获取删除节点的前一个节点
            DoubleNode<T> prev = getNodeByIndex(index - 1);
            // 获取将要被删除的节点
            del = prev.next;
            // 让被删除节点的next指向被删除节点的下一个节点
            prev.next = del.next;
            // 让被删除节点的下一个节点的prev指向prev节点
            if (del.next != null) {
                del.next.prev = prev;
            }
            // 将被删除节点的prev、next引用赋为null
            del.prev = null;
            del.next = null;
        }
        size--;
        return del.data;
    }

    public static void main(String[] args) {
    }
}