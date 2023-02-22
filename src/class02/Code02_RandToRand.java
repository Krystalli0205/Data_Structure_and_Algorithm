package class02;

public class Code02_RandToRand {
	public static void main(String[] args) {
		System.out.println("测试开始");
		int testTimes = 10000000;
		int count = 0;
		for ( int i = 0; i < testTimes; i++) {
			if (Math.random() < 0.67) {
				count ++;
			}
		}
		System.out.println((double)count/(double)testTimes);//算<0.3的次数比上总次数，得出<0.3的概率；把int转化为double
	
	
		System.out.println("===================");
		
		//[0,1) -> [0,8)
		count = 0;
		for (int i = 0; i < testTimes; i++) {
			if (Math.random() * 8 < 5) {
				count ++;
			}
		}
		System.out.println((double)count/(double)testTimes);
		System.out.println((double)5/(double)8);
		
		
		System.out.println("===================");
		
		int K = 9; //【0,K) -> [0,8]
		
		int[] counts = new int[9]; //counts[0]表示0出现的次数，counts[1]表示1出现的次数
		for (int i = 0; i < testTimes; i++) {
			int ans = (int)(Math.random()*K); // [0,K) -> [0, K-1]的所有int
			counts[ans]++;
			}
		
		for(int i = 0; i < K; i++) {
			System.out.println(i + "这个数出现了 " + counts[i]+ " 次");
		}
	
		
		System.out.println("===================");
		
		count = 0;
		double x = 0.9;
		for (int i = 0; i < testTimes; i++) {
			if(xToXPower2() < x) {
				count++;
			}
		}
		
		System.out.println((double)count/(double)testTimes);
		System.out.println(Math.pow(x, 2));
		
	
		System.out.println("===================");
		
		count = 0;
		double y = 0.8;
		for (int i = 0; i < testTimes; i++) {
			if(xToXPower3() < y) {
				count++;
			}
		}
		
		System.out.println((double)count/(double)testTimes);
		System.out.println(Math.pow(y, 3));
		
		
		System.out.println("===================");

		//测试f2是否为0-1等概率返回器
		count = 0;
		for (int i = 0; i < testTimes; i ++) {
			if (f2()==0) {
				count ++;
			}
		}
		System.out.println((double)count/(double)testTimes);
		
		
		System.out.println("===================");

	
		counts = new int [8];
		for (int i = 0; i < testTimes; i ++) {
			int num  = g();
			counts[num]++; //count[num]表示num出现的次数
		}
		
		for(int i = 0; i < K; i++) {
			System.out.println(i + "这个数出现了 " + counts[i]+ " 次");
		}
	}
		
	
	//返回[0，1)的一个小数
	//任何x，x属于[0,1),[0,x)范围上的数出现的概率由原来的x变为x^2
	public static double xToXPower2() {
		return Math.max(Math.random(), Math.random()); 
		//为啥用max：只有两次分别调出的数范围都是[0,x)，max的范围才能是[0,x)。两次分别的概率是x，则max的概率是x^2
	}
		

	public static double xToXPower3() {
		return Math.max(Math.random(), Math.max(Math.random(),Math.random())); 
	}
	
	
	
	//f(x)等概率返回1-5，要求做一个函数g(x)等概率返回1-7，不许用Math.random()等
	//思路：用f(x)做0-1等概率发生器；1、2定位为0，4、5定位为1，3定位为f(x)再发生一遍
	//为啥不能*6+1？因为这样算得到的是1和7的等概率，不是1-7等概率（？没懂）
	
	//step 1: 构建函数，等概率返回1-5，是lib里的不能改！（问题：为啥不是等概率返回1-6？？？）
	public static int f1() {
		return (int)(Math.random()*5)+1; 
	}

	//step 2: 构建函数f2，随机机制，只能用f1。等概率返回0和1
	//？？？没懂代码逻辑
	public static int f2() {
		int ans = 0;
		do {
			ans = f1();
		}while(ans == 3); //do while意思是满足while()则实现do，不然return
		//在do里rou第一次，rou出1/2/3/4/5，在while里如果发现得3就重新做，如果不是3，则下一步return到0 or 1
		return ans < 3? 0 : 1;//控制不得到3，只得到0、1，且等概率
	}
	
	//step 3: 得到000-111做到等概率 -> 等价于0-7等概率返回
	//思路：调用3次f2，从0-0-0、0-0-1....到1-1-1的概率各自都是1/8（因为每次调用f2，结果是0（或1）的概率是1/2)
	//为什么调用f2 3次？（这点儿没太听懂。。。老师说是2^2+2^1+2^0=7，即需要3位二进制。从000到111正好是0~7）
	public static int f3() {
		return ((f2() << 2) + (f2() << 1)+ (f2() << 0)); //f2左移2位，确认最前面一位是0or1；左移1位，确认第二位是0or1；不动，确认最后一位是0or1
	}
	
	//step 4：做到0~6等概率返回
	public static int f4() {
		int ans = 0;
		do {
			ans = f3();
		}while (ans ==7); //在do里rou第一次，rou出来1/2/3/4/5/6/7，如果是7则重做，如果不是7直接return结果
		return ans;
	}
	
	//step 4：做到1~7等概率返回
	public static int g() {
		return f4()+1;
	}
	
	
	//新题目：你只能知道，x以固定概率返回1，但是x的内容你看不到，如何用x函数搞出y函数，让y函数等概率返回0和1？
    //思路：做2次x()，0-0不要，1-1不要，0-1返回0，1-0返回1，后两种概率是P*(1-P)，就变形回0-1等概率发生器
	public static int x() {
		return Math.random() < 0.84 ? 0 : 1;  //意思是x()有84%概率返回0、16%概率返回1
	}
	
	public static int y() {
		int ans = 0;
		do {
			ans = x();
		}while (ans == x()); 
		//在do里rou第一次，得到0 or 1
		//在while里rou第二次！！！但当第二次和原来函数一致（即rou出来0-0或1-1）就重做，如果是0-1或1-0就下一步
		return ans;
	}
	
}
