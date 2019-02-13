package com.faceghost.elasticbg.base.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class FileUtil {
	
	/**
	 * 文件复制
	 * @date 2016年5月14日 下午2:22:17
	 * @param fileName 原文件
	 * @param newFile 新文件地址
	 * @return 复制成功 ture
	 */
	public static boolean copyFile(String fileName,String newFile){
		InputStream is = null;
		OutputStream os = null;
		boolean flag = false;
		try {
			is = new FileInputStream(new File(fileName));
			os = new FileOutputStream(new File(newFile));
			byte[] b = new byte[1024];
			int i = -1;
			while((i = is.read(b)) != -1){
				os.write(b,0,i);
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			close(os);
			close(is);
		}
		return flag;
	}
	
	
	public static boolean validateImage(File file){
		if(!file.exists()){
			return false;
		}
		Image img = null;
		try {
			img = ImageIO.read(file);
			if(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally{
			img = null;
		}
	}
	/**
	 * 关闭流
	 * @date 2016年5月25日 上午11:07:27
	 * @param ca
	 */
	private static void close(Closeable ca){
		try {
			if(ca != null){
				ca.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
