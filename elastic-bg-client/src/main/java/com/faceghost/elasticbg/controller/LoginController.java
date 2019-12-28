package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.controller.BaseController;
import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.service.SystemLogService;
import com.faceghost.elasticbg.base.service.SystemUserService;
import com.faceghost.elasticbg.base.shiro.ShiroKit;
import com.faceghost.elasticbg.base.shiro.ShiroUser;
import com.faceghost.elasticbg.base.statics.BaseSysConst;
import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import com.faceghost.elasticbg.base.statics.LogType;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.IPUtil;
import com.faceghost.elasticbg.base.utils.PasswordUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.BaseVo;
import com.faceghost.elasticbg.conf.PropConf;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 登录
 */
@Slf4j
@Controller
public class LoginController extends BaseController {

	@Autowired
	private SystemUserService systemUserService;

	@Autowired
	private PropConf propConf;

	@Autowired
	private SystemLogService systemLogService;

	private static final String clazzName = LoginController.class.getSimpleName();

	/**
	 * 系统登录时默认页面，直接重定向到登录页面。
	 * @return
	 */
	@GetMapping(value = "/")
	public String index(){
		return REDIRECT + "/login";
	}

	/**
	 * 登录页面跳转，如果已经登录则跳转至系统主界面"/main"
	 * @return
	 */
	@GetMapping(value = "/login")
	public String login(){
		//是否已经登录
		if(ShiroKit.getUser() != null || ShiroKit.isAuthenticated()){
			return REDIRECT + "/main";
		}
		return "login";
	}

	/**
	 * Ajax登录
	 * @param username 用户名
	 * @param password 密码
	 * @param validateCode 验证码
 	 * @return
	 */
	@PostMapping(value = "/login")
	@ResponseBody
	public BaseVo login(String username,String password,String validateCode) {
		String methodName = "login";
		BaseVo vo = new BaseVo();
		//尝试登录系统错误次数
		int retryNumber=0;
		//登录尝试失败N次数后冻结账户,0永不冻结
		int maxRetryNunber = propConf.getLoginMaxRetryNumber();
		SystemUser bean = null;
		try {	
			try {
				if(ValidateUtil.validateBlank(username) || ValidateUtil.validateBlank(password)){
					vo.setMsg("请输入用户名或密码");
					return vo;
				}else{
					//trim
					username = username.trim();
					password = password.trim();

					/*
					validateCode = (validateCode == null ? "" : validateCode.trim());
					if(!validateCode.equalsIgnoreCase((String) (session.getAttribute(SysConst.systemCheckCode)))){
						vo.setMsg("验证码输入错误");
						return vo;
					}
					*/

					Subject user = SecurityUtils.getSubject();
					bean = systemUserService.getSystemUserByUserName(username);
					//用户不存在
					if(bean == null) {
						throw new  UnknownAccountException();
					}
					//状态已冻结
					if(bean.getStatus() == 0 ){
						systemLogService.saveLog
								(
								LogType.LOGIN.getType(),
								String.valueOf(bean.getId()),
								"执行：登陆，失败：用户已被冻结",
								String.valueOf(bean.getId()),
								clazzName,
								methodName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
								);
						vo.setMsg("该用户已被冻结");
						return vo;
					}

					if(bean.getRetryNumber() != null && bean.getRetryNumber() <= 0){
						retryNumber = 1;
					}else{
						retryNumber = bean.getRetryNumber() + 1;
					}
					//明文密码---->暗文密码
					UsernamePasswordToken token = new UsernamePasswordToken(username,PasswordUtil.encryptPassword(password,bean.getId(),bean.getSalt()));
					//shiro执行登录
					user.login(token);
					systemUserService.updateByLogin(username, 1, 0,new Date());
					systemLogService.saveLog
							(
							LogType.LOGIN.getType(),
							bean.getId(),
							"执行：登陆，成功",
							String.valueOf(bean.getId()),
							clazzName,
							methodName,
							IPUtil.getInnerIpAddress(request),
							IPUtil.getOuterIpAddress(request)
							);


					ShiroUser shiroUser = ShiroKit.getUser();
					session.setAttribute(BaseSysConst.systemSessionUser, shiroUser);
					vo.setSuccess(Boolean.TRUE);
				}
				
			} catch (UnknownAccountException unknown) {
				vo.setMsg("用户名或密码错误");
				return vo;
			} catch (IncorrectCredentialsException incorrect) {
				String msg = "";
				//是否冻结账户
				if(maxRetryNunber > 0 ){
					msg = "用户名或密码错误，剩余次数: "+( maxRetryNunber - retryNumber );
					if(retryNumber >= maxRetryNunber){
						systemUserService.updateByLogin(username, 0, retryNumber,new Date());
					}else{
						systemUserService.updateByLogin(username, 1, retryNumber,new Date());
					}
				}else{
					msg = "用户名或密码错误";
				}
				systemLogService.saveLog
						(
						LogType.LOGIN.getType(),
						bean.getId(),
						"执行：登陆，失败：用户名或密码错误",
						String.valueOf(bean.getId()),
						clazzName,
						methodName,
						IPUtil.getInnerIpAddress(request),
						IPUtil.getOuterIpAddress(request)
						);

				vo.setMsg(msg);
				return vo;
			}
		} catch (Exception e) {
			vo.setMsg(ErrorMsgConst.errSys);
			log.error(String.format("执行：登录，异常：%s", ExceptionUtil.getExDetail(e)));
			if(bean != null){
				ShiroKit.getSubject().logout();
				systemLogService.saveLog
						(
								LogType.LOGIN.getType(),
								bean.getId(),
								"执行：登陆，异常："+ ExceptionUtil.getExDetail_log(e),
								String.valueOf(bean.getId()),
								clazzName,
								methodName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
						);


			}
			return vo;
		}
		return vo;
    }

	/**
	 * 退出登录
	 */
	@GetMapping(value = "/logout")
	public String logOut() {
		String methodName = "logOut";
		if(getLoginUser() != null){
			systemLogService.saveLog
				(
						LogType.LOGIN.getType(),
						String.valueOf(getLoginUser().getId()),
						"执行：退出登录",
						String.valueOf(getLoginUser().getId()),
						clazzName,
						methodName,
						IPUtil.getInnerIpAddress(request),
						IPUtil.getOuterIpAddress(request)
				);
		}
		ShiroKit.getSubject().logout();
		return REDIRECT + "/login";
	}

}
