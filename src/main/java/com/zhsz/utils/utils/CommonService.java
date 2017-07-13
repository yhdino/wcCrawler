package com.zhsz.utils.utils;

import java.util.ArrayList;
import java.util.List;


public class CommonService {
	/**
	 * 根据菜的数量分配几个盒子
	 * 两个菜放一个盒子
	 * @param count
	 */
     public static int  getBmhgHz(int count ) {
    	  int i = count/2;
    	  int j = count%2;
    	  if (j == 0) {
    		  i = count/2;
    	  } else {
    		  i = count/2+1;
    	  }
    	  return i;
     }
     
     /**
      * 分配盒子
      * @param count
      * @return
      */
     public static String  setHz(List<String> list,int count ) {
    	 String hzcode = "";
    	 for (int i=0;i<list.size();i++) {
				if (count >= 2) {
					String[] str = list.get(i).split(",");
					if (str.length == 2) {
						//还有两个空位
						list.set(i, list.get(i) + ",1,1");
						hzcode += str[1]+"|";
						count = count -2;
						continue;
					}
					if (str.length == 3) {
						//还有两个空位
						list.set(i, list.get(i) + ",1");
						hzcode += str[1]+"|";
						count = count -1;
						continue;
					}
				} else if (count ==1) {
					String[] str = list.get(i).split(",");
					if (str.length == 2) {
						//还有两个空位
						list.set(i, list.get(i) + ",1");
						hzcode += str[1]+"|";
						count = count -1;
						continue;
					}
					if (str.length == 3) {
						//还有两个空位
						list.set(i, list.get(i) + ",1");
						hzcode += str[1]+"|";
						count = count -1;
						continue;
					}
				}
		 }
   	  return hzcode;
    }
     
     public static void main(String[] args) {
    	 List<String> list = new ArrayList<String>();
    	 list.add("1,A01");
    	 list.add("2,A02");
    	 list.add("3,A03");
    	 list.add("4,A04");
    	 list.add("5,A05");
    	 System.out.println(setHz(list,5));
    	 System.out.println(setHz(list,6));
	}
}
