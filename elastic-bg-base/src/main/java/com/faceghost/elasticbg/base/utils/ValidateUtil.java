package com.faceghost.elasticbg.base.utils;



import java.util.regex.Pattern;


/**
 * <pre>
 * 类名称    : ValidateUtils
 * 类描述    : 验证某一字符串是否符合正则表达式
 * </pre>
 */
public class ValidateUtil{
	
	/**
	 * 验证某一字符串是不是   null or ""
	 * @param src 验证的字符串
	 * @return true or false
	 */
	public static boolean validateBlank(String src){
		return (src == null) || src.trim().equals("") ;
	}
	
	/**
	 * 验证是不是合法email
	 * @param email 验证的email
	 * @return
	 */
	public static boolean validateEmail(String email){
		String regex = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
		if(!validateBlank(email)){
			return Pattern.compile(regex).matcher(email).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * 验证是不是合法的手机号
	 * @param phone 验证的手机号
	 */
	public static boolean validatePhone(String phone){
		String regex = "^1([3-8]){1}\\d{9}$";
		if(!validateBlank(phone)){
			return Pattern.compile(regex).matcher(phone).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * 验证是不是合法的固话
	 * @param tel 验证的固话
	 * @return
	 */
	public static boolean validateTel(String tel){
		String regex = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
		if(!validateBlank(tel)){
			return Pattern.compile(regex).matcher(tel).matches();
		}else{
			return false;
		}
	}
	/**
	 * 验证是不是合法的邮编
	 * @param postCode 验证的邮编
	 * @return
	 */
	public static boolean validatePostCode(String postCode){
		String regex = "^\\d{6}$";
		if(!validateBlank(postCode)){
			return Pattern.compile(regex).matcher(postCode).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * 验证中文汉字
	 * @param chinese 验证的字符
	 * @param min 最低
	 * @param max 最多
	 * @return
	 */
	public static boolean validateChinese(String chinese,int min,int max){
		String regex = "^[\u4E00-\u9FA5\uf900-\ufa2d]{"+min+","+max+"}$";
		if(!validateBlank(chinese)){
			return Pattern.compile(regex).matcher(chinese).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * 验证英文字符串
	 * @param name 验证的字符
	 * @param min 最低
	 * @param max 最多
	 * @return
	 */
	public static boolean validateWorld(String name,int min,int max){
		String regex = "^\\w{"+min+","+max+"}$";
		if(!validateBlank(name)){
			return Pattern.compile(regex).matcher(name).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * 验证是不是常见图片格式
	 * @param name
	 * @return
	 */
	public static boolean ValidateImage(String name){
		String regex = "^(.*)\\.(jpg|png|bmp|jpeg)$";
		if(!validateBlank(name)){
			name = name.trim().toLowerCase();
			return Pattern.compile(regex).matcher(name).matches();
		}else{
			return false;
		}
	}
	/**
	 * text文件
	 * @param name
	 * @return
	 */
	public static boolean ValidateText(String name){
		String regex = "^(.*)\\.(txt)$";
		if(!validateBlank(name)){
			name = name.trim().toLowerCase();
			return Pattern.compile(regex).matcher(name).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * doc文件
	 * 判断是否为word文件
	 */
	public static boolean ValidateDoc(String name){
		String regex = "^(.*)\\.(doc|docx)$";
		if(!validateBlank(name)){
			name = name.trim().toLowerCase();
			return Pattern.compile(regex).matcher(name).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * 判断是否为excel
	 * @param str
	 * @return
	 */
	public static boolean ValidateExcel(String str){
		String regEx4="^(.*)\\.(xls|xlsx)$";
		boolean flag=false;
		if(!validateBlank(str)){
			for(int i=0;i<str.length();i++){
				flag=Pattern.compile(regEx4).matcher(str.trim().toLowerCase()).matches();
				if(flag==false){
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 验证是否为数字
	 * @date 2016年4月20日 下午7:04:08
	 * @param str
	 * @return
	 */
	public static boolean validateDouble(String str){
		//^[1-9]+[0-9]*(.?)[0-9]+$|^[0-9]$|^0.[0-9]+$
		String reg = "[1-9]+[0-9]*(.?)[0-9]+$|[0-9]|0.[0-9]+";
		if(!validateBlank(str)){
			return Pattern.compile(reg).matcher(str.trim()).matches();
		}else{
			return false;
		}
	}
	
	/**
	 * 验证是否为正整数
	 * @date 2016年4月21日 上午8:42:31
	 * @param str
	 * @return
	 */
	public static boolean validateInteger(String str){
		String reg = "[^0][0-9]+|[0-9]";
		if(!validateBlank(str)){
			return Pattern.compile(reg).matcher(str.trim()).matches();
		}else{
			return false;
		}
	}

	/**
	 * 是不是合法的IP
	 * @param str
	 * @return
	 */
	public static  boolean validateIp(String str){
		String reg = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		if(!validateBlank(str)){
			return Pattern.compile(reg).matcher(str.trim()).matches();
		}else{
			return false;
		}
	}


	
}
