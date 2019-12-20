package com.faceghost.elasticbg.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.faceghost.elasticbg.base.shiro.ShiroUser;
import com.faceghost.elasticbg.base.statics.BaseSysConst;
import com.faceghost.elasticbg.base.vo.BaseVo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class BaseController {

    protected final String REDIRECT = "redirect:";
    protected final String FORWARD = "forward:";


    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;


    @ModelAttribute
    public void execPre(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public ShiroUser getLoginUser() {
        return (ShiroUser) session.getAttribute(BaseSysConst.systemSessionUser);
    }


    /**
     * 是否是Ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (this.isAjaxRequest(request)) {
            // 输出JSON
            BaseVo vo = new BaseVo();
            vo.setSuccess(Boolean.FALSE);
            vo.setMsg("您无权执行，请联系管理员授权！");
            PrintWriter out = null;
            try {
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpStatus.OK.value());
                response.setContentType("application/json; charset=utf-8");
                out = response.getWriter();
                out.write(JSONObject.toJSON(vo).toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            return null;
        } else {
            return "redirect:error/no_access";
        }
    }

}
