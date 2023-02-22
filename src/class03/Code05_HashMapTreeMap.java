package class03;

import java.util.HashMap;

// (K V)表
public class Code05_HashMapTreeMap {
	
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("我是李智", "我今年26岁"); //新增
		System.out.println(map.containsKey("我是李智")); //找, return true
		System.out.println(map.containsKey("李智")); //找, return false
		System.out.println(map.get("我是李智")); //找，renturn get的string。（疑问：为啥这个不返回null呢！！！？？？）
		
		map.put("我是李智", "我今年23岁"); //更新
		System.out.println(map.get("我是李智"));
		
		map.remove("我是李智"); //删原来表的内容
		System.out.println(map.containsKey("我是李智"));
		System.out.println(map.get("我是李智"));
		
	}
	
}
