package class04;

// 测试链接：https://leetcode.com/problems/add-two-numbers/
public class Code05_AddTwoNumbers {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
		int len1 = listLength(head1);
		int len2 = listLength(head2);
		
		//下面这两句作用：长链表head是l，短链表head是s
		ListNode l = len1 >= len2 ? head1 : head2; //如果链表1更长，head1就是较长链表的头
		ListNode s = l == head1 ? head2 : head1;
		
		ListNode curL = l; //长链表当前节点
		ListNode curS = s; //短链表当前节点
		ListNode last = curL;
		int carry = 0; //临时变量，管进位
		int curNum = 0; //临时变量，管长短链表节点相加后结果
		
		//阶段1: 长有、短有，相加
		while (curS != null) {
			curNum = curL.val + curS.val + carry;
			curL.val = (curNum % 10); //相加后该位置上结果
			carry = curNum / 10; //看是否要进位
			last = curL;
			curL = curL.next;
			curS = curS.next;
		}
		while (curL != null) {
			curNum = curL.val + carry;
			curL.val = (curNum % 10);
			carry = curNum / 10;
			last = curL;
			curL = curL.next;
		}
		if (carry != 0) {
			last.next = new ListNode(1);
		}
		return l;
	}

	// 求链表长度
	public static int listLength(ListNode head) {
		int len = 0;
		while (head != null) {
			len++;
			head = head.next;
		}
		return len;
	}

}