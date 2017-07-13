package com.zhsz.wechat.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class RemoveHtmlTag {

    //定义script的正则表达式
    private static final String REGEX_SCRIPT="<script[^>]*?>[\\s\\S]*?<\\/script>";
    //private static final String REGEX_SCRIPT="<script[^>]*>[\\d\\D]*?</script>";

    //定义style的正则表达式
    private static final String REGEX_STYLE="<style[^>]*?>[\\s\\S]*?<\\/style>";

    //定义HTML标签的正则表达式
    private static final String REGEX_HTML="<[^>]+>";

    //private static final String REGEX_ID = "<(?<HtmlTag>[\\w]+)[^>]*\\s[iI][dD]=(?<Quote>[\"']?)js_article(?(Quote)\\k<Quote>)[\"']?[^>]*>((?<Nested><\\k<HtmlTag>[^>]*>)|</\\k<HtmlTag>>(?<-Nested>)|[\\s\\S]*?)*</\\k<HtmlTag>>";

    private static String delHTMLTag(String htmlStr){
        //Pattern p_id=Pattern.compile(REGEX_ID,Pattern.CASE_INSENSITIVE);
        //Matcher m_id=p_id.matcher(htmlStr);
        //htmlStr=m_id.replaceAll("");

        Pattern p_script=Pattern.compile(REGEX_SCRIPT,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(REGEX_STYLE,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(REGEX_HTML,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    /**
     * 去掉字符串里面的html代码。<br>
     * 要求数据要规范，比如大于小于号要配套,否则会被集体误杀。
     *
     * @param content
     * 内容
     * @return 去掉后的内容
     */
    public static String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        // 还原HTML
        // content = HTMLDecoder.decode(content);
        return content;
    }
}
