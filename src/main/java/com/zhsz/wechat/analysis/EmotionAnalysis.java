package com.zhsz.wechat.analysis;

import com.zhsz.controller.test.AnalysisThread;
import com.zhsz.controller.test.TestController;
import com.zhsz.utils.utils.JsonUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class EmotionAnalysis {
    private static final String EMOTION_URL = "https://aip.baidubce.com/rpc/2.0/nlp/v1/sentiment_classify";
    private static final String EMOTION_OAUTH_URL = "https://aip.baidubce.com/oauth/2.0/token";
    private static final String GRANT_TYPE = "client_credentials";
    private static final String CLIENT_ID = "nl5baDQNi8Vt6FVCjRUackHq";
    private static final String CLIENT_SECRET = "GnxmTNUDYeOioFcBSxss9lUYMNkHU5M7";

    public static String getEmotion(String txt) {
        String access_token = "24.8908bbe09408d788d6b1b8fd66e13cdb.2592000.1501812507.282335-9842723";
        String url = EMOTION_URL+"?access_token="+access_token;
        String param = null;
        param = "{\"text\":\""+ txt+"\"}";
        return new EmotionAnalysis().sendPost(url,param);
    }

    //{"access_token":"24.8908bbe09408d788d6b1b8fd66e13cdb.2592000.1501812507.282335-9842723","session_key":"9mzdA5Plis+11jx5ZZ\/VE0etwM0Wu1etbiG52K7N4M3scdkT73+RobyX5Mb+e+sURIvuJCR\/71cX5\/BsYNGBWHlcXiNU","scope":"public vis-faceverify_faceverify vis-ocr_ocr vis-faceattribute_faceattribute nlp_simnet nlp_wordemb nlp_comtag nlp_dnnlm_cn vis-antiporn_antiporn_v2 vis-classify_watermark brain_ocr_scope vis-faceverify_faceverify_v2 brain_gif_antiporn brain_ocr_general brain_ocr_general_basic brain_ocr_general_enhanced vis-classify_terror brain_ocr_webimage brain_nlp_lexer brain_all_scope solution_face brain_ocr_idcard brain_ocr_driving_license brain_ocr_vehicle_license brain_antiporn brain_antiterror brain_nlp_comment_tag brain_nlp_dnnlm_cn brain_nlp_word_emb_vec brain_nlp_word_emb_sim brain_nlp_sentiment_classify vis-classify_\u6076\u5fc3\u56fe\u8bc6\u522b\u670d\u52a1 brain_politician brain_imgquality_general brain_nlp_simnet brain_kg_pie_task brain_nlp_depparser brain_solution audio_voice_assistant_get audio_tts_post wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian wangrantest_test wangrantest_test1 bnstest_test1 bnstest_test2 vis-classify_flower","refresh_token":"25.e0ba597b1c92884ddb0ff15dad8fc7a1.315360000.1814580507.282335-9842723","session_secret":"958dd52cb57c99db7682791f20c6e852","expires_in":2592000}
    public static String getOauth(){
        String url = EMOTION_OAUTH_URL;
//        String param = "{\"grant_type\":\"+GRANT_TYPE+\",\"client_id\":\"+CLIENT_ID+\",\"client_secret\":\"+CLIENT_SECRET+\"}";
        String param = "grant_type="+GRANT_TYPE+"&client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&";
        return new EmotionAnalysis().sendPost(url,param);
    }

    private String sendPost(String url, String param){
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
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "GBK"));
//            String content =  URLEncoder.encode(param, "GBK");
//            String content =  URLEncoder.encode("{\"text\":\"中兴是一家大公司\"}", "GBK");
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
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
        System.out.println(result);
        return result;
    }





    public static void main(String[] args) throws Exception {
        //System.out.println(new EmotionAnalysis().getOauth());
        //System.out.println(new EmotionAnalysis().getEmotion("已经热的不行不行的了，还要继续热。。。/晕"));
        new Thread(new AnalysisThread()).start();
        String driverClassName = com.zhsz.wechat.tools.SysConfig.getPropertiesValue("driverclassname");
        String url = com.zhsz.wechat.tools.SysConfig.getPropertiesValue("jdbc.url");
        String username = com.zhsz.wechat.tools.SysConfig.getPropertiesValue("jdbc.username");
        String password = com.zhsz.wechat.tools.SysConfig.getPropertiesValue("jdbc.password");
        Class.forName(driverClassName);
        Connection conn = DriverManager.getConnection(url, username, password);
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        if(conn == null){
            System.out.println("get the connection error...");
            return ;
        }
        try {
            pstmt = conn.prepareStatement("select reply.content,reply.id from reply");
            rs = pstmt.executeQuery();
            while(rs.next()){
                HashMap hm = new HashMap();
                hm.put("content", rs.getString(1));
                hm.put("id",rs.getString(2));
//				System.out.println(JsonUtils.map2json(hm));
                TestController.replyQueue.add(JsonUtils.map2json(hm));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try {
                rs.close();
                pstmt.close();
                if(!conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
