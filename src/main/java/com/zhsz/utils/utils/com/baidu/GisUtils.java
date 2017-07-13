package com.zhsz.utils.utils.com.baidu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.zhsz.utils.utils.JsonUtils;

public class GisUtils
{
  private static String ak = "orrT4rIFhW8eLChbBH7lQv7Z";
  private static String uri = "http://api.map.baidu.com/geocoder/v2/";
  private static String baseCity = "南京市";
  private static final double EARTH_RADIUS = 6378137.0D;

  public static Map Geocoder(String addr)
  {
    if (addr != null) {
      String resultJsonStr = sendGet(uri, "ak=" + ak + "&output=json&address=" + addr + "&city=" + baseCity);
      if (resultJsonStr.indexOf("location") > 0) {
        resultJsonStr = resultJsonStr.substring(resultJsonStr.indexOf("location") + 10, resultJsonStr.indexOf("precise") - 2);
        try
        {
          return JsonUtils.json2map(resultJsonStr);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    else {
      return null;
    }
    return null;
  }

  private static String sendGet(String url, String param) {
    String result = "";
    BufferedReader in = null;
    try {
      String urlNameString = url + "?" + param;
      URL realUrl = new URL(urlNameString);
      URLConnection connection = realUrl.openConnection();
      connection.setRequestProperty("accept", "*/*");
      connection.setRequestProperty("connection", "Keep-Alive");
      connection.setRequestProperty("user-agent", 
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      connection.connect();
      Map map = connection.getHeaderFields();
      in = new BufferedReader(new InputStreamReader(
        connection.getInputStream()));
      String line;
      while ((line = in.readLine()) != null)
      {
        result = result + line;
      }
      return result;
    } catch (Exception e) {
      System.out.println("发送GET请求出现异常！" + e);
      e.printStackTrace();
    }
    finally
    {
      try {
        if (in != null)
          in.close();
      }
      catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return result;
  }

  public static Map GetNeighPosition(double lng, double lat, double dis) {
    double r = 6371.0D;
    Map map = new HashMap();

    double dlng = 2.0D * Math.asin(Math.sin(dis / (2.0D * r)) / Math.cos(lat * 3.141592653589793D / 180.0D));
    dlng = dlng * 180.0D / 3.141592653589793D;
    double dlat = dis / r;
    dlat = dlat * 180.0D / 3.141592653589793D;
    map.put("minlat", Double.valueOf(lat - dlat));
    map.put("maxlat", Double.valueOf(lat + dlat));
    map.put("minlng", Double.valueOf(lng - dlng));
    map.put("maxlng", Double.valueOf(lng + dlng));
    return map;
  }

  private static double rad(double d)
  {
    return d * 3.141592653589793D / 180.0D;
  }

  public static double GetDistance(double lng1, double lat1, double lng2, double lat2) {
    double radLat1 = rad(lat1);
    double radLat2 = rad(lat2);
    double a = radLat1 - radLat2;
    double b = rad(lng1) - rad(lng2);
    double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
    s *= 6378137.0D;
    s = Math.round(s * 10000.0D) / 10000L;
    return s;
  }

  public static void main(String[] args)
    throws Exception
  {
  }
}