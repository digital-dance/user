<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/taglibs.jspf"%>
<%@page import="java.lang.System"%>
<link rel="stylesheet" type="text/css" href="../../css/login/style.css" />
<run:header title="登录-输入信息"/>
    <body>
    <script  type="text/javascript" language="javascript">
    	function getSessionId(){
    		$.ajax({

    			type: "get",
    		       
    			url: "../../login/session/id",
                xhrFields:{withCredentials:true},
    			success: function(data){
    		 		document.getElementById("msgBox").innerHTML = JSON.stringify(data);
    		            alert(JSON.stringify(data));

    			}
    		     
    		});
    	}
    	
    	function login(){
    		var user = {};
    		//http://16.187.145.23/user/login/session_in
    		user.email = document.getElementById("userEmail").value;
    		user.password = document.getElementById("password").value;
    		user.isRemember = true;
    		$.ajax({

    			type: "post",
    			
    			url: "http://192.168.1.78:8008/user/login/user?clientType=3",
    			data: JSON.stringify(user),

    			contentType: "application/json; charset=utf-8",

    			dataType: "json",
    			xhrFields:{withCredentials:true},
    			success: function(ret){
    		 		document.getElementById("msgBox").innerHTML = JSON.stringify(ret);
    		            alert(JSON.stringify(ret));

    			}
    		     
    		});
    	}

    	function getHome(){
            		$.ajax({

            			type: "get",

            			url: "../../index/home",
                        xhrFields:{withCredentials:true},
            			success: function(data){
            		 		document.getElementById("msgBox").innerHTML = JSON.stringify(data);
            		            alert(JSON.stringify(data));

            			}

            		});
        }

        function getRemotetoken(){
        var userEmail = document.getElementById("userEmail").value;
                    		$.ajax({

                    			type: "get",

                    			url: "http://192.168.1.78:8008/user/login/remotetoken?token="+userEmail,
                                xhrFields:{withCredentials:true},
                    			success: function(data){
                    		 		document.getElementById("msgBox").innerHTML = JSON.stringify(data);
                    		            alert(JSON.stringify(data));

                    			}

                    		});
                }
    </script>
        <div class="login-bg">
<div id="msgBox"></div>
        </div>
        <div class="login-page-wrap mt40">
            <div class="form-wrap " id="loginForm">
                <form v-form name="myform">
                    <div class="form-group" v-input-delete>
                        <label class="form-label"><i class="u-icon icon-user-name">&nbsp;</i></label>
                        <input type="text" v-model="model.userName" id="userEmail" name="userEmail" placeholder="请输入您的用户名或手机号"
                               v-form-ctrl required class="form-text input-text" on-blurs="test">
                        <span class="icons-wrap">
                            <i class="u-icon icon-delete">&nbsp;</i>
                        </span>
                    </div>
                    <div class="form-group password-wrap mt15" v-input-delete>
			            <label class="form-label"><i class="u-icon icon-password">&nbsp;</i></label>
			            <input type="password" v-model="model.psw" id="password" name="password" v-form-ctrl required placeholder="请输入8-16位密码" class="form-text input-text password" on-blurs="test">
			            <span class="icons-wrap">
			                <i class="u-icon icon-delete">&nbsp;</i>
			                
			            </span>
			        </div>
			        <div class="mt10 text-right"><a href="../../login/forgetPassword.do" class="forget-password ">忘记密码？</a></div>
                    <div class="form-group button-group mt60">
                        <input v-bind:class="{'disabled': !myform.$valid}" class="btn-default hide " onclick="login()" type="button"  value="登陆"></button>
                        <br>
                        <input v-bind:class="{'disabled': !myform.$valid}" id="sessionId" class="btn-default hide " onclick="getSessionId()" value="会话" type="button"></button>
                        <br>
                        <input v-bind:class="{'disabled': !myform.$valid}" id="sessionId" class="btn-default hide " onclick="getHome()" value="home" type="button"></button>
                        <br>
                        <input v-bind:class="{'disabled': !myform.$valid}" id="sessionId" class="btn-default hide " onclick="getRemotetoken()" value="Remotetoken" type="button"></button>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script language="javascript" type="text/javascript">
		var scriptNode = document.createElement("script");
		scriptNode.setAttribute("type", "text/javascript");
		scriptNode.setAttribute("src", "../../js/jquery-1.4.2.min.js?" + new Date().getTime());
		document.body.parentNode.appendChild(scriptNode);
	</script>
    <!--<script type="text/javascript" src="${baseUrl}/user-war/js/jquery-1.4.2.min.js?<%=System.currentTimeMillis()%>"></script>-->
   
    
</html>
