package com.zhsz.utils.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class RandomNumber {
	/** * 获取一个四位随机数，并且四位数不重复 * * @return Set<Integer> */
	public static Set<Integer> GetRandomNumber() {
		// 使用SET搜索以此保证写入的数据不重复
		Set<Integer> set = new HashSet<Integer>(); // 随机数
		Random random = new Random();
		while (set.size() < 4) { // nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括） //
									// 和指定值（不包括）之间均匀分布的 int 值。
			set.add(random.nextInt(10));
		}
		return set;
	}
	
	public static String GetRandom() {
		Set<Integer> set = GetRandomNumber(); // 使用迭代器
		Iterator<Integer> iterator = set.iterator(); // 临时记录数据
		String temp = "";
		while (iterator.hasNext()) {
			temp += iterator.next();
			// System.out.print(iterator.next());
		}
		return temp;
	}
	
	public static void main(String[] args) {
		System.out.println(GetRandom());;
	}
}
