/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Stack_Array
 * Author:   猫熊小才天
 * Date:     2018/11/27 13:23
 * Description: 栈——数组实现方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package main.Stack;

import java.util.EmptyStackException;

/**
 * 〈一句话功能简述〉<br>
 * 〈栈——数组实现方法——顺序栈〉 栈与线性表的最大区别是数据的存取的操作， 我们可以这样认为栈(Stack)是一种特殊的线性表，
 * 其插入和删除操作只允许在线性表的一端进行， 一般而言，把允许操作的一端称为栈顶(Top)， 不可操作的一端称为栈底(Bottom)，
 * 同时把插入元素的操作称为入栈(Push),删除元素的操作称为出栈(Pop)。
 *
 * @author 猫熊小才天
 * @create 2018/11/27
 * @since 1.0.0
 */
public class Stack_Array<T> implements Stack<T> {
	private int top = -1; // 栈顶指针,-1代表空栈

	private int capacity = 10;

	private T[] array;

	private int size;

	public Stack_Array(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public Stack_Array() {
		array = (T[]) new Object[this.capacity];
	}

	public int size() {
		return size;
	}

	// 判断数组是否为空
	public boolean isEmpty() {
		return this.top == -1;
	}

	// 添加元素，从栈顶（数组尾部）插入
	public void push(T data) {
		// 判断容量是否充足
		if (array.length == size)
			ensureCapacity(size * 2 + 1);// 扩容

		// 从栈顶添加元素
		array[++top] = data;
		size++;
	}

	/**
	 * 获取栈顶元素的值,不删除
	 * 
	 * @return
	 */
	@Override
	public T peek() {
		if (isEmpty())
			new EmptyStackException();
		return array[top];
	}

	/**
	 * 从栈顶(顺序表尾部)删除
	 * 
	 * @return
	 */
	@Override
	public T pop() {
		if (isEmpty())
			new EmptyStackException();
		size--;
		return array[top--];
	}

	/**
	 * 扩容的方法
	 * 
	 * @param capacity
	 */
	public void ensureCapacity(int capacity) {
		// 如果需要拓展的容量比现在数组的容量还小,则无需扩容
		if (capacity < size)
			return;

		T[] old = array;
		array = (T[]) new Object[capacity];
		// 复制元素
		for (int i = 0; i < size; i++)
			array[i] = old[i];
	}

	public static void main(String[] args) {
		Stack_Array<String> tmpStack = new Stack_Array<>();
		tmpStack.push("a");
		tmpStack.push("b");
		tmpStack.push("d");
		tmpStack.push("f");
		tmpStack.push("c");
		System.out.println("size->" + tmpStack.size());
		for (int i = 0; i < tmpStack.size(); i++) {
			System.out.println("s.pop->" + tmpStack.pop());
		}
		System.out.println("s.peek->" + tmpStack.peek());
	}
}