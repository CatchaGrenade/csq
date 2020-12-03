package com.learn.comUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 封装一些对本系统常用的公共方法
 * @author pengmm
 */
public class CommUtil{
	
	private static Log logger = LogFactory.getLog(CommUtil.class);


	/**
	 * 将request格式的参数转换为map格式
	 * @param request 请求消息头
	 * @return map格式的请求信息
	 */
	public static  Map<String,Object> requestParamToMap4Miniui(HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			Object value = request.getParameter(key);
			returnMap.put(key, value);
		}
		return returnMap;
	}

	public static int getPageNum(Map<String,Object> map){
		int pageNum=(map.get("page")==null?0:Integer.parseInt(map.get("page").toString()));
		return pageNum;
	}
	public static int getPageSize(Map<String,Object> map){
		int pageSize=(map.get("limit")==null?10:Integer.parseInt(map.get("limit").toString()));
		return pageSize;
	}

	/**
	 * 判断map中是否含有key
	 * @param param 参数map
	 * @param key 要判断的key
	 * @return true：存在；false：不存在
	 */
	public static boolean isKeyExist(Map<String, ? extends Object> param,String key){
		if (param == null || param.isEmpty()) {
			return false;
		}
		if (!param.containsKey(key) 
			|| key == null || "".equals(key) 
			|| "null".equals(key) || null==param.get(key)
			||"undefined".equals(param.get(key))
			|| "".equals(param.get(key))) {
			return false;
		}
		if ("".equals(param.get(key))||"null".equals(param.get(key))) {
			return false;
		}
		return true;
	}
	/**
	 * 判断map中是否不含有key
	 * @param param 参数map
	 * @param key 要判断的key
	 * @return true：存在；false：不存在
	 */
	public static boolean isKeyNotExist(Map<String, ? extends Object> param,String key){
		return !isKeyExist(param,key);
	}

	/**
	 * 将参数解码为正常字符串
	 * @param searchPara 编码后的字符串
	 * @return 解码后的字符串
	 */
	public static String decode(String searchPara) {
		if(searchPara != null){
			try {
				searchPara = URLDecoder.decode(searchPara,"utf-8");
				searchPara = URLDecoder.decode(searchPara,"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(),e);
			}
		}
		return searchPara;
	}
	/**
	 * 判断字符串是否为空
	 * @param key
	 * @return true:不为空;false:为空
	 */
	public static boolean isNotBlank(String key) {
		if (null == key || "".equals(key) || "null".equals(key)||"undefined".equals(key)) {
			return false;
		}
		return true;
	}
	/**
	 * 判断字符串是否为空
	 * @param key
	 * @return true:为空;false:不为空
	 */
	public static boolean isBlank(String key) {
		return !isNotBlank(key);
	}
	/**
	 * 判断map是否为空
	 * @autor pengmm
	 * @date 2018-4-12下午8:39:41
	 * @param param
	 * @return
	 */
	public static boolean isBlank(Map<String, ? extends Object> param) {
		return (param == null || param.isEmpty()) ? true : false;
	}
	/**
	 * 判断map是否不为空
	 * @autor pengmm
	 * @date 2018-4-12下午8:39:55
	 * @param param
	 * @return
	 */
	public static boolean isNotBlank(Map<String, ? extends Object> param) {
		return !isBlank(param);
	}
	/**
	 * 判断list是否为空
	 * @autor pengmm
	 * @date 2018-4-12下午8:40:07
	 * @param list
	 * @return
	 */
	public static boolean isBlank(List<? extends Map<String,?>> list) {
		return (list == null || list.isEmpty() || list.size() == 0) ? true : false;
	}
	/**
	 * 判断list是否不为空
	 * @autor pengmm
	 * @date 2018-4-12下午8:40:22
	 * @param list
	 * @return
	 */
	public static boolean isNotBlank(List<? extends Map<String,?>> list) {
		return !isBlank(list);
	}

	/**
	 * list集合转换成in集合参数形式的字符串
	 * @param list
	 * @return
	 */
	public static String listToInstr(List<String> list){
		StringBuffer sBuffer = new StringBuffer(" (");
		for (String val : list) {
			sBuffer.append("'").append(val).append("',");
		}
		String resultStr = sBuffer.substring(0, sBuffer.length()-1);
		resultStr += ") ";
		return resultStr;
	}
	/**
	 * list集合转换成in集合参数形式的字符串,并封装参数list
	 * @date 2018-3-16下午5:35:40
	 * @param list
	 * @param paramList
	 * @return
	 */
	public static String listToInstr(List<String> list,List<Object> paramList){
		StringBuffer sBuffer = new StringBuffer(" (");
		for (String val : list) {
			sBuffer.append("?,");
			paramList.add(val);
		}
		String resultStr = sBuffer.substring(0, sBuffer.length()-1);
		resultStr += ") ";
		return resultStr;
	}
	/**
	 * in集合字符串转换为list
	 * @param pks
	 * @return
	 */
	public static List<String> inStrToList(String pks){
		String[] strPks = pks.split(",");
		List<String> list = new ArrayList<String>();
		for (String str : strPks) {
			list.add(str);
		}
		return list;
	}
	/**
	 * 获取32位随机字符串
	 * @return
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
	    uuid = uuid.replace("-", "");               //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可  
	    return uuid;
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 *            request请求
	 * @return IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 	下载文件时，不同浏览器对中文文件名处理<br/>
	 *  1.IE浏览器，采用URLEncoder编码<br/>
	 *	2.Opera浏览器，采用filename*方式<br/>
	 *	3.Safari浏览器，采用ISO编码的中文输出<br/>
	 *	4.Chrome浏览器，采用Base64编码或ISO编码的中文输出<br/>
	 *	5.FireFox浏览器，采用Base64或filename*或ISO编码的中文输出<br/>
	 * @param userAgent 浏览器引擎
	 * @param fileName 原来文件名
	 * @return 新的文件名
	 */
	public static String getFileNameForDown(String userAgent,String fileName) {
		String new_filename = fileName;
		try {
			new_filename = URLEncoder.encode(fileName, "utf-8");
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
		String rtn = "filename=\"" + new_filename + "\"";
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") != -1) {
				rtn = "filename=\"" + new_filename + "\"";
			}
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") != -1) {
				rtn = "filename*=utf-8''" + new_filename;
			}
			// Safari浏览器，只能采用ISO编码的中文输出
			else if (userAgent.indexOf("safari") != -1) {
				try {
					rtn = "filename=\"" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + "\"";
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
			// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			else if (userAgent.indexOf("applewebkit") != -1) {
				try {
					rtn = "filename=\"" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + "\"";
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") != -1) {
				rtn = "filename*=utf-8''" + new_filename;
			}
		}
		return rtn;
	}
	/**
	 * 	下载文件时，不同浏览器对中文文件名处理<br/>
	 *  1.IE浏览器，采用URLEncoder编码<br/>
	 *	2.Opera浏览器，采用filename*方式<br/>
	 *	3.Safari浏览器，采用ISO编码的中文输出<br/>
	 *	4.Chrome浏览器，采用Base64编码或ISO编码的中文输出<br/>
	 *	5.FireFox浏览器，采用Base64或filename*或ISO编码的中文输出<br/>
	 * @param fileName 原来文件名
	 * @return 新的文件名
	 */
	public static String getFileNameForDown(HttpServletRequest request,String fileName) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		return getFileNameForDown(userAgent,fileName);
	}
	
	public static byte[] inputStreamToByte(InputStream in){ 
        ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();     
        byte[] data = new byte[]{};
        try {
        	byte[] buffer = new byte[1024];
        	int len = -1;
        	while((len = in.read(buffer) ) != -1){ 
        		bAOutputStream.write(buffer,0,len); 
        	} 
        	data = bAOutputStream.toByteArray();
        	bAOutputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return data;
		}
        return data; 
    }
	
	/**
     * 复制文件
     * @param fromFile
     * @param toFile
     * <br/>
     * 2016年12月19日  下午3:31:50
     * @throws IOException 
     */
    public static void copyFile(File fromFile,File toFile){
        FileInputStream ins=null;
        FileOutputStream out=null;
		try {
			ins = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			byte[] b = new byte[1024];
	        int n=0;
	        while((n=ins.read(b))!=-1){
	            out.write(b, 0, n);
	        }
	        out.flush();
	        ins.close();
	        out.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
		}catch (IOException e) {
			logger.error(e.getMessage(),e);
		}finally{
			if(ins!=null){
				try {
					ins.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
    }
    /**
     * 
     * @param ls
     * @param colStr
     * @return
     */
    public static List<Map<String, Object>> lsToMapLs(List<Object[]> ls , String colStr){
		List<Map<String, Object>> retls= new ArrayList<Map<String,Object>>();
		try {
			String[] colarr =colStr.split(",");
			if(ls!=null && ls.size()>0){
				for(int l=0;l<ls.size();l++){
					Map<String, Object> objMap = new HashMap<String, Object>();
					Object[] obj=ls.get(l);
					if(obj.length>=colarr.length){
						for(int i=0;i<colarr.length;i++) {
							objMap.put(colarr[i], obj[i]);
						}
					}
					retls.add(objMap);
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return retls;
	}
    
    /**
     * 获取项目根目录(window)
     * @param request
     * @param pix
     * <br/>
     * 2017年12月19日  下午3:31:50
     * @throws IOException 
     */
    public static String  createRootPath(HttpServletRequest request,String pix){
    	String uploadPath = request.getSession().getServletContext().getRealPath("").substring(0,2);
		File root = new File(uploadPath+File.separator+pix);
		 if(!root.exists()){
			 root.mkdirs();
		 }
    	return root.getAbsolutePath();
    }
    /**
     * 获取字符串值
     * @autor pengmm
     * @date 2018-4-4上午10:07:18
     * @param map
     * @param key
     * @return
     */
    public static String getString(Map<String, ? extends Object> map,String key){
    	String result = "";
    	if (map == null || map.isEmpty()) {
    		result = "";
    		return result;
		}
    	Object object = map.get(key);
    	if (object != null) {
    		result = String.valueOf(object);
		}
    	if (isBlank(result)) {
			result = "";
		}
    	return result;
    }
    /**
     * 获取字符串值
     * @autor pengmm
     * @date 2018-4-4上午10:07:31
     * @param object
     * @return
     */
    public static String getString(Object object){
    	String result = "";
    	if (object != null) {
    		result = String.valueOf(object);
		}
    	if (isBlank(result)) {
			result = "";
		}
    	return result;
    }
    /**
     * 判读一个字符串是否包含另一个字符串
     */
    public static boolean contains(String str1,String str2){
    	if (CommUtil.isBlank(str1) || CommUtil.isBlank(str2)) {
			return true;
		}
    	if (str1.indexOf(str2) != -1) {
			return true;
		}
    	return false;
    }
    
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0); 
    
    public static synchronized String getUniqueKey(int iNum){
    	StringBuffer str = new StringBuffer();
    	NumberFormat numberFormat = new DecimalFormat("000000"); 
    	String  key = "";
    	if(iNum > 999999 ){
    		key = iNum + "";
    	}else{
    		key = String.valueOf(numberFormat.format(iNum, str, HELPER_POSITION)); 
    	}
    	return key;
    }
    /**
     * 敏感信息隐藏替换为"**"
     * @param info 原始信息
     * @param beginIndex 从第几个字符开始隐藏
     * @param count 隐藏字符个数
     * @return
     */
    public static String sensitiveInfoHiding(String info,int beginIndex,int count){
    	if (CommUtil.isBlank(info)) {
			return "";
		}
    	int minLength = beginIndex + count ;
    	if (info.length() < minLength) {
			return info;
		}
    	String replaceStr = "";
    	for (int i = 0; i < count; i++) {
    		replaceStr+="*";
		}
    	return info.substring(0,beginIndex-1)+replaceStr+info.substring(minLength-1, info.length());
    }
    /**
     * 敏感信息隐藏替换为"**"，默认从第7个字符开始，替换8个字符
     * @param info 原始信息
     * @return
     */
    public static String sensitiveInfoHiding(String info){
    	return sensitiveInfoHiding(info,7,8);
    }
    /**
     * 敏感信息隐藏替换为"**"
     * @param list 原始list
     * @param fileds 要隐藏的字段
     * @param beginIndex 从第几个字符开始隐藏
     * @param count 隐藏字符个数
     * @return
     */
    public static List<Map<String, Object>> sensitiveInfoHiding(List<Map<String, Object>> list,String fileds,int beginIndex,int count) {
    	if (null == fileds) {
    		fileds = "sfzh";
		}
    	String[] split = fileds.split(",");
    	for(Map<String, Object> map : list) {
    		for (String f : split) {
    			if (CommUtil.isKeyExist(map, f)) {
    				map.put(f.toUpperCase(), CommUtil.sensitiveInfoHiding(CommUtil.getString(map, f), beginIndex, count));
    			}
			}
		}
    	return list;
	}
    /**
     * 敏感信息隐藏替换为"**"，默认从第7个字符开始，替换8个字符
     * @param list 原始信息
     * @param fileds 原始要替换的字段
     * @return
     */
    public static List<Map<String, Object>> sensitiveInfoHiding(List<Map<String, Object>> list,String fileds){
    	return sensitiveInfoHiding(list,fileds,7,8);
    }
}
