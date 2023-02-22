package class04;

// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
//LC hard难度！！！！
public class Code04_ReverseNodesInKGroup {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	
	public static ListNode reverseKGroup(ListNode head, int k) {
		// a -> b -> c -> d -> e -> f -> g -> h -> null 
		ListNode start = head; //a是start&head
		ListNode end = getKGroupEnd(start, k); //假设k=3，c是end
		if (end == null) { //什么情况下end == null？k = 3, 链表是a -> b -> null，长度不够直接返回head
			return head;
		}
		// 第一组凑齐了！
		head = end; //head就抓住新头部，head来到c，a依然是start
		reverse(start, end); //跑完后变成a <- b <- c  d -> e -> f -> g -> h -> null，且a指向d，start=a, end=c
		ListNode lastEnd = start; // 上一组的结尾节点，即a
		while (lastEnd.next != null) {
			start = lastEnd.next; //d变成start
			end = getKGroupEnd(start, k); //d再数3个，结尾来到f
			if (end == null) { //要是长度不够，回到head
				return head;
			}
			reverse(start, end); //a <- b <- c  d <- e <- f  g -> h -> null，且a指向d、d指向g，start=d, end=f
			lastEnd.next = end; //lastend是a，指向此时的end即f，a -> f
			lastEnd = start; //d变成新的lastend（每次lastend指的是不对的，在这步改对）
		}
		return head;
	}

	//给一个开始节点，数k个元素，把数到的第k个节点返回。例如：f(x, 5)，原始链表x a b c d，k=5，return d
	public static ListNode getKGroupEnd(ListNode start, int k) { 
		while (--k != 0 && start != null) { //图见笔记。翻译：当这一组后面还有元素，以及这组元素有>=k个，以确保跳到最后不是null。1）啥叫--k!=0：还拿上面链表举例，--k=4指向a、--k=3指向b ... --k=1指向d、--k=0指向e，--k!=0说明d后面还有元素。2）啥叫 start != null：为了确保链表有k个元素。如果不够k个元素，return null
			start = start.next;
		}
		return start;
	}

	//整体思路：初始... -> s -> a -> b -> c -> e -> k。1）s指向null；2）a,b,c往回指，e往回指之前先记住k，e再往回指；3）s指向k。图见笔记
	public static void reverse(ListNode start, ListNode end) {
		//... -> a -> b -> c -> d -> f，一开始s=a，e=d
		end = end.next; //组里的尾巴先往后跳一个，e来到f，这是为了到e才停，abcd都是要做逆序的
		ListNode pre = null;
		ListNode next = null;
		ListNode cur = start; //current来到s
		while (cur != end) {//到e停止，让abcd干事儿
			next = cur.next; //loop 1: next来到b
			cur.next = pre; //loop 1: a指向空
			pre = cur; //loop 1: b指向a
			cur = next; //loop 1: cur来到b
		}
		start.next = end;
	}

}