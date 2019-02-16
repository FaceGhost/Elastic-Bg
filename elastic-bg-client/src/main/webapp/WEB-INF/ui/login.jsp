<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./includes/meta.jsp" %>
<!DOCTYPE HTML >
<html>
  <head>
    <title>Elastic-Bg&nbsp;通用后台管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style>
	  body {font-size: 12px;line-height: normal;margin: 0;display: block;font-family: -apple-system,PingFang SC,Hiragino Sans GB,Microsoft YaHei,Helvetica Neue,Arial,sans-serif;text-rendering: optimizeLegibility;color: #333;}
	  .login_wrapper {position: relative;margin: 0 auto;width: 100%;max-width: 960px;}
	  .login_wrapper:after {display: table;content: "";clear: both;}
	  .login_wrapper .login_form {border-radius: 10px;box-shadow: 0 0px 2px #CCC;margin:0 auto;margin-top: 120px !important;width: 450px;padding: 40px 60px 0;background-color: #f4f5f5;box-sizing: border-box;}
	  .login_wrapper .login_form .logo{text-align: center; margin: 0 auto; padding-bottom: 10px;font-size: 25px;color:#6699CC;}
	  .login_wrapper .login_form .login_title{font-size: 12px;border-radius: 2px;box-sizing: border-box;}
	  .login_wrapper .login_form .login_title h1{margin: 0;font-weight: 300;text-align: left;line-height: 1.5;font-size: 22px;}
	  .login_wrapper .login_form .login_body{margin-top: 20px;text-align: center;}
	  .login_wrapper .login_form .login_body .login_input{width:94%;margin: 0;padding: 12px 8px;font-size:14px; outline: none;border: 1px solid #dddddd;line-height: 1.4;color: #222222;}
	  .login_wrapper .login_form .login_body .top{border-radius: 5px 5px 0 0;margin-bottom: 0px;}
	  .login_wrapper .login_form .login_body .bot{border-radius: 0 0 5px 5px;border-top: 0;}
	  .login_wrapper .login_form .login_body .submit_btn{margin-top: 25px;padding: 8px 0px;width: 100%;display: inline-block;cursor: pointer;color: #fff; background-color: #6699CC; border-radius: 4px;border: 1px solid transparent;}
	  .login_wrapper .login_form .login_bot{padding-bottom: 10px;margin: 30px auto 2px;text-align: center;font-size: 12px;}
	  .login_wrapper .login_form .login_bot a{color:#333;}
    </style>
	<script type="text/javascript" src="${basepath}static/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="${basepath}static/js/jquery.placeholder.js"></script>
	<script type="text/javascript">
		var base = '${basepath}';
		//登录页面不可以在iframe中
        if (self != top) {
            window.top.location.href = base
        }

        /**
		 * 登录
		 */
        function execLogin(bol){
            /**
             * IE支持需要placeholder,直接用$.ajax
             */
			var username = $('input[name="username"]').val();
			var password = $('input[name="password"]').val();
			var ing  =   $('input[name="submit_btn"]').attr('ing');
			if(ing == 'false'){
                if($.trim(username) == '' || $.trim(password) == ''){
                    $('input[name="submit_btn"]').val('请输入账户名和密码');
                    return;
                }else{
                    $('input[name="submit_btn"]').val('确定');
				}
				if(bol){
                    $('input[name="submit_btn"]').attr('ing','true');
                    $('input[name="submit_btn"]').val('登录中');
                    $.ajax({
                        type:'POST',
                        url: base + 'login',
                        data:{
                            'username': username ,
                            'password': password
                        },
                        cache:false,
                        success:function(data){
                            $('input[name="submit_btn"]').attr('ing','false');
                            if(data == null){
                                $('input[name="submit_btn"]').val('系统繁忙，请稍后重试！');
                            }
                            if(data != null && data.success){
                                window.location = base + 'main';
                            }else{
                                $('input[name="submit_btn"]').val(data.msg);
                            }
                            setTimeout(function(){
                                $('input[name="submit_btn"]').val('确定');
							},1000)
                        }
                    });
				}
			}

		}
        /**
		 * 回车登录
         */
        $(document).keydown(function(event){
            if(event.keyCode == "13"){
                execLogin(true);

            }
        });

        $(function(){
            $('input, textarea').placeholder({customClass:'placeholderCls'});

            $('input[name="username"]').on('input',function(){execLogin(false)});
            $('input[name="password"]').on('input',function(){execLogin(false)});
            $('.submit_btn').click(function(){execLogin(true)});

		});
	</script>
  </head>
  <body>
  <div class="login_wrapper">
	  <div class="login_div">
		  <form class="login_form">
			  <div class="logo">
				  Elastic-Bg演示系统
			  </div>
			  <div class="login_title">
				  <h1>登录</h1>
			  </div>
			  <div class="login_body">
				  <div  class="login_input_div">
					  <input  placeholder="请输入账户名" value="" name="username" type="text" class="login_input top username" />
				  </div>
				  <div  class="login_input_div">
					  <input  placeholder="请输入密码" value="" name="password" type="password"  class="login_input bot password" />
				  </div>
				  <input ing="false" type="button" name="submit_btn"  class="login_input submit_btn"  value="确定"/>
			  </div>
			  <div class="login_bot">
				  演示系统&nbsp;<a target="_blank" href="https://faceghost.com">见鬼网</a>&nbsp;提供，技术服务请关注公众号：见鬼网
			  </div>
		  </form>
	  </div>
  </div>
  </body>
</html>
