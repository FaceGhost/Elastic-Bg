package com.faceghost.elasticbg.base.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 
 * 图片验证码
 */
public class CheckImageUtil {
	private ByteArrayInputStream image;//图像  
    private String str="";//验证码  
      
    private CheckImageUtil(){  
        init();//初始化属性  
    }  
    /* 
     * 取得RandomNumUtil实例 
     */  
    public static CheckImageUtil Instance(){  
        return new CheckImageUtil();  
    }  
    /* 
     * 取得验证码图片 
     */  
    public ByteArrayInputStream getImage(){  
        return this.image;  
    }  
    /* 
     * 取得图片的验证码 
     */  
    public String getString(){  
        return this.str;  
    }  
      
    private void init() {  
        //       在内存中创建图象  
        int width=100, height=40;  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        //       获取图形上下文  
        Graphics g = image.getGraphics();  
        //      生成随机类  
        Random random = new Random();  
        //       设定背景色  
        g.setColor(getRandColor(100,250));  
        g.fillRect(0, 0, width, height);  
        //      设定字体  
        g.setFont(new Font("微软雅黑",Font.PLAIN,15));  
        //       随机产生155条干扰线，使图象中的认证码不易被其它程序探测到  
        g.setColor(getRandColor(160,200));  
        for (int i=0;i<155;i++)  
        {  
         int x = random.nextInt(width);  
         int y = random.nextInt(height);  
         int xl = random.nextInt(200);  
         int yl = random.nextInt(200);
         g.drawLine(x,y,x+xl,y+yl);  
        }  
        //       取随机产生的认证码(4位数字)  
        String sRand="";  
        for (int i=0;i<4;i++){  
        	int j=random.nextInt(3);
        	String code;
        	if(j==0){
//        		int itmp = random.nextInt(26) + 65;
//                char ctmp = (char) itmp;
                code=String.valueOf(random.nextInt(9));
                
        	}else if(j==1){
        		code=String.valueOf(random.nextInt(9));
        	}else{
//        		int itmp = random.nextInt(26) + 97;
//                char ctmp = (char) itmp;
                code=String.valueOf(random.nextInt(9));
        	}
        	if(!"0".equals(code)&&!"o".equals(code)&&!"l".equals(code)&&!"1".equals(code)){
        		 sRand += code;
        		  g.setColor(new Color(20 + random.nextInt(110), 20 + random
        	              .nextInt(110), 20 + random.nextInt(110)));
        		  g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 20));
        	            g.drawString(code, 23 * i + 8, 28);
        	             this.str+=code;/*   赋值验证码   */  
        	}else{
        		i--;
        	}
           
          
        }  
        //       图象生效  
        g.dispose();  
        ByteArrayInputStream input=null;  
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        try{  
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);  
            ImageIO.write(image, "JPEG", imageOut);  
            imageOut.close();  
            input = new ByteArrayInputStream(output.toByteArray());  
        }catch(Exception e){  
            System.out.println("验证码图片产生出现错误："+e.toString());  
        }  
          
        this.image=input;/*  赋值图像  */  
    }  
    /* 
     * 给定范围获得随机颜色 
     */  
    private Color getRandColor(int fc,int bc){  
        Random random = new Random();  
        if(fc>255) fc=255;  
        if(bc>255) bc=255;  
        int r=fc+random.nextInt(bc-fc);  
        int g=fc+random.nextInt(bc-fc);  
        int b=fc+random.nextInt(bc-fc);  
        return new Color(r,g,b);  
   }  
}
