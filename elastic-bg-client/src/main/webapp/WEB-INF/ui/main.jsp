<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="./includes/meta.jsp" %>
<%@ page import="com.faceghost.elasticbg.base.statics.BaseSysConst" %>
<%@ page import="com.faceghost.elasticbg.base.shiro.ShiroUser" %>
<%@ page import="com.faceghost.elasticbg.statics.JsCssVersionConst" %>
<%--加载该页面时，执行doGetAuthenticationInfo进行加载权限认证信息 --%>
<shiro:hasPermission name="load:doGetAuthorizationInfo"/>
<!DOCTYPE html >
<html>
  <head>
  <title>Elastic-Bg通用后台管理系统</title>
  <meta charset="utf-8" />
  <style>
	    #loading-mask{ background-color:white;height:100%;position:absolute;left:0;top:0;width:100%;z-index:20000;}
	    #loading{height:auto;position:absolute;left:45%;top:40%;padding:2px;z-index:20001;}
	    #loading .loading-indicator{ background:white;color:#444;font:bold 13px Helvetica, Arial, sans-serif;height:auto;margin:0;padding:10px;}
	    #loading-msg {font-size: 10px;font-weight: normal;}
		.layout_top_div {height: 60px; background-image: url(static/image/blue.png);}
		.layout_top_div_left {float: left;}
		.layout_top_div_left #layout_logo_txt{color: #6699CC; width: 300px;font-size: 20px;position: absolute;top: 13px;text-indent: 10px;}
		.layout_top_div_right {float: right;padding-right: 5px;padding-top: 5px;position: absolute;right: 0px;top: 0px;}
		.layout_top_div_right .row{line-height: 25px;color: #6699CC;height: 30px;}
		.layout_top_div_right .row .hi{font-size: 10px;white-space: nowrap;float: right;}
		.layout_top_div_right .row .hi .hit{color: #ef5e5e;font-size: 13px;}
		.layout_top_div_right .row a{color: #6699CC;text-decoration: none;}
		.layout_top_div_right .row a:hover{cursor: pointer;}
		.layout_bottom_div {color: #6699CC;padding-right: 10px;padding-top: 1px;text-align: center;width: 100%;position: absolute;right: 0px;top: 0px;}
  </style>
  </head>
  <body>
	 	 <div id="loading-mask" style=""></div>
		 <div id="loading">
	        <div class="loading-indicator">
	            <img src="${basepath}static/image/extanim32.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>Elastic-Bg综合管理系统
	            <br /><span id="loading-msg">Loading styles and images...</span>
	        </div>
	     </div>
		 <div id="div_center">
			<iframe id="iframe_main" src="welcome.html" width="100%" height="100%" style="min-height:500px;" frameborder="0" ></iframe>
		</div>
		
			<div id="layout_top_div" class="layout_top_div" >
				<div style="width: 100%;">
						<div class="layout_top_div_left">
							<label id="layout_logo_txt">Elastic-Bg&nbsp;基础框架</label>
						</div>
						<div class="layout_top_div_right">
							<div class="row">
								<label class="hi">你好 、<span class="hit"><%= ((ShiroUser)session.getAttribute(BaseSysConst.systemSessionUser)).getName()%></span> 、欢迎使用本系统</label>
							</div>
							<div class="row">
									<a id="userInfoSetting" rid="<%=((ShiroUser)session.getAttribute(BaseSysConst.systemSessionUser)).getId()%>"><i class="fa fa-github-square"></i> 个人设置</a>
									|
									<a id="changePwd" ><i  class="fa fa-key"></i> 修改密码</a>
									|
									<a><i class="fa fa-power-off"></i> <a href="logout">退出</a></a>						
							</div>
						</div>
			</div>
		</div>
		<div id="layout_bottom_div" class="layout_bottom_div" >
				<i class="fa fa-copyright"></i> 2019 Elastic-Bg
		</div>
	</body>
    <base href="${basepath}">
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = 'Loading UI样式 ...';</script>
	<link rel="stylesheet" type="text/css" href="${basepath}static/js/extjs/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="${basepath}static/css/awesome/css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="${basepath}static/css/ext_override.css" />
	<script type="text/javascript">document.getElementById('loading-msg').innerHTML = 'Loading UI组件 ...';</script>
	<script type="text/javascript" src="${basepath}static/js/extjs/ext-all.js" ></script>
	<script type="text/javascript" src="${basepath}static/js/extjs/ux/TabCloseMenu.js"></script>
	<script type="text/javascript" src="${basepath}static/js/extjs/locale/ext-lang-zh_CN.js"></script>
   	<script type="text/javascript">
		Ext.Loader.setConfig({
			enabled: true,
			paths: {
				'Ext.ux': '${basepath}static/js/extjs/ux'
			}
		});
		var base = ${basepath};
	</script>
	<script type="text/javascript">document.getElementById('loading-msg').innerHTML = 'Loading 初始化 ...';</script>
    <script type="text/javascript" src="${basepath}static/uijs/viewport.js?v=<%=JsCssVersionConst.getJsVersion()%>"></script>
    <script type="text/javascript" src="${basepath}static/uijs/changePwd.js?v=<%=JsCssVersionConst.getJsVersion()%>"></script>
    <script type="text/javascript" src="${basepath}static/uijs/userInfoSetting.js?v=<%=JsCssVersionConst.getJsVersion()%>"></script>

</html>