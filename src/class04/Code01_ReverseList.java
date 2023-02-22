package class04;


public class Code01_ReverseList {

	public static class Node{
		public int value;
		public Node next;
		
		public Node(int data) {
			value = data;
		}
	}
	
	public static class DoubleNode{
		public int value;
		public DoubleNode last;
		public DoubleNode next;
		
		public DoubleNode(int data) {
			value = data;
		}
	}
	
	
	//for test
	public static void main(String[]args) {
		Node n1 = new Node(1);
		n1.next = new Node(2); //n1的指针指向下一个节点，叫node(2)
		n1.next.next = new Node(3); //下一个节点node(3)
		n1 = reverseLinkedList(n1); //n1逆序后，新的头部被n1抓住，方便以后再用n1
		
		while (n1 != null) {
			System.out.print(n1.value + " ");
			n1 = n1.next; //n1跳到下个节点
		}
		System.out.println();
	
	}
	
	public static Node reverseLinkedList(Node head) {
		Node pre = null;
		Node next = null;
		while(head != null){
			next = head.next;//提前记好next，来方便改变指针指向后，head还能找到2；链表难就因为边界难搞
			head.next = pre; //loop 1: 让1的指针指向空；loop 2: 让2的指针指1；loop 3: 让3的指针指2
			pre = head;//loop 1: pre来到head的位置，即1; loop 2: pre来到2; loop 3: pre来到3
			head = next; //loop 1: 因为提前记了2是next，head来到next位置，即2；loop2: head来到3; loop3: head来到
		}
		return pre;
	}
	
	public static DoubleNode reverseDoubleList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while(head != null) {
			next = head.next; //记下一个数，方便指针改了后还能找到
			head.next = pre; //a的next指向空
			head.last = next; //a的last指向b
			pre = head;
			head = next;
		}
		return pre;
	}
	
	
}
