package com.shareyuan.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : kent
 * @Description : 字符串工具类
 * @Date : 11:40 2019/9/17
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * @Author : kent
     * @Description : 获取ip地址
     * @Date : 11:39 2019/9/17
     * @Params : request
     * @Return: String
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String[] ips = ip.split(",");
        return "0:0:0:0:0:0:0:1".equals(ips[0])?"127.0.0.1":ips[0];
    }

}
