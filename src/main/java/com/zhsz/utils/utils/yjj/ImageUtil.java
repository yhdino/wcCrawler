package com.zhsz.utils.utils.yjj;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zhsz.utils.utils.FileUtil;
import com.zhsz.utils.utils.SysConfig;
import com.zhsz.utils.utils.UploadPathImpl;
import com.zhsz.utils.utils.constant.Globals;

@SuppressWarnings("restriction")
public class ImageUtil {
	/**
	 * 直接指定压缩后的宽高： (先保存原文件，再压缩、上传) 壹拍项目中用于二维码压缩
	 * 
	 * @param oldFile
	 *            要进行压缩的文件全路径
	 * @param width
	 *            压缩后的宽度
	 * @param height
	 *            压缩后的高度
	 * @param quality
	 *            压缩质量
	 * @param smallIcon
	 *            文件名的小小后缀(注意，非文件后缀名称),入压缩文件名是yasuo.jpg,则压缩后文件名是yasuo(+smallIcon
	 *            ).jpg
	 * @return 返回压缩后的文件的全路径
	 */
	/*public static String zipImageFile(String oldFile, int width, int height,
			float quality, String smallIcon) {
		if (oldFile == null) {
			return null;
		}
		String newImage = null;
		try {
			*//** 对服务器上的临时文件进行处理 *//*
			Image srcFile = ImageIO.read(new File(oldFile));
			*//** 宽,高设定 *//*
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
			String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
			*//** 压缩后的文件名 *//*
			newImage = filePrex + smallIcon
					+ oldFile.substring(filePrex.length());
			*//** 压缩之后临时存放位置 *//*
			FileOutputStream out = new FileOutputStream(newImage);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			*//** 压缩质量 *//*
			jep.setQuality(quality, true);
			encoder.encode(tag, jep);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newImage;
	}*/

	/**
	 * 保存文件到服务器临时路径(用于文件上传)
	 * 
	 * @param fileName
	 * @param is
	 * @return 文件全路径
	 */
	public static String writeFile(String fileName, InputStream is) {
		if (fileName == null || fileName.trim().length() == 0) {
			return null;
		}
		try {
			/** 首先保存到临时文件 */
			FileOutputStream fos = new FileOutputStream(fileName);
			byte[] readBytes = new byte[512];// 缓冲大小
			int readed = 0;
			while ((readed = is.read(readBytes)) > 0) {
				fos.write(readBytes, 0, readed);
			}
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 等比例压缩算法： 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
	 * 
	 * @param srcURL
	 *            原图地址
	 * @param deskURL
	 *            缩略图地址
	 * @param comBase
	 *            压缩基数
	 * @param scale
	 *            压缩限制(宽/高)比例 一般用1：
	 *            当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale
	 *            <1,缩略图width=comBase,height按原图宽高比例
	 * @throws Exception
	 * @author shenbin
	 * @createTime 2014-12-16
	 * @lastModifyTime 2014-12-16
	 */
	public static void saveMinPhoto(String srcURL, String deskURL,
			double comBase, double scale) throws Exception {
		File srcFile = new java.io.File(srcURL);
		Image src = ImageIO.read(srcFile);
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		int deskHeight = 0;// 缩略图高
		int deskWidth = 0;// 缩略图宽
		double srcScale = (double) srcHeight / srcWidth;
		/** 缩略图宽高算法 */
		if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
			if (srcScale >= scale || 1 / srcScale > scale) {
				if (srcScale >= scale) {
					deskHeight = (int) comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			} else {
				if ((double) srcHeight > comBase) {
					deskHeight = (int) comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			}
		} else {
			deskHeight = srcHeight;
			deskWidth = srcWidth;
		}
		BufferedImage tag = new BufferedImage(deskWidth, deskHeight,
				BufferedImage.TYPE_3BYTE_BGR);
		tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
		FileOutputStream deskImage = new FileOutputStream(deskURL); // 输出到文件流
		//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
		//encoder.encode(tag); // 近JPEG编码
		deskImage.close();
	}

	public static String getWeixinFile(String filename, String access_token, String media_id)
			throws Exception {
		String filePath = null;
		String spath = null;
		// new一个URL对象
		URL url = new URL("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id);
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			byte[] data = readInputStream(inStream);
			spath = SysConfig.getPropertiesValue("sysimgdir");
            filePath = new UploadPathImpl().getUploadPath(spath, Globals.PIC_DIR_PJ)+ filename + ".jpg";
            String detfilePath = new UploadPathImpl().getUploadPath(spath, Globals.PIC_DIR_PJ)+ filename + "thumb.jpg";
            FileUtil.saveFile(filePath, data);
            ImageUtil.saveMinPhoto(filePath, detfilePath, 150, 0.9d);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return SysConfig.getPropertiesValue("pipath")+filePath.substring(spath.length(), filePath.length());
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  

//	public static void main(String args[]) throws Exception {
//		String media_id = "pBRWaQKUZb0b6jCNml9apjvNy0c7-r3RbmWhQfeX4jAwKKkrMNHP66C4ozUeb6xu";
//		System.out.println(ImageUtil.getWeixinFile(media_id, "uG-D_Xo1GpKvCZIVSp_niG3uNNatOvqPyx--Ku7ViF0fZd6iSQ3pZj8wukEbG6hRRGtopGFQWryMdy85jwQO7OHd0dpUJxcMdZAF5TR2BBcGTNgAIAXKE", media_id));
//	}
	
	public static void main(String[] args) {
		try {
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/10.png", "C:/Users/Daland/Desktop/评论10条/10thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/11.png", "C:/Users/Daland/Desktop/评论10条/11thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/12.png", "C:/Users/Daland/Desktop/评论10条/12thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/20.png", "C:/Users/Daland/Desktop/评论10条/20thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/21.png", "C:/Users/Daland/Desktop/评论10条/21thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/40.png", "C:/Users/Daland/Desktop/评论10条/40thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/41.png", "C:/Users/Daland/Desktop/评论10条/41thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/50.png", "C:/Users/Daland/Desktop/评论10条/50thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/70.png", "C:/Users/Daland/Desktop/评论10条/70thumb.jpg", 150, 0.9d);
			ImageUtil.saveMinPhoto("C:/Users/Daland/Desktop/评论10条/71.png", "C:/Users/Daland/Desktop/评论10条/71thumb.jpg", 150, 0.9d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
