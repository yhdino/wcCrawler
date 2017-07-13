import com.zhsz.wechat.tools.EncryptUtil;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public class test_url {
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //String content =  URLEncoder.encode(param, "GBK");
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String sr=test_url.sendPost("http://192.168.10.92:8080/test/addTheme.htm", "json={\"keyword\":\"龙卷风\",\"days\":30}");
        //String sr=test_url.sendPost("http://192.168.10.85:8080/sqn-web-sysadmin/test/searchTheme.htm", "sign=1&json={\"searchkey\":\"684aae68-351b-4bf9-95e3-2ab024324960\",\"keyword\":\"淹水\",\"days\":30}");
        //String sr=test_url.sendPost("http://192.168.10.85:8080/sqn-web-sysadmin/test/returnPostDetail.htm","sn=479f64a9dc6dbb991d932657485cc443");
        //String sr=test_url.sendPost("http://192.168.10.85:8080/sqn-web-sysadmin/test/returnPost.htm","");
        //String sr=test_url.sendPost("http://192.168.10.85:8080/sqn-web-sysadmin/test/analyzeImgs.htm","");
        System.out.println(sr.toString());
    }
}
