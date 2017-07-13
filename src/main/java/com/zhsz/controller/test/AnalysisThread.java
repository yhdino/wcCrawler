package com.zhsz.controller.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhsz.utils.utils.JsonUtils;
import com.zhsz.utils.utils.db.DBHelper;
import com.zhsz.wechat.analysis.EmotionAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class AnalysisThread implements Runnable {
    public static String jstokenWH = null;
    private static Logger log = LoggerFactory.getLogger(AnalysisThread.class);

    public void run() {
        List<String> list = new ArrayList<String>();
        while (true) {
            try {
                if (TestController.replyQueue.size() > 0) {
                    String replyStr = TestController.replyQueue.poll();
                    String rtmsg = "";
                    new EmotionAnalysis();
                    HashMap reply = JsonUtils.json2pojo(replyStr, HashMap.class);
                    rtmsg = EmotionAnalysis.getEmotion((String) reply.get("content"));
                    while (true) {
                        if (rtmsg != "") {
                            JSONObject obj = JSONObject.parseObject(rtmsg);
                            JSONArray array = (JSONArray) JSONArray.toJSON(obj.get("items"));
                            String positive_prob = array.getJSONObject(0).get("positive_prob").toString();//积极
                            String sql = "";
                            if (Double.parseDouble(positive_prob) >= 0.60) {
                                sql = "update reply set positive = '1' where "
                                        + "id='" + reply.get("id") + "'";
                            } else if (Double.parseDouble(positive_prob) <= 0.40) {
                                sql = "update reply set negative='1' where "
                                        + "id='" + reply.get("id") + "'";
                            } else {
                                sql = "update reply set neutral='1' where "
                                        + "id='" + reply.get("id") + "'";
                            }
                            list.clear();
                            list.add(sql);
                            DBHelper.exectesql(list);
                            break;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
