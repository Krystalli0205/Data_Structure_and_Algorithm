package class01;

public class Code07_SelectionSort {

	public static void selectSort(int[]arr) {
		if (arr == null || arr.length < 2) {
			return;
		} //先想边界条件，如果arr是空，或者arr只有1个数，则天然有序，不用排序
		
		//下述思路：
		//0～n-1，选出最小值，放到0位置
		//1～n-1，选出最小值，放到1位置
		//2～n-1，选出最小值，放到2位置
		int N = arr.length;
		for (int i = 0; i < N ; i++) { //i是管起始数的范围的
			int minValueIndex = i;
			for (int j = i+1; j < N; j++) { //把i之后的数都遍历一遍
				minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex; //如果j位置的数比i位置的数小，则j位置成为最小值的位，不然依旧i位置是最小值。这句的作用是定位数组中最小值的位置
			}
			swap(arr, i, minValueIndex);//i位置的数和最小值所在的“位置”交换
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
		//用临时变量做交换
	}
	
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println(); //最后统一打个回车
	}
	
	public static void main(String[]args) {
		int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
		printArray(arr);
		selectSort(arr);
		printArray(arr);
		
	}
}
