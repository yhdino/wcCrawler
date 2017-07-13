package com.zhsz.utils.utils.com.baidu;

public class BaiduMap {

	  public static void main(String[] args)
	  {
	   /* String sql = " select id,spjdmc from zhsq_store ";
	    List list = DBHelper.executeQuery(StoreVo.class, sql);
	    for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
	      StoreVo vo = (StoreVo)obj;
	      Map map = GisUtils.Geocoder(vo.getSpjdmc());
	      try {
	        Object lng = map.get("lng");
	        Object lat = map.get("lat");
	        String sql1 = " update zhsq_store set lng = " + lng + ",lat = " + lat + " where id = '" + vo.getId() + "'";
	        boolean flag = DBHelper.exectesql(sql1);
	        System.out.println(flag + ":" + sql1);
	      } catch (Exception e) {
	        System.out.println(e);
	      }
	    }*/
		  
		  GisUtils.GetNeighPosition(0, 0, 1);
	  }
	}