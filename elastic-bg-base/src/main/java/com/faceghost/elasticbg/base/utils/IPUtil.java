package com.faceghost.elasticbg.base.utils;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {

    /**
     *
     * @param request
     * @return
     */
    public static String getInnerIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "0";
        }
        String ip = request.getRemoteAddr();
        /**
         * ip是否合法
         */
        if (!ValidateUtil.validateIp(ip)) {
            ip = "0";
        }
        return ip;
    }
    /**
     * 获取外网IP
     * @param request
     * @return
     */
    public static String getOuterIpAddress(HttpServletRequest request) {
        if(request == null){
            return "0";
        }
        String ip = request.getHeader("x-forwarded-for");
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

        /**
         * ip是否合法
         */
        if (!ValidateUtil.validateIp(ip)) {
            ip = "0";
        }
        return ip;
    }


}
