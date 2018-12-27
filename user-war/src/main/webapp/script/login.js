var userName_hint = "邮箱/手机号码";
var pass_hint = '密码';
var loginTitle_const_from_user = '欢迎使用数字脉动';
var loginTitle_const_from_other = '您尚未登录数字脉动账户';

$(document).ready(function () {//alert(0);
    try {
        if (document.URL.indexOf('activityInfo.html') > -1) {
            $('#loginTitle').text(loginTitle_const_from_other);
        }
        else {
            $('#loginTitle').text(loginTitle_const_from_user);
        }
    }
    catch (e)
        { }


    $("#txtPass")
        .bind("blur", function () {
            //$(this).removeClass().addClass("inputStyleNone");
            //$('#errorTip').hide();
            if ($(this).val() == pass_hint || $(this).val() == "") {
                $("#txtPass").val(pass_hint);
                //$(this).attr('type', 'text');
                document.getElementById("txtPass").type = 'text';
            }
        })
        .bind("focus", function () {
            if ($(this).val() == pass_hint || $(this).val() == "") {
                $("#txtPass").val("");
            }
            //$(this).attr('type', 'password');
            document.getElementById("txtPass").type = 'password';

            //$(this).removeClass().addClass("inputStyleNone");
            //$('#errorTip').hide();
        });

    $("#txtLoginName")
        .bind("blur", function () {

            if ($(this).val() == userName_hint || $(this).val() == "") {
                $("#txtLoginName").val(userName_hint);
                $(this).attr('class', "inputStyleNone errorNote");
                $('#errorTip').show();
            }
        })
        .bind("focus", function () {
            if ($(this).val() == userName_hint || $(this).val() == "") {
                $("#txtLoginName").val("");
            }
            $(this).removeClass().addClass("inputStyleNone");
            $('#errorTip').hide();
        });

    $("#btnLogin")
        .bind("click", function () {
            var username = ($("#txtLoginName").val())
              , password = ($("#txtPass").val())
              , remember = false;
            if (username.length <= 0 || password.length <= 0 || username == userName_hint || password == pass_hint) {
                $("#txtLoginName").attr('class', "inputStyleNone errorNote");
                //$('#errorTip').text("用户名或密码不能为空！");
                $('#errorTip').show();
                return false;
            }
						alert(escape(password));
            $.ajax({
						type: "POST",
						url: "http://192.168.28.255:8008/user/login/user",
						data: JSON.stringify({﻿"email":escape(username),﻿"password":escape(password),"isRemember": remember}),
						contentType: "application/json; charset=utf-8",
						dataType: "json",
						success:function (data, status) {

							var datas = data.result;
//							﻿alert(data.code);
//                          alert( (data.result.redirect) );

							switch (data.code) {
									case "10000":
											$(this).attr('class', "inputStyleNone");
											$('#errorTip').hide();
											// var returnUrl = wapDecode(getQueryStringValue('returnUrl'));
											var returnUrl = data.result.redirect;
											if (returnUrl == null || returnUrl == '') {
													document.location.href = "/user/register.html";
											}
											else {
													document.location.href = decodeURI(returnUrl);
											}
											break;
									case "10001":
											alert("用户没有通过邮箱验证!");
											break;
									case "10003":
											alert("用户没有访问权限!");
											break;
									default:
											$("#txtLoginName").attr('class', "inputStyleNone errorNote");
											$('#errorTip').show();
							}
						}	
            });
        });
});