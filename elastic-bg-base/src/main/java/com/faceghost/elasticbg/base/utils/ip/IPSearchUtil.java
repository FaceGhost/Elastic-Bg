package com.faceghost.elasticbg.base.utils.ip;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.lang.reflect.Method;

public class IPSearchUtil {
	
	public static String getCityInfo (String ip){
		
		String path = IPSearchUtil.class.getResource("/ip2region.db").getPath();
		
        return getCityInfo(ip, path);
        
	}
	
	public static String getCityInfo (String ip,String dbPath){
		DataBlock dataBlock = null;
		
		try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);
           
            Method method = searcher.getClass().getMethod("btreeSearch", String.class);;
            
            
            if ( Util.isIpAddress(ip) == false ) {
               return "Error: Invalid ip address :" + ip;
            }else{
            	dataBlock = (DataBlock) method.invoke(searcher, ip);
            	searcher.close();
            	return dataBlock.getRegion();
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
		return null;
		
	}
	
	
    public static void main(String[] argv)
    {    
    	String ip = "120.85.77.178";
    	System.out.println(getCityInfo(ip));

    }
}
