package class03;

import java.util.Arrays;

public class Code02_BSNearLeft {

	//arr是有序的，找 >= num最左的数的位置
	public static int mostLeftNoMoreNumIndex(int[]arr, int num) {
		if(arr == null || arr.length == 0) {
			return -1; //返回-1表示没有这个概念，是空集
		}
		
		int L = 0;
		int R = arr.length - 1;
		int ans = -1; //思路：1）先随便给ans赋值，2）在while里更新ans的值；3）最后返回ans
		//插一句，如果在[1,2,3,4]里找>=100的最左，1）第一次二分，2<100，左边都砍了；2）第二次二分，3<100，左边都砍了；3）第三次二分，4<100。最后返回ans = -1，说明在int[]arr里找不到>=100的
		while(L <= R) {
			int mid = (L+R)/2;
			if(arr[mid] >= num) {
				ans = mid;
				R = mid - 1; //实现把右边都砍了，只看左边
			}else {
				L = mid + 1; //实现把左边都砍了，只看右边
			}
		}
		
		return ans;
		
	}
	
	
	//对数器，暴力遍历找>=num最左的数，遍历法没有二分法好
	public static int test(int[]arr, int value) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= value) {
				return i;
			}
		}
		return -1;
	}
	
	//for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int)(Math.random()*maxSize)];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*maxValue);
		}
		return arr;
	}
	
	public static void main(String[]args) {
		int maxSize = 10;
		int maxValue = 100;
		int testTime = 100000;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			Arrays.sort(arr);
			int value = (int)(Math.random()*maxValue);
			if (test(arr, value) != mostLeftNoMoreNumIndex(arr, value)){
				//printArray(arr);
				System.out.println(value);
				System.out.println(test(arr, value));
				System.out.println(mostLeftNoMoreNumIndex(arr, value));
				succeed = false;
				break;
			}
		}
		System.out.println(succeed? "nice":"wrong");
	}
}
