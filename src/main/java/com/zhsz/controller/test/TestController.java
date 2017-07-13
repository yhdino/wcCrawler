package com.zhsz.controller.test;


import com.zhsz.dao.entity.misc.*;
import com.zhsz.service.misc.*;
import com.zhsz.utils.exception.WebException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.test.GloablParam;
import com.zhsz.utils.utils.*;
import com.zhsz.utils.utils.EncryptUtil;
import com.zhsz.utils.utils.SysConfig;
import com.zhsz.utils.utils.constant.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/test")
public class TestController{
	
	//全集queue
	Queue<com.zhsz.dao.entity.misc.Theme> themeQueue = new LinkedList<>();
	public static  Queue<String> replyQueue = new LinkedList<String>();

	// 编码
    private static final String ECODING = "UTF-8";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
	
	@Autowired
	private PostService postService;
	@Autowired
	private WeixinService weixinService;
	@Autowired
	private PostdetailService postdetailService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private PostdetailimgService postdetailimgService;
	
	
	/**
	 * 活动新闻一览
	 * @param
	 * @param request
	 * @param response
	 * @param
	 * @param
	 * @param
	 * @param
	 * @param model
	 * @return
	 * @throws WebException
	 */
	@RequestMapping("JsonToPost")
	public String JsonToPost( HttpServletRequest request, HttpServletResponse response,String json,String biz,String param,
			ModelMap model) throws WebException {
		try {
			
			
			if(StringUtils.isNotEmpty(json)){
				json = URLDecoder.decode(URLDecoder.decode(json,"utf-8"),"utf-8");
			}else{
				json = "";
			}
			if(StringUtils.isNotEmpty(biz)){
				biz = URLDecoder.decode(URLDecoder.decode(biz,"utf-8"),"utf-8");
			}else{
				biz = "";
			}
			if(StringUtils.isNotEmpty(param)){
				param = URLDecoder.decode(URLDecoder.decode(param,"utf-8"),"utf-8");
			}else{
				param = "";
			}
			int n = postService.doInsertTest1(json,biz,param);
			GloablParam.setPost(n+"");
			
			response.getWriter().print(n);
		} catch (Exception e) {
			ZhszLogger.error("查询活动信息失败", e, this.getClass());
			throw new WebException("查询活动信息失败");
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @param url
	 * @param model
	 * @return
	 * @throws WebException
	 */
	@RequestMapping("JsonToWeixin")
	public String insertJsonToWeixin( HttpServletRequest request, HttpServletResponse response,String json,String url,
			ModelMap model) throws WebException {
		try {
			if(StringUtils.isNotEmpty(json)){
				json = URLDecoder.decode(URLDecoder.decode(json,"utf-8"),"utf-8");
			}else{
				json = "";
			}
			if(StringUtils.isNotEmpty(url)){
				url = URLDecoder.decode(URLDecoder.decode(url,"utf-8"),"utf-8");
			}else{
				url = "";
			}
			
			//json = "{\"advertisement_num\":0,\"advertisement_info\":[],\"appmsgstat\":{\"show\":true,\"is_login\":true,\"liked\":false,\"read_num\":14674,\"like_num\":42,\"ret\":0,\"real_read_num\":0},\"comment_enabled\":1,\"reward_head_imgs\":[],\"only_fans_can_comment\":false,\"base_resp\":{\"wxtoken\":2226029804}}";
			
			int n = weixinService.doInsertWeixin(json,url);
			if(n==0){
				response.getWriter().print("false");
			}else{
				response.getWriter().print("true");
			}
			
		} catch (Exception e) {
			ZhszLogger.error("查询活动信息失败", e, this.getClass());
			throw new WebException("查询活动信息失败");
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @param url
	 * @param model
	 * @return
	 * @throws WebException
	 */
	@RequestMapping("JsonToPostDetail")
	public String JsonToPostDetail( HttpServletRequest request, HttpServletResponse response,String content,String url,String json,
			ModelMap model) throws WebException {
		try {
			if(StringUtils.isNotEmpty(content)){
				content = URLDecoder.decode(URLDecoder.decode(content,"utf-8"),"utf-8");
			}else{
				content = "";
			}
			if(StringUtils.isNotEmpty(url)){
				url = URLDecoder.decode(URLDecoder.decode(url,"utf-8"),"utf-8");
			}else{
				url = "";
			}
			
			if(StringUtils.isNotEmpty(json)){
				json = URLDecoder.decode(URLDecoder.decode(json,"utf-8"),"utf-8");
			}else{
				json = "";
			}
			
			//json = "{\"searchkey\":\"5d87scd-6ea6-43fc-9f06-69cd440dc001\",\"articletime\":\"2017-06-17\",\"days\":\"7\"}";
			
			int n = postdetailService.doInsertPostDetail(content,url,json);
			if(n==0){
				response.getWriter().print("false");
			}else{
				response.getWriter().print("true");
			}
			
			if(StringUtils.isEmpty(json)){//normal
				GloablParam.setPost(n+"");
			}
			
		} catch (Exception e) {
			ZhszLogger.error("查询活动信息失败", e, this.getClass());
			throw new WebException("查询活动信息失败");
		}
		
		return null;
	}
	
	@RequestMapping("JsonToReplay")
	public String JsonToReplay( HttpServletRequest request, HttpServletResponse response,String json,String url,
			ModelMap model) throws WebException {
		try {
			if(StringUtils.isNotEmpty(json)){
				json = URLDecoder.decode(URLDecoder.decode(json,"utf-8"),"utf-8");
			}else{
				json = "";
			}
			if(StringUtils.isNotEmpty(url)){
				url = URLDecoder.decode(URLDecoder.decode(url,"utf-8"),"utf-8");
			}else{
				url = "";
			}
			//json = "{\"base_resp\":{\"ret\":0,\"errmsg\":\"ok\"},\"enabled\":1,\"is_fans\":0,\"nick_name\":\"zhsz_ts\",\"logo_url\":\"\",\"my_comment\":[],\"elected_comment\":[{\"id\":6,\"my_id\":139,\"nick_name\":\"蓦然回首，那人却在灯火阑珊处\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM5F77Mwew08vEIbv0j8GFeBtsCGh1w8Y7cNesayf26KQg\\/132\",\"content\":\"感冒吸了会流泪，哮喘吸了会沉默……\",\"create_time\":1491828961,\"content_id\":\"11278115186176688267\",\"like_id\":10002,\"like_num\":7,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[]},\"is_from_me\":0,\"is_top\":0},{\"id\":4,\"my_id\":6,\"nick_name\":\"卢建威\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/3a3QxMHZ8YxFzZbt5nFUWSia8P80cplj2iaHs9G3fdZJCJx9RkzEsSyA\\/132\",\"content\":\"请问苏苏这场雪在什么时侯结束?我们老百姓讨厌这个杨柳花。\",\"create_time\":1491828492,\"content_id\":\"4483621647122694150\",\"like_id\":10005,\"like_num\":5,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"后面会跟进的\\/可爱每年咱们北极阁也会派出志愿者小分队献策献力\",\"uin\":3098407705,\"create_time\":1491828645,\"reply_id\":1,\"to_uin\":1043924514,\"reply_like_num\":5}]},\"is_from_me\":0,\"is_top\":0},{\"id\":1,\"my_id\":11,\"nick_name\":\"小冰\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/aeprA1njibUEZy6JCNd30FzEAdKicTPxny0yvI1YPB8u0\\/132\",\"content\":\"今天是我喜欢的帅哥播天气\\/色\",\"create_time\":1491828261,\"content_id\":\"3098421031178600459\",\"like_id\":10004,\"like_num\":5,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"我也喜欢\\/害羞\\/害羞\",\"uin\":3098407705,\"create_time\":1491828355,\"reply_id\":1,\"to_uin\":721407363,\"reply_like_num\":4}]},\"is_from_me\":0,\"is_top\":0},{\"id\":7,\"my_id\":103,\"nick_name\":\"lan\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM5DicTExeeQwq1szibtEqkGAweBUvZqyPO5z63TAI948f1w\\/132\",\"content\":\"表情包好强大！拜服！\",\"create_time\":1491829151,\"content_id\":\"9962555983173517415\",\"like_id\":10009,\"like_num\":3,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"据说每天来看苏苏的人都有用不完的表情包\",\"uin\":3098407705,\"create_time\":1491829221,\"reply_id\":1,\"to_uin\":2319588322,\"reply_like_num\":4}]},\"is_from_me\":0,\"is_top\":0},{\"id\":9,\"my_id\":16,\"nick_name\":\"不羁\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM6iarep6mt20HsuDEU6Pbic0dr5rvxVMAvvU4Izr39qPY0g\\/132\",\"content\":\"又可以忙活啦。\",\"create_time\":1491829547,\"content_id\":\"7524547033441501200\",\"like_id\":10008,\"like_num\":4,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[]},\"is_from_me\":0,\"is_top\":0},{\"id\":3,\"my_id\":63,\"nick_name\":\"????\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM44hBrn13uzMM3MHpl6K1EicTR3ENshUEzBmaAYCFCOj7w\\/132\",\"content\":\"一楼头像是我男神\",\"create_time\":1491828480,\"content_id\":\"5121432304987668543\",\"like_id\":10003,\"like_num\":4,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[]},\"is_from_me\":0,\"is_top\":0},{\"id\":2,\"my_id\":18,\"nick_name\":\"霍小飞飞\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/oRtI4VT9hupgpPV7ibcMu4KBa5fmOX5elGEetDLVoNLM\\/132\",\"content\":\"不下雨升温就好！雪可以克服！\",\"create_time\":1491828414,\"content_id\":\"19864417017528338\",\"like_id\":10001,\"like_num\":4,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[]},\"is_from_me\":0,\"is_top\":0},{\"id\":8,\"my_id\":3,\"nick_name\":\"?? Captain虐虐 ??\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM6moFentehN4Dwbp2OvmJe5lST8BSFLibyCCKiaANOWkemg\\/132\",\"content\":\"我男票！\\/色\",\"create_time\":1491829309,\"content_id\":\"8886314221919272963\",\"like_id\":10007,\"like_num\":3,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"是大家的男票??\",\"uin\":3098407705,\"create_time\":1491829379,\"reply_id\":1,\"to_uin\":2069006260,\"reply_like_num\":3}]},\"is_from_me\":0,\"is_top\":0},{\"id\":5,\"my_id\":213,\"nick_name\":\"春困秋乏夏打盹囡\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/QBD5ZWx8DIeduGS3Yibxtkfau350szEWiaBKGFZtlJbfc\\/132\",\"content\":\"晚上好喔\",\"create_time\":1491828754,\"content_id\":\"3243287361789886677\",\"like_id\":10006,\"like_num\":3,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"晚上好！??\",\"uin\":3098407705,\"create_time\":1491828906,\"reply_id\":1,\"to_uin\":755136684,\"reply_like_num\":3}]},\"is_from_me\":0,\"is_top\":0},{\"id\":13,\"my_id\":55,\"nick_name\":\"雨霖铃\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM79J5uz8W8iaMWicZWxIljJiapKMIu4pAMCyB2dfiaYdSDXIw\\/132\",\"content\":\"蘇蘇和天妞一樣可愛啊！\",\"create_time\":1491834305,\"content_id\":\"9314748471099523127\",\"like_id\":10012,\"like_num\":1,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"么么哒?? 被夸奖了有点害羞呢\",\"uin\":3098407705,\"create_time\":1491834943,\"reply_id\":1,\"to_uin\":2168758882,\"reply_like_num\":2}]},\"is_from_me\":0,\"is_top\":0},{\"id\":12,\"my_id\":6,\"nick_name\":\"???.???\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/1cIQsdyfjW1mnooDes56AUSlO26vIyr19lBO8gfVLew\\/132\",\"content\":\"吃下苏苏这一剂辣条\\/坏笑\",\"create_time\":1491832091,\"content_id\":\"439771644286730246\",\"like_id\":10010,\"like_num\":2,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"辣条娘你好\",\"uin\":3098407705,\"create_time\":1491832491,\"reply_id\":1,\"to_uin\":102392315,\"reply_like_num\":1}]},\"is_from_me\":0,\"is_top\":0},{\"id\":14,\"my_id\":16,\"nick_name\":\"isabel 虫\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM4QIEWOEzLeVV0P8ja0BaiaWuXA6IicW4HPP6VPFbsvtjGQ\\/132\",\"content\":\"哈哈，标题和表情包只服我苏\\/呲牙\",\"create_time\":1491864487,\"content_id\":\"8440550217786327056\",\"like_id\":10011,\"like_num\":2,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[]},\"is_from_me\":0,\"is_top\":0},{\"id\":11,\"my_id\":11,\"nick_name\":\"二熊大王\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/01MbDrQBTZMANjeX7MmoImUFz6icaHoIbYZAYQcdBEsU\\/132\",\"content\":\"又到了一把鼻涕一把眼泪的时候…\",\"create_time\":1491830997,\"content_id\":\"929071498516234251\",\"like_id\":10013,\"like_num\":1,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"说多了都是泪~\",\"uin\":3098407705,\"create_time\":1491832014,\"reply_id\":1,\"to_uin\":216316315}]},\"is_from_me\":0,\"is_top\":0},{\"id\":10,\"my_id\":18,\"nick_name\":\"最暖人心y\",\"logo_url\":\"http:\\/\\/wx.qlogo.cn\\/mmhead\\/Q3auHgzwzM50Er8B76vyU17iaRN4hvfXFmBicglucBXatky3sRVDLFvg\\/132\",\"content\":\"不习惯微信和苏苏互动，还是喜欢微博互动！\",\"create_time\":1491830956,\"content_id\":\"4452347533519224850\",\"like_id\":10014,\"like_num\":1,\"like_status\":0,\"is_from_friend\":0,\"reply\":{\"reply_list\":[{\"content\":\"微信苏默默看你们在围脖玩的好开心\\/快哭了\\/快哭了\",\"uin\":3098407705,\"create_time\":1491832043,\"reply_id\":1,\"to_uin\":1036642942}]},\"is_from_me\":0,\"is_top\":0}],\"friend_comment\":[],\"elected_comment_total_cnt\":14,\"only_fans_can_comment\":false}";
			//url = "/mp/appmsg_comment?action=getcomment&scene=0&__biz=MzA5ODQwNzcwNQ==&appmsgid=2650740230&idx=1&comment_id=4267381852&offset=0&limit=100&uin=777&key=777&pass_ticket=WppIpRvQP5QRGZn5LxxwCvj0G2JjzxE2b6xtwy8bFUm3ivj%25252BVpSyapWA%25252B487FoGM&wxtoken=3755072422&devicetype=android-22&clientversion=26050839&x5=1&f=json HTTP/1.1";
			String res = replyService.doInsertReply(json,url);
			response.getWriter().print(res);
			
		} catch (Exception e) {
			ZhszLogger.error("查询活动信息失败", e, this.getClass());
			throw new WebException("查询活动信息失败");
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param
	 * @param
	 * @param model
	 * @return
	 * @throws WebException
	 */
	@ResponseBody
	@RequestMapping("queryTheme")
	public void queryTheme(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws WebException {
		String json = "";
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			List<com.zhsz.dao.entity.misc.Theme> list = themeService.doQueryByConditionNoPage(condition);
			for(Theme theme:list){
				theme.setKeyword(URLEncoder.encode(theme.getKeyword(),ECODING));
			}
			json = ReturnCode.returnjson(ReturnCode.returnstateZ00,ReturnCode.returncontentZ00,",\"list\":"+JsonUtils.obj2json(list));
			PrintWriter out;
			out = response.getWriter();
			out.print(json);
			out.close(); 
			
		} catch (Exception e) {
			ZhszLogger.error("查询活动信息失败", e, this.getClass());
			throw new WebException("查询活动信息失败");
		}
		
	}
	
	/**
	 * 添加主题
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("addTheme")
	public int addTheme(HttpServletRequest request,HttpServletResponse response,String json){
		Map<String, Object> map = null;
		try {
			request.setCharacterEncoding("utf-8");
			Timestamp nousedate = new Timestamp(new Date().getTime());
			if(StringUtils.isNotEmpty(json)){
				json = URLDecoder.decode(URLDecoder.decode(json,"utf-8"),"utf-8");
			}else{
				json = "";
			}
			Theme theme = new Theme();
			map = JsonUtils.json2map(json);
			theme.setKeyword((String)map.get("keyword").toString()); 
			theme.setDays((String)map.get("days").toString()); 
			theme.setId(UUID.randomUUID().toString());
			theme.setType("0");
			theme.setCreatetime(nousedate);
			int n = themeService.doInsert(theme);
			if(n==0){
//				response.getWriter().print("false");
				return 0;
			}else{
//				response.getWriter().print("true");
				//插入queue
				themeQueue.offer(theme);
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * 查询或重查
	 * @param request
	 * @param response
	 * @param sign
	 */
	@ResponseBody
	@RequestMapping("searchTheme")
	public void searchTheme(HttpServletRequest request,HttpServletResponse response,String sign,String json){
		Map<String, Object> map = null;
		try {
			if(StringUtils.isNotEmpty(json)){
				json = URLDecoder.decode(URLDecoder.decode(json,"utf-8"),"utf-8");
			}else{
				json = "";
			}
			if(sign.equals("1")){//research
				map = JsonUtils.json2map(json);
				Theme theme = new Theme();
				theme.setId((String)map.get("searchkey").toString());
				theme.setKeyword((String)map.get("keyword").toString());
				theme.setDays((String)map.get("days").toString());
				themeQueue.offer(theme);
				response.getWriter().print("true");
			}else{//search
				if(themeQueue.size()<1){
					response.getWriter().print("false");
				}else{
					Theme theme = themeQueue.poll();
					String keyword = new String(theme.getKeyword().getBytes(ECODING),"GBK");
					String jsons = "{\"searchkey\":\""+theme.getId()+"\",\"keyword\":\""+EncryptUtil.base64Encode(keyword)+"\",\"days\":\""+theme.getDays()+"\"}";
					System.out.println(jsons);
					response.getWriter().print(jsons);
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	@ResponseBody
	@RequestMapping("isCoutinue")
	public void isCoutinue(HttpServletRequest request,HttpServletResponse response){
		try {
			String param = GloablParam.getPost();
			response.getWriter().print(param);
			GloablParam.setPost("1");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 分析图片
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("analyzeImgs")
	public Boolean analyzeImgs(HttpServletRequest request,HttpServletResponse response){
		
			Timestamp nousedate = new Timestamp(new Date().getTime());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("status", "0");
			List<Postdetail> list = postdetailService.doQueryByConditionNoPage(condition);
			if(list !=null){
				for(Postdetail postdetail:list){
					System.out.println(AnalyzeImgs.getImgsUrl(postdetail.getContent()));
					List<String> ls = getImageSrc(AnalyzeImgs.getImgsUrl(postdetail.getContent()));//原始图片地址
					BufferedImage bi = null;//建立一个图片缓存
			        URL url;
			        for(int i=0;i<ls.size();i++){
			        	try {
				        	System.out.println(ls.get(i));
			                url = new URL((String) ls.get(i));
			                bi = ImageIO.read(url);//远程获取图片
			                //去除小于20k的图片
			                ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
			                ImageIO.write(bi, "png", os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
			                byte b[]=os.toByteArray();//从流中获取数据数组。
			                if(b.length<20480){
			                	continue;
			                }
			                //保存图片地址
			                String spath = SysConfig.getPropertiesValue("sysimgdir");
			                String filePath = new UploadPathImpl().getUploadPath(spath,"postDetail");
			                File file_mk = new File(filePath);
			                if(!file_mk.exists()){
			                	file_mk.mkdirs();
			                }
			                String name =  UUID.randomUUID().toString() + ".png";
			                File f=new File(filePath+name);
			                ImageIO.write(bi, "png",f);
			                //插入postdetailimg表
			                Postdetailimg postdetailimg = new Postdetailimg();
			                postdetailimg.setId(UUID.randomUUID().toString());
			                postdetailimg.setCreatetime(nousedate);
			                postdetailimg.setFingerprint(ImgProcess.produceFingerPrint(bi));
			                postdetailimg.setNewpath("/"+filePath.substring(filePath.indexOf("postDetail"))+name);
			                postdetailimg.setOldpath(ls.get(i));
			                postdetailimg.setContentid(postdetail.getId());
			                postdetailimgService.doInsert(postdetailimg);
			                bi.flush();
			        	} catch (Exception e) {
			                e.printStackTrace();
			            }
			        }
			        
			        
			        String removecontent = RemoveHtmlTag.stripHtml(postdetail.getContent());
			        //System.out.println(removecontent);
			        //修改postdetail表
			        postdetail.setRemovecontent(removecontent);
			        postdetail.setStatus("1");
			        postdetail.setUpdatetime(nousedate);
			        postdetailService.doUpdate(postdetail);
				}
			}
			return true;
	}
	
	
	
	@ResponseBody
	@RequestMapping("returnPost")
	public void returnPost(HttpServletRequest request,HttpServletResponse response,String searchkey){
		String json = "";
		try {
			if(StringUtils.isNotEmpty(searchkey)){
				searchkey = URLDecoder.decode(URLDecoder.decode(searchkey,"utf-8"),"utf-8");
			}else{
				searchkey = "";
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("searchkey", searchkey);
			List<Post> list = postService.doQueryByPostDetail(condition);
			for(Post post:list){
				post.setTitle(URLEncoder.encode(post.getTitle(), ECODING).replace("+", "%20"));
				post.setDigest(URLEncoder.encode(post.getDigest(), ECODING).replace("+", "%20"));
			}
			json = ReturnCode.returnjson(ReturnCode.returnstateZ00,ReturnCode.returncontentZ00,",\"list\":"+JsonUtils.obj2json(list));
			PrintWriter out;
			out = response.getWriter();
			out.print(json);
			out.close(); 
		}catch (Exception e) {
			json = ReturnCode.exceptionjson(e.getMessage());
		}
	}
	
	
	@ResponseBody
	@RequestMapping("returnPostDetail")
	public void returnPostDetail(HttpServletRequest request,HttpServletResponse response,String sn){
		String json = "";
		try {
			if(StringUtils.isNotEmpty(sn)){
				sn = URLDecoder.decode(URLDecoder.decode(sn,"utf-8"),"utf-8");
			}else{
				sn = "";
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("sn", sn);
			List<Postdetail> list = postdetailService.doQueryByPostImg(condition);
			String returncontent = URLEncoder.encode(list.get(0).getRemovecontent(), ECODING).replace("+", "%20");
			List<Postdetail> list_ = list;
			for(Postdetail postdetail:list){
				postdetail.setRemovecontent("");
				postdetail.setNewpath(SysConfig.getPropertiesValue("pipath")+postdetail.getNewpath());
				
			}
			json = ReturnCode.returnjson(ReturnCode.returnstateZ00,returncontent,",\"list\":"+JsonUtils.obj2json(list_));
			PrintWriter out;
			out = response.getWriter();
			out.print(json);
			out.close(); 
		}catch (Exception e) {
			json = ReturnCode.exceptionjson(e.getMessage());
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

	/**
	 * 返回一个主题的文章数，平均阅读量和点赞量
	 * @param request
	 * @param response
	 * @param searchkey
	 */
	@ResponseBody
	@RequestMapping("returnNumByTheme")
	public void returnNumByTheme(HttpServletRequest request,HttpServletResponse response,String searchkey){
		String json = "";
		try {
			if(StringUtils.isNotEmpty(searchkey)){
				searchkey = URLDecoder.decode(URLDecoder.decode(searchkey,"utf-8"),"utf-8");
			}else{
				searchkey = "";
			}

			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("searchkey", searchkey);
			List<Postdetail> list = postdetailService.doQueryByTheme(condition);
			Postdetail postdetail = list.get(0);

			json = ReturnCode.returnjson(ReturnCode.returnstateZ00,ReturnCode.returncontentZ00,",\"postdetail\":"+JsonUtils.obj2json(postdetail));
			PrintWriter out;
			out = response.getWriter();
			out.print(json);
			out.close();
		}catch (Exception e) {
			json = ReturnCode.exceptionjson(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("returnCommentByReply")
	public void returnCommentByReply(HttpServletRequest request,HttpServletResponse response,String searchkey){
		String json = "";
		try {
			if(StringUtils.isNotEmpty(searchkey)){
				searchkey = URLDecoder.decode(URLDecoder.decode(searchkey,"utf-8"),"utf-8");
			}else{
				searchkey = "";
			}

			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("searchkey", searchkey);
			List<Reply> list = replyService.doQueryByReply(condition);
			Reply reply = list.get(0);

			json = ReturnCode.returnjson(ReturnCode.returnstateZ00,ReturnCode.returncontentZ00,",\"reply\":"+JsonUtils.obj2json(reply));
			PrintWriter out;
			out = response.getWriter();
			out.print(json);
			out.close();
		}catch (Exception e) {
			json = ReturnCode.exceptionjson(e.getMessage());
		}
	}

}
