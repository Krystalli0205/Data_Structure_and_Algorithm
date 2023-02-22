package class01;

public class Code09_InsertSort {

	public static void insertSort1(int[] arr) {
		if ( arr == null || arr.length <2) {
			return;
		}
		
		//0~0 有序
		//0~1 排序
		//0~2 排序
		
		int N = arr.length;
		for (int end = 1 ; end < N; end ++) { //新数在end上
			int newNumIndex = end;
			while (newNumIndex - 1 >= 0 && arr[newNumIndex-1] > arr[newNumIndex]) {
				//最右边的数满足：1）左边有数；2）左边的数比它大。则和左边的数交换，换完再看下一个左边的数
				swap(arr, newNumIndex-1, newNumIndex);
				newNumIndex --;
			}
		}
	}
	
	public static void insertSort2(int[] arr) {
		if ( arr == null || arr.length <2) {
			return;
		}
		
		int N = arr.length;
		for (int end = 1; end <N; end++) {
			for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre+1]; pre--) {
				swap (arr, pre, pre+1);
			}
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
		insertSort2(arr);
		printArray(arr);
	}
	
}
