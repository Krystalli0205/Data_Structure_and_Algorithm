package class03;

public class Code04_BSAwesome {

	// 在无序且相邻数不相等的arr中找局部最小值的位置
	public static int oneMinIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}

		int N = arr.length;

		if (N == 1) {
			return 0;
		}

		// 以下这两个条件，一方面限制了左右两边的情况，另外也把length == 2的情况考虑了，不用单独写长度为2时的限制条件
		if (arr[0] < arr[1]) {
			return 0;
		}

		if (arr[N - 1] < arr[N - 2]) {
			return N - 1;
		}

		int L = 0;
		int R = N - 1;
		while (L < R - 1 ) { //确保L...R大于3个数很重要！为啥这么写？为了防止从L到R一共只有2个数，那么mid-1 or mid+1就不落在[L]~[R]里了
			int mid = (L + R) / 2; //当L+R是奇数时，mid自动往左挪半位，取中上位
			if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
				return mid; //mid就是局部最小
			} else {
				if (arr[mid] > arr[mid - 1]) { // 右边放弃，在左边找
					R = mid - 1;
				} else { // 潜台词：arr[mid] > arr[mid+1]) ，左边放弃，在右边找
					L = mid + 1;
				}
			}
		}
		return arr[L] < arr[R] ? L : R; // L~R>=3个数，见以上while循环；L~R总共2个数，返回更小的；总共<=1个数见最前头边界条件
	}

	// 对数器，生成大范围随机数组，满足无序且相邻数不相等
	public static int[] randomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * maxLen);
		int[] arr = new int[len]; //长度随机
		if (len > 0) {
			arr[0] = (int) (Math.random() * maxValue);
			for (int i = 1; i < len; i++) { // 确保相邻不相等
				do {
					arr[i] = (int) (Math.random() * maxValue);
				} while (arr[i] == arr[i - 1]); // 当相邻数相等，重做
			}
		}
		return arr;
	}
	
	//验证找到的局部最小是否真的是局部最小，return true说明找对了
	public static boolean check(int[] arr, int minIndex /* 最小值的位置 */) {
		if (arr.length == 0) {
			return minIndex == -1;
		}
		int left = minIndex - 1;
		int right = minIndex + 1;
		// 判断是否左边更大？left>=0说明是有效位置，看left是否真的大于minIndex的数；left <0说明越界，不破坏规则、不影响结果
		boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
		// 判断是否右边更大？right < arr.length说明是有效位置，看right是否真的大于minIndex的数；right>arr.length说明越界，不影响结果
		boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
		return leftBigger && rightBigger;
	}
	
	//打印数组，以防出错
	public static void printArray(int[] arr) {
		for(int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int testTime = 100000;
		int maxLen = 8;
		int maxValue = 200;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[]arr = randomArray(maxLen, maxValue); //创建无序数组arr，满足相邻数不等
			int ans = oneMinIndex(arr); //找到arr中局部最小的位置
			if(!check(arr, ans)) { //如果测试没通过，打印错误例子（数组、最小值的位置）
				printArray(arr);
				System.out.print(ans);
				break;
			}
		}
		System.out.println("测试结束");
	}
}