package class02;

//这节目的：如何用对数器找bug
//思路：写arr1并升序排序，如果程序写错了，拷贝arr1为arr2，打印arr2就知道数哪里排错了，从而在源代码中找bug
//对数器：生成随机样本并比对的机器

public class Code03_Comp {

	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] >= arr[minIndex]) {
					minIndex = j;
				}
			}
			swap(arr, i, minIndex);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}
	
	//目标：返回一个数组arr，arr长度[0, maxLen-1]，arr中的每个值[0,maxValue-1]
	public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
		int len = (int)(Math.random()*maxLen);
		int[] ans = new int[len]; //做到长度随机
		for(int i = 0; i < len; i++) {
			ans[i] = (int)(Math.random()*maxValue); //做到每个值随机
		}
		return ans;
	}
	
	//目标：写拷贝函数，原封不动拷贝原数组
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length]; //长度与原数组一致
		for(int i = 0; i < arr.length; i++) {
			ans[i] = arr[i]; //每个值与原数组一致
		}
		return ans;
	}
	
	//测试arr是否是升序的
	public static boolean isSorted(int[] arr) {
		if(arr == null || arr.length <2) {
			return true;
		}
		
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(max > arr[i]) {
				return false; //如果前面的数比后面的数大，说明是无序的，数组没排对
			}
			max = Math.max(max, arr[i]); //别忘了更新max
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 100;
		int testTime = 100000;
		for (int i =0; i < testTime; i++) {
			int[] arr1 = lenRandomValueRandom(maxLen, maxValue); //arr1长度、数值都是大样本随机
			int[] arr2 = copyArray(arr1); //克隆arr1，长度、数值都一样，但是占不同的内存区
			selectionSort(arr1);
			insertionSort(arr2);
			if(!isSorted(arr1)) { //意思是“arr1不是升序”
				for (int j = 0; j < arr2.length; j++) {
					System.out.print(arr2[j]+ " ");
				}
				System.out.println(); //加个回车
				System.out.println("选择排序错了");
				
				break;
			}
		
		}
	}
	
}
