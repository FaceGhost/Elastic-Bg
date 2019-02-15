<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.faceghost.elasticbg.statics.JsCssVersionConst" %>
<!DOCTYPE html >
<html>
  <head>
 	  <base href="${basepath}">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	  <style>
		#loading-mask{background-color:white;height:100%;position:absolute;left:0;top:0;width:100%;z-index:20000;}
		#loading{height:auto;position:absolute;left:45%;top:40%;padding:2px;z-index:20001;}
		#loading .loading-indicator{ background:white;color:#444;font:bold 13px Helvetica, Arial, sans-serif;height:auto;margin:0;padding:10px;}
		#loading-msg {font-size: 10px;font-weight: normal;}
	   </style>
  </head>
  <body>
	<div id="loading-mask" style=""></div>
	<div id="loading">
	     <div class="loading-indicator">
	         <img src="${basepath}static/image/extanim32.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>
	         <br /><span id="loading-msg">Loading ...</span>
	     </div>
	</div>
   </body>
   <link rel="stylesheet" type="text/css" href="${basepath}static/js/extjs/resources/css/ext-all.css" />
   <link rel="stylesheet" type="text/css" href="${basepath}static/css/awesome/css/font-awesome.min.css" />
   <link rel="stylesheet" type="text/css" href="${basepath}static/css/ext_override.css" />
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/js/extjs/ext-all.js" ></script>
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/js/extjs-override/Js.ux.DatePicker.js" ></script>
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/js/extjs-override/js.ux.TreeCombo.js" ></script>
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/js/extjs-override/ComboBoxTree.js" ></script>
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/js/extjs-override/GridPanel.js" ></script>
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/js/extjs/ux/TabCloseMenu.js"></script>
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/js/extjs/locale/ext-lang-zh_CN.js"></script>
   <script type="text/javascript" charset="UTF-8" src="${basepath}static/uijs/app.js?v=<%=JsCssVersionConst.getJsVersion()%>"></script>
   <script type="text/javascript">
	Ext.Loader.setConfig({
		enabled: true,
		paths: {
			'Ext.ux': '${basepath}static/js/extjs/ux'
		}
	});
	var base = ${basepath};
	//ajax session超时跳转至登录页面
    Ext.Ajax.addListener("requestcomplete",function(conn, response, options, eOpts){
        var msg = response.getAllResponseHeaders();
        if(msg['sessionstatus'] == 'timeout'){
            window.top.location.href = base + 'login';
        }
    },this);
  </script>
</html>