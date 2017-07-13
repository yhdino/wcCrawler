package com.zhsz.utils.utils.com.image;


/*import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;*/

public class ImageDemo {
	/**
	 * 图片压缩处理
	 * @author 刘浩
	 *//*
		private Image img;
		private int width;
		private int height;
		private static ArrayList<String> imglist = new ArrayList<String>();
		public static void main(String[] args) throws Exception {
			// 获取某路径下所有图片
			try {
				String base = "D:\\ZHSQ\\2015";
				readfile(base);
				System.out.println("imglist的长度："+imglist.size());
                for (String string : imglist) {
                	ImageDemo imgCom = new ImageDemo(string);
    				imgCom.resizeFix(800, 800,string);
				}
			} catch (Exception e) {
				 System.out.println("抓到异常：" + e);
			}
		}
		*//**
		 * 构造函数
		 *//*
		public ImageDemo(String fileName) throws IOException {
			File file = new File(fileName);// 读入文件
			img = ImageIO.read(file);      // 构造Image对象
			width = img.getWidth(null);    // 得到源图宽
			height = img.getHeight(null);  // 得到源图长
		}
		*//**
		 * 按照宽度还是高度进行压缩
		 * @param w int 最大宽度
		 * @param h int 最大高度
		 *//*
		public void resizeFix(int w, int h,String path) throws IOException {
			if (width / height > w / h) {
				resizeByWidth(w,path);
			} else {
				resizeByHeight(h,path);
			}
		}
		*//**
		 * 以宽度为基准，等比例放缩图片
		 * @param w int 新宽度
		 *//*
		public void resizeByWidth(int w,String path) throws IOException {
			int h = (int) (height * w / width);
			resize(w, h,path);
		}
		*//**
		 * 以高度为基准，等比例缩放图片
		 * @param h int 新高度
		 *//*
		public void resizeByHeight(int h,String path) throws IOException {
			int w = (int) (width * h / height);
			resize(w, h,path);
		}
		*//**
		 * 强制压缩/放大图片到固定的大小
		 * @param w int 新宽度
		 * @param h int 新高度
		 *//*
		public void resize(int w, int h,String path) throws IOException {
			// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
			BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB ); 
			image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
			File destFile = new File(path);
			FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
			// 可以正常实现bmp、png、gif转jpg
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image); // JPEG编码
			out.close();
			System.out.println("已经修改的图片："+path);
		}
 
		public static void readfile(String path) {
			File file = new File(path);
			if (!file.isDirectory()) {
				System.out.println("文件");
				System.out.println("path=" + file.getPath());
				System.out.println("absolutepath=" + file.getAbsolutePath());
				System.out.println("name=" + file.getName());
				if (isImage(file)) {
					System.out.println("path=" + file.getPath());
				}
				if (isImage(file)) {
					//System.out.println("path=" + file.getName());
				}
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				try {
					for (int i = 0; i < filelist.length; i++) {
						File readfile = new File(path + "\\" + filelist[i]);
						if (!readfile.isDirectory()) {
							System.out.println("path=" + readfile.getPath());
							System.out.println("absolutepath="
									+ readfile.getAbsolutePath());
							System.out.println("name=" + readfile.getName());
							if (isImage(file)) {
								System.out.println("path=" + file.getPath());
							}
							if (isImage(readfile)) {
								//System.out.println("path=" + readfile.getName());
							}
						} else if (readfile.isDirectory()) {
							readfile(path + "\\" + filelist[i]);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		private static boolean isImage(File file)
		{
			boolean flag = false; 
			try
			{
				//String[] list = {"jpg","jpeg","png","gif"};
				String[] list = {"jpg","jpeg","png","gif"};
				String name = file.getName().substring( file.getName().lastIndexOf(".")+1, file.getName().length());
				for (String string : list) {
					if (name.toLowerCase().equals(string)) {
						flag = true;
						imglist.add(file.getPath());
						//System.out.println(file.getPath());
						break;
					}
				}
			} catch (Exception e)
			{
			}
			return flag;
		}*/

}
