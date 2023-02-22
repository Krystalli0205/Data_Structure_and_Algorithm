package class01;

public class Code08_BubbleSort {

	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		//0~n-1，把最大值换到n-1位置上
		//0~n-2，把最大值换到n-2位置上
		//0~n-3，把最大值换到n-3位置上
		
		int N = arr.length;
		for (int end = N-1; end >= 0; end--) {//这个循环控制右边数到n-1、n-2、n-3...
			//目标：在0~end上，干一坨事：0 1换不换？1 2换不换？... end-1和end换不换？
			for (int second = 1; second <= end; second++) {//把第二个位置上的数命名为second
				if (arr[second-1] > arr[second]) { //如果第二个位置上的数比第一个位置上的数大，则交换
					swap(arr, second-1, second);
				}
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
		bubbleSort(arr);
		printArray(arr);
	}
}
