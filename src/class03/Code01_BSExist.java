package class03;

import java.util.Arrays;

public class Code01_BSExist {

	//arr保证有序
	public static boolean find(int[]arr, int num) {
		if(arr == null || arr.length == 0) {
			return false;
		}
		
		int L = 0;
		int R = arr.length - 1;
		// arr[0...n-1]上找num
		
		while (L<=R) {
			int mid = (L+R)/2;
			if (arr[mid] == num) {
				return true;
			} else if(arr[mid] < num){
				L = mid+1; //数组中值比要找的数小，则左边的都不要，只看右边
			} else {
				R = mid -1; //数组中值比要找的数大，则右边都舍弃，只看左边
			}
		}
		return false;
	}
	
	
	//暴力找num，挨个遍历
	 public static boolean test(int[] sortedArr, int num) {
		 for (int cur : sortedArr) {
			 if (cur == num) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 //for test
	 public static int[] generateRandomArray(int maxSize, int maxValue) {
		 int[] arr = new int[(int)((maxSize + 1) * Math.random())];
		 for (int i = 0; i < arr.length; i++) {
			 arr[i] = (int)(Math.random()*maxValue);
		 }
		 return arr;
	 }
	
	 public static void main(String[]args) {
		 int maxSize = 100;
		 int maxValue = 10;
		 int testTime = 100000;
		 boolean succeed = true;
		 for(int i = 0; i < testTime; i++) {
			 int[] arr = generateRandomArray(maxSize, maxValue);
			 Arrays.sort(arr);
			 int value = (int)(Math.random()*maxValue);
			 if (test(arr, value) != find(arr, value)) {
				 System.out.println("出错了！");
				 succeed = false;
				 break;
			 }
		 }
	 
		 System.out.println(succeed? "nice!" : "something went wrong");
	 
	 }
	 
}
