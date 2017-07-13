package com.zhsz.controller.test;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class AnalyzeImgs {
    // 编码
    private static final String ECODING = "UTF-8";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";

    public static List<String> getImgsUrl(String article){
        if (article.length()<0){
            return null;
        }else {
            Matcher matcher = Pattern.compile(IMGURL_REG).matcher(article);
            List<String> listImgUrl = new ArrayList<String>();
            while (matcher.find()) {
                listImgUrl.add(matcher.group());
            }
            return listImgUrl;
        }
    }

    public static List<String> getImageSrc(List<String> listImageUrl) {
        List<String> listImgSrc = new ArrayList<String>();
        for (String image : listImageUrl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()) {
                listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
            }
        }
        return listImgSrc;
    }



    public static void main(String[] args) {
        String s = "";

        //System.out.println(getImageSrc(getImgsUrl(s)));
        System.out.println(getImgsUrl(s));
        List ls = getImageSrc(getImgsUrl(s));//原始图片地址
        BufferedImage bi = null;//建立一个图片缓存
        URL url;
        for(int i=0;i<ls.size();i++){
            try {
                url = new URL((String) ls.get(i));
                File f=new File("D:/content/"+ UUID.randomUUID().toString() + ".gif");
                System.out.println(ls.get(i));
                bi = ImageIO.read(url);//远程获取图片
                ImageIO.write(bi, "gif",f);
                bi.flush();
                System.out.println(ImgProcess.produceFingerPrint(bi));//生成fingerprint
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println(RemoveHtmlTag.stripHtml(s));//去掉js,css
    }
}
