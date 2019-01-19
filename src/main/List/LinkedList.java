/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LinkedList
 * Author:   猫熊小才天
 * Date:     2018/11/26 10:43
 * Description: 单向链表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package main.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈单向链表〉
 *
 * @author 猫熊小才天
 * @create 2018/11/26
 * @since 1.0.0
 */
public class LinkedList {

    private Node head = null;
    private Node reHead = null;

    /***
     * data 存放数据
     * next 存放下一个结点
     */
    private class Node {
        int data; // 结点中的数据
        Node next = null; // 下一个结点的地址信息

        public Node(int data){
            this.data = data;
        }

        public Node() {
            super();
        }
    }

    /**
     * 插入新的结点（尾插法）
     * 头插法：
     * Node newNode = new Node(d);
     * newNode.next = head;
     * head = newNode;
     * @param data
     */
    public void addNode(int data){
        Node newNode = new Node(data); // 初始化一个结点
        if(head == null){
            head = newNode;
            return;
        }else {
            Node tmp = head;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
    }

    /**
     * 删除第index个结点
     * @param index
     * @return
     */
    public boolean deleteNodeByIndex(int index){
        /**
         * 如果输入的下标超过单向链表的范围
         */
        if(index < 1 || index > length()){
            System.out.println("输入下标不在单向链表的范围中");
            return false;
        }

        /**
         * index == 1，即删除第一个，将head往后移
         */
        if(index == 1){
            head = head.next;
            System.out.println("删除头结点成功");
            return true;
        }

        int i = 2; // 比较下标，head下标为1，所以从2开始判断
        Node preNode = head;
        Node curNode = head.next;
        while(curNode != null){
            if(index == i){
                preNode.next = curNode.next;
                System.out.println("删除第" + index + "个结点成功");
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            ++i;
        }
        System.out.println("删除失败");
        return false;
    }

    /**
     * 返回单向链表的长度
     * @return
     */
    public int length(){
        int len = 0;
        Node tmp = head;
        while(tmp != null){
            ++len;
            tmp = tmp.next;
        }
        return len;
    }

    /**
     * 删除单向链表中数据为data的所有结点
     * @param data
     * @return
     */
    public boolean deleteNodeByData(int data){
        if(head == null){
            System.out.println("单向链表为空");
            return false;
        }
        boolean flag = false;
        int count = 0;
        // 先循环将头结点删除，直到头结点中的data≠data
        Node tmp = head;
        while(tmp != null){
            if(tmp.data == data){
                tmp = tmp.next;
                head = tmp;
                ++count;
                flag = true;
            }else {
                break;
            };
        }
        Node preNode = head;
        Node curNode = head.next;
        while(curNode != null){
            if(curNode.data == data){
                preNode.next = curNode.next;
                flag = true;
                ++count;
            }
            preNode = curNode;
            curNode = curNode.next;
        }
        if(flag == true){
            System.out.println("共删除了" + count + "个结点");
            return true;
        }else {
            System.out.println("删除失败");
            return false;
        }
    }

    /**
     * 获取所有符合data数据的结点的下标
     * @param data
     * @return
     */
    public String getDataIndex(int data){
        String indexStr = "";
        if(head == null){
            System.out.println("单向链表为空");
            return indexStr + "查无此数据";
        }
        int idx = 1;
        Node tmp = head;
        while(tmp != null){
            if(tmp.data == data){
                indexStr = indexStr + idx + ",";
            }
            ++idx;
            tmp = tmp.next;
        }
        if(indexStr.equals("")){
            indexStr = "查无此数据";
        }else {
            indexStr = "符合查询数据的结点下标是：" + indexStr.substring(0,indexStr.length() - 1);
        }
        return indexStr;
    }

    /**
     * 打印单向链表
     * 如果传过来的参数head是list.reHead即为逆向输出
     * @param head
     */
    public void printLinkedList(Node head) {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data);
            tmp = tmp.next;
            if(tmp != null){
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    /**
     * 非递归方式反转单向链表
     */
    public void reverseList(){
        if (head == null)
            return;
        Node pre = head; // 上一结点
        Node cur = head.next; // 当前结点
        Node tmp = null; // 临时结点，用于保存当前结点的指针域（即下一结点）
        while(cur != null){ // 当前结点为null，说明位于尾结点
            tmp = cur.next;
            cur.next = pre; // 反转指针指向

            // 指针向下移动
            pre = cur;
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.next = null;
        reHead = pre;
    }

    /**
     * 采用递归方式逆向输出单向链表
     * @param head
     */
    public void printReverse(Node head){
        if(head != null){
            printReverse(head.next);
            System.out.print(head.data + " ");
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addNode(5);
        list.addNode(3);
        list.addNode(1);
        list.addNode(5);
        list.addNode(2);
        list.addNode(55);
        list.addNode(36);
        list.addNode(15);

        /*System.out.println("单向链表的长度为：" + list.length());
        System.out.println("打印单向链表：");
        list.printLinkedList(list.head);*/

        /*list.deleteNodeByIndex(2);
        System.out.println("单向链表的长度为：" + list.length());
        System.out.println("打印单向链表：");
        list.printLinkedList(list.head);*/

        /*list.deleteNodeByData(5);
        System.out.println("单向链表的长度为：" + list.length());
        System.out.println("打印单向链表：");
        list.printLinkedList(list.head);*/

        /*System.out.println(list.getDataIndex(5));*/

        /*list.printLinkedList(list.head);
        list.reverseList();
        list.printLinkedList(list.reHead);*/

        list.printReverse(list.head);
    }
}