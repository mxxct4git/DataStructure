/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CircleLinkedList
 * Author:   猫熊小才天
 * Date:     2018/11/26 17:06
 * Description: 单向循环链表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package main.List;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈单向循环链表〉
 * 在循环的时候不能使用如下方法：
 * Node tmp = head;
 * while(tmp.next != null){
 *
 * }
 * 会出现死循环，换成
 * for(int i = 1; i <= getSize(); ++i)
 * @author 猫熊小才天
 * @create 2018/11/26
 * @since 1.0.0
 */
public class CircleLinkedList {

	private class Node {
		int data; // 结点中的数据
		Node next = null; // 下一个结点的地址信息

		public Node(int data) {
			this.data = data;
		}

		public Node() {
			super();
		}
	}

	private Node head = null; // 保存头结点
	private Node tail = null; // 保存尾节点
	private int size; // 保存已有的的结点数

	public CircleLinkedList() {
		super();
	}

	/**
	 * 头插法
	 * 
	 * @param data
	 */
	public void headAdd(int data) {
		// 如果链表为空
		if (isEmpty()) {
			head = new Node(data);
			head.next = head;
			tail = head;
		} else {
			Node newNode = new Node(data);
            // 让新结点指向head
			newNode.next = head;
            // 然后让新结点作为head
			head = newNode;
            // 将尾结点指向头结点
			tail.next = head;
		}
		++size;
	}

	/**
	 * 尾插法
	 * 
	 * @param data
	 */
	public void tailAdd(int data) {
		// 如果链表为空
		if (isEmpty()) {
			head = new Node(data);
			head.next = head;
			tail = head;
		}else {
		    Node newNode = new Node(data);
		    // 让尾结点指向新结点
		    tail.next = newNode;
		    // 让新结点指向头结点
		    newNode.next = head;
		    // 将新结点作为尾结点
		    tail = newNode;
        }
        ++size;
	}

    /**
     * 删除第index个结点
     * @param index
     * @return
     */
    public boolean deleteNodeByIndex(int index) {
        boolean res = false;
        if (isEmpty()) {
            System.out.println("单向循环链表为空");
            res = false;
        }
        /**
         * 如果输入的下标超过单向循环链表的范围
         * 假设第一个node的下标是1
         */
        if (index < 1 || index > getSize()) {
            System.out.println("输入下标不在单向循环链表的范围中");
            res = false;
        }else if(index == 1){
            // 删除头结点
            Node tmp = new Node(head.next.data);
            // 让tmp等于head的下一个结点
            tmp.next = head.next.next;
            // 将tmp赋值给head，即相当于删除了原先的head
            head = tmp;
            // 尾结点指向新的头结点
            tail.next = head;
            System.out.println("删除头结点成功");
            res = true;
        }else if(index == getSize()){
            // 删除尾结点
            // 获取倒数第二个结点
            Node tmp = getDataByIndex(getSize() - 1);
            // 让倒数第二个结点指向head
            tmp.next = head;
            // tmp赋值到新的尾结点
            tail = tmp;
            System.out.println("删除尾结点成功");
            res = true;
        }else if(index > 1 && index < getSize()){
            // 删除的是中间任一结点
            // 获取第 index - 1 个结点
            Node prev = getDataByIndex(index - 1);
            // 需要删除的结点即第index-1个结点的next
            Node cur = prev.next;
            prev.next = cur.next;
            System.out.println("删除第" + index + "个结点成功");
            res = true;
        }
        --size;
        return res;
    }

    /**
     * 删除单向循环链表中数据为data的所有结点
     * @param data
     * @return
     */
    public boolean deleteNodeByData(int data) {
        if (isEmpty()) {
            System.out.println("单向循环链表为空");
            return false;
        }
        boolean res = false;
        int count = 0;
        // 先循环将头结点删除，直到头结点中的data≠data
        Node tmp = head;
        for(int i = 1; i <= getSize(); ++i){
            if(tmp.data == data){
                tmp = tmp.next;
                head = tmp;
                tail = head;
                ++count;
                --size; // 需要将size减少！！！
                res = true;
            }else {
                break;
            }
        }
        // 再循环将尾结点删除，直到尾结点中的data≠data
        Node tmp2 = tail;
        for(int i = 1; i <= getSize(); ++i){
            if(tmp.data == data){
                tmp = getDataByIndex(getSize() - 1);
                tmp.next = head;
                tail = tmp;
                ++count;
                --size;
                res = true;
            }else {
                break;
            }
        }

        Node preNode = head;
        Node curNode = head.next;
        for(int i = 1; i <= getSize(); ++i){
            if(curNode.data == data){
                preNode.next = curNode.next;
                ++count;
                --size;
                res = true;
            }
            preNode = curNode;
            curNode = curNode.next;
        }

        if(res == true){
            System.out.println("共删除了" + count + "个结点");
        }else {
            System.out.println("删除失败");
        }
        return res;
    }

    /**
     * 获取index位置下的Node数据
     * @param index
     */
    public Node getDataByIndex(int index){
        if (isEmpty()) {
            System.out.println("单向循环链表为空");
            return null;
        }else if(index < 1 || index > getSize()){
            System.out.println("输入下标不在单向循环链表的范围中");
            return null;
        }else {
            Node res = head;
            for(int i = 1; i <= getSize() && res.next != null; ++i){
                if(i == index){
                    System.out.println("第" + index + "个结点的数据是：" + res.data);
                    return res;
                }
                res = res.next;
            }
            return res;
        }
    }

	/**
	 * 判断链表是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 返回链表长度
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 清空线性表
	 */
	public void clear() {
		// 将头结点和尾结点设为空
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * 打印单向链表 如果传过来的参数head是list.reHead即为逆向输出
	 * 
	 * @param head
	 */
	public void printCircleLinkedList(Node head) {
		Node tmp = head;
		for(int i = 1; i <= getSize(); ++i){
            System.out.print(tmp.data);
            tmp = tmp.next;
            if (i != getSize()) {
                System.out.print(" -> ");
            }
        }
		System.out.println();
	}

    public int findMinArrowShots(int[][] points) {
	    int count = 0;
	    int xmin = 0;
	    int xmax = 0;
	    for(int i = 0; i < points.length; ++i){
	        xmin = xmin < points[i][0] ? xmin : points[i][0];
            xmax = xmax > points[i][1] ? xmax : points[i][1];
        }
        boolean flag = false;
        for(int x = xmin; x <= xmax; ++x){
            flag = false;
	        for(int i = 0; i < points.length; ++i){
	            if(points[i][0] <= x && x <= points[i][1]){
	                flag = true;
                }
            }
            if(flag == true){
	            count++;
            }
        }
	    return count;
    }

    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();

        list.tailAdd(5);
        list.tailAdd(2);
        list.tailAdd(4);
        list.tailAdd(1);
        list.tailAdd(5);
        list.tailAdd(3);
        list.tailAdd(5);
        list.printCircleLinkedList(list.head);

        /*list.deleteNodeByIndex(6);
        list.printCircleLinkedList(list.head);*/

        //list.getDataByIndex(4);

        list.deleteNodeByData(5);
        list.printCircleLinkedList(list.head);
    }
}