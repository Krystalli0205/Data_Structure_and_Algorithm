package class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_LinkedListToQueueAndStack {

	public static class Node<V> {
		public V value;
		public Node<V> next;

		public Node(V v) {
			value = v;
			next = null;
		}
	}

	public static class MyQueue<V> {
		private Node<V> head;
		private Node<V> tail;
		private int size;

		public MyQueue() { //初始是空的
			head = null;
			tail = null;
			size = 0;
		}

		public boolean isEmpty() { //队列功能1: 知道里面是否有元素
			return size == 0;
		}

		public int size() { //返回元素个数
			return size;
		}

		public void offer(V value) { //功能2：接受某个值以队列的形式进入（新元素成为尾巴）
			Node<V> cur = new Node<V>(value); //value值进来，先建成一个节点
			if (tail == null) { //tail = null说明一个元素没有，进来的是第一个元素
				head = cur; //head和tail都指向第一个元素
				tail = cur;
			} else {
				tail.next = cur; //指针指向新进来的元素
				tail = cur; //新进元素成为尾巴
			}
			size++; //每次进来一个节点，元素数+1
		}

		// C/C++的同学需要做节点析构的工作
		public V poll() { // 功能3：弹出的时候先进先出
			V ans = null;
			if (head != null) {
				ans = head.value; //抓住头节点
				head = head.next; //让head往后移动一个
				size--; //因为3不在head和tail中间了，不可达，JVM自动释放3
			}
			if (head == null) { //表明head已经挪到tail后面了，说明链表已经弹没了，让tail和head保持一致，都指向null
				tail = null;
			}
			return ans;
		}

		// C/C++的同学需要做节点析构的工作
		public V peek() { // 功能4：找到下一个要弹出的元素，但不真的弹出
			V ans = null;
			if (head != null) { //头节点不为空，直接return下一个值
				ans = head.value;
			}
			return ans;
		}

	}

	public static class MyStack<V> {
		private Node<V> head;
		private int size;

		public MyStack() {
			head = null;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public int size() {
			return size;
		}

		public void push(V value) { //step 1: 把新元素以栈的方式放入链表
			Node<V> cur = new Node<>(value);
			if (head == null) { //如果原来链表是0，进来后第一个元素用head指
				head = cur;
			} else {
				cur.next = head; //如果原来链表有节点，新元素指向原节点
				head = cur; //新元素成为head
			}
			size++;
		}

		public V pop() { //step 2: 新元素按照后进先出弹出
			V ans = null;
			if (head != null) {
				ans = head.value; //找到头元素
				head = head.next; //head下移到后一个元素
				size--; //自动释放不可达的老head
			}
			return ans;
		}

		public V peek() {
			return head != null ? head.value : null;
		}

	}

	//这个对数器写得贼好，自己看！
	public static void testQueue() {
		MyQueue<Integer> myQueue = new MyQueue<>();
		Queue<Integer> test = new LinkedList<>();
		int testTime = 5000000;
		int maxValue = 200000000;
		System.out.println("测试开始！");
		for (int i = 0; i < testTime; i++) {
			if (myQueue.isEmpty() != test.isEmpty()) {
				System.out.println("Oops!");
			}
			if (myQueue.size() != test.size()) {
				System.out.println("Oops!");
			}
			double decide = Math.random();
			if (decide < 0.33) {
				int num = (int) (Math.random() * maxValue);
				myQueue.offer(num);
				test.offer(num);
			} else if (decide < 0.66) {
				if (!myQueue.isEmpty()) {
					int num1 = myQueue.poll();
					int num2 = test.poll();
					if (num1 != num2) {
						System.out.println("Oops!");
					}
				}
			} else {
				if (!myQueue.isEmpty()) {
					int num1 = myQueue.peek();
					int num2 = test.peek();
					if (num1 != num2) {
						System.out.println("Oops!");
					}
				}
			}
		}
		if (myQueue.size() != test.size()) {
			System.out.println("Oops!");
		}
		while (!myQueue.isEmpty()) {
			int num1 = myQueue.poll();
			int num2 = test.poll();
			if (num1 != num2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束！");
	}

	public static void testStack() {
		MyStack<Integer> myStack = new MyStack<>();
		Stack<Integer> test = new Stack<>();
		int testTime = 5000000;
		int maxValue = 200000000;
		System.out.println("测试开始！");
		for (int i = 0; i < testTime; i++) {
			if (myStack.isEmpty() != test.isEmpty()) {
				System.out.println("Oops!");
			}
			if (myStack.size() != test.size()) {
				System.out.println("Oops!");
			}
			double decide = Math.random();
			if (decide < 0.33) {
				int num = (int) (Math.random() * maxValue);
				myStack.push(num);
				test.push(num);
			} else if (decide < 0.66) {
				if (!myStack.isEmpty()) {
					int num1 = myStack.pop();
					int num2 = test.pop();
					if (num1 != num2) {
						System.out.println("Oops!");
					}
				}
			} else {
				if (!myStack.isEmpty()) {
					int num1 = myStack.peek();
					int num2 = test.peek();
					if (num1 != num2) {
						System.out.println("Oops!");
					}
				}
			}
		}
		if (myStack.size() != test.size()) {
			System.out.println("Oops!");
		}
		while (!myStack.isEmpty()) {
			int num1 = myStack.pop();
			int num2 = test.pop();
			if (num1 != num2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束！");
	}

	public static void main(String[] args) {
		testQueue();
		testStack();
	}

	
}
