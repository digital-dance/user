var userName_hint = "邮箱/手机号码";
var userName_tips_hint = "请输入邮箱/手机号码";
var group_tips_hint = '个人用户无需填写';
var password_tips_hint = '6-20位数字、字母、字符';
var input_authcode_tips_hint = "请输入验证码";

var user_name_input_tips = '请填写邮箱/手机号码';
var user_name_type_tips = '只能填写邮箱/手机号码';
var user_name_not_null = '用户名不能为空';
var user_name_max_length = '用户名不能超过50个字符';

function checkPass(v) {
    var flag = true;
    if ("" == v) {
        showTips($("#txtRegPass"), $("#passTips"), false, "请输入密码"); 
        //$("#txtRegPass").attr('class', "inputStyle errorNote");
        flag = false;
    } else if (v.length < 6) {
        showTips($("#txtRegPass"), $("#passTips"), false, "密码太短了，最少6位"); 
        //$("#txtRegPass").attr('class', "inputStyle errorNote");
        flag = false;
    } else if (v.length > 20) {
        showTips($("#txtRegPass"), $("#passTips"), false, "密码太长了，最多20位"); 
        //$("#txtRegPass").attr('class', "inputStyle errorNote");
        flag = false;
    } else if (-1 != v.indexOf(" ")) {
        showTips($("#txtRegPass"), $("#passTips"), false, "密码不能包含空格"); 
        //$("#txtRegPass").attr('class', "inputStyle errorNote");
        flag = false;
    } else if (v == $("#txtRegUserName").val()) {
        //$("#txtRegPass").attr('class', "inputStyle errorNote");
        showTips($("#txtRegPass"), $("#passTips"), false, "密码不能和用户名相同，容易被人猜到"); 
        flag = false;
        //showTips($("#passTips"), false, "密码不能和<b>" + $('#spanUser').html() + "</b>相同，容易被人猜到"); flag = false;
    } else if (/^[0-9]{6}$/i.test(v)) {
        showTips($("#txtRegPass"), $("#passTips"), false, "密码不能用7位以下的纯数字，容易被人猜到"); 
        flag = false;
    } else if (/^(.)\1{5,}$/i.test(v)) {
        //$("#txtRegPass").attr('class', "inputStyle errorNote");
        showTips($("#txtRegPass"), $("#passTips"), false, "密码不能用重复的字符，容易被人猜到"); 
        flag = false;
    } else {
        showTips($("#txtRegPass"), $("#passTips"), true, "cancel");
    }
    return flag;
}

function checkRePass(v) {
    var flag = true;
    if ("" == v) {
        showTips($("#txtRepass"), $("#repassTips"), false, "请再次输入密码"); 

        flag = false;
    } else if (v != $("#txtRegPass").val()) {
        showTips($("#txtRepass"), $("#repassTips"), false, "与设置的密码不一致"); 

        flag = false;
    } else {
        showTips($("#txtRepass"), $("#repassTips"), true, "cancel");
    }
    return flag;
}

/* 显示提示消息 */
function showTips(box, tip, ico, msg) {
    //tip = $("#userTips");
    if (msg == 'cancel') {
        if (box != null) {
            box.attr('class', "inputStyle");
        }
        tip.text('');
        tip.hide();
        return;
    }

    if (ico) {
        //tip.css('background', 'url(../images/gou.png) no-repeat');
        if (box != null) {
            box.attr('class', "inputStyle");
        }
        //tip.css('color', '#9FDF0A');
    }
    else {
        //tip.css('background', 'url(../images/no.png) no-repeat');
        //tip.css('color', '#ff0000');
        if (box != null) {
            box.attr('class', "inputStyle errorNote");
        }
    }
    tip.text(msg);
    tip.show();
}

function showDefault(box, tip, msg) {
    if (box != null) {
        box.attr('class', "inputStyle");
    }
    tip.text(msg);
    tip.show();
}


function checkUserName(box, v) {
    var flag = true;
    if ("" == v || v == userName_hint) {
        showTips(box, $("#userTips"), false, user_name_input_tips); 
        //box.attr('class', "inputStyle errorNote");
        flag = false;
    } else if (!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i.test(v)
                && !/^(13[0-9]|15[0-9]|18[0-9])([0-9]{8})$/i.test(v)) {
        showTips(box, $("#userTips"), false, user_name_type_tips); 
        //box.attr('class', "inputStyle errorNote");
        flag = false;
        //} else if (!/^[_a-z0-9]{3,16}$/i.test(v)) {
        //    showTips($("#userTips"), false, "用户名由3-16位字母、数字或下划线构成"); flag = false;
        //} else if (!/^[_a-z0-9]{3,16}$/i.test(v)) {
        //    showTips($("#userTips"), false, "用户名由3-16位字母、数字或下划线构成"); flag = false;
        //} else if (!/^[a-z]/i.test(v)) {
        //    showTips($("#userTips"), false, "用户名只能以字母开头"); flag = false;
        //} else if (/_$/.test(v)) {
        //    showTips($("#userTips"), false, "为了您方便记忆，末尾不要用下划线"); flag = false;
        //} else if (-1 != v.indexOf("admin")) {
        //    showTips($("#userTips"), false, "用户名不能包含admin"); flag = false;
    }
    else if (v.length > 50) {
        showTips(box, $("#userTips"), false, user_name_max_length); 
        //box.attr('class', "inputStyle errorNote");
        flag = false;
    }

    return flag;
}

$(document).ready(function () {

    function closeRegPage() {
        $("#txtRegUserName").attr('class', "inputStyle");
        $("#txtRegUserName").val(userName_hint);
        showDefault($("#txtRegUserName"), $("#userTips"), "");
        $("#txtRegPass").val("");
        showDefault($("#txtRegPass"), $("#passTips"), "");
        $("#txtRepass").val("");
        showDefault($("#txtRepass"), $("#repassTips"), "");
        $("#textGroupCode").attr('class', "inputStyle1");
        $("#textGroupCode").val(group_tips_hint);
    }

    /* 注册 用户名*/
    $("#txtRegUserName")
        .bind("blur", function () {
            if ($(this).val() == userName_hint || $(this).val() == "") {
                //                $(this).attr('class', "inputStyle errorNote");
                $(this).val(userName_hint)
                //$("#userTips").html("邮箱/手机号码");
                showTips($("#txtRegUserName"), $("#userTips"), false, "用户名不能为空");
                //                $("#userTips").text("用户名不能为空");
                //                $("#userTips").show();
            }
            else {
                showTips($("#txtRegUserName"), $("#userTips"), true, "cancel");
                if (checkUserName($(this), $(this).val())) {
                    //$("#userTips").html("正在验证...");
                    $.post("RegCheckUser.aspx?key=" + Math.random() + "&v=" + (+new Date()),
                        {
                            type: 1,
                            user: $(this).val()
                        },
                        function (data, status) {
                            switch (data) {
                                case "0":
                                    //showTips($("#txtRegUserName"), $("#userTips"), true, "恭喜，可以注册");
                                    //alert("恭喜，可以注册");
                                    break;
                                case "-2":
                                    showTips($("#txtRegUserName"), $("#userTips"), false, "该用户已存在，请重新输入");
                                    //alert("该用户已存在，请重新输入");
                                    break;
                                case "-3":
                                    alert("非法访问！");
                                    break;
                                case "-5":
                                    showTips($("#txtRegUserName"), $("#userTips"), false, "用户名不能超过50个字符！");
                                    //alert("用户名不能超过50个字符！");
                                    break;
                            }
                        });
                }
            }
        })
        .bind("focus", function () {
            //$(this).attr('class', "inputStyle");
            showTips($(this), $("#userTips"), true, "cancel");
            if ($(this).val() == userName_hint || $(this).val() == "") {
                $(this).val("");
                //showTips($(this), $("#userTips"), userName_tips_hint);
            }
            else {
                //showTips($(this), $("#userTips"), true, "cancel");
            }
        });
    /* 注册 密码*/
    $("#txtRegPass")
        .bind("blur", function () {
            if (checkPass($(this).val()))
                showTips($(this), $("#passTips"), true, "cancel");
        })
        .bind("focus", function () {
            //$("#txtRegPass").attr('class', "inputStyle");
            showTips($(this), $("#passTips"), true, "cancel");
            if ($(this).val() == "") {
                //showDefault($(this), $("#passTips"), password_tips_hint);
            }
        });
    /* 注册 确认密码*/
    $("#txtRepass")
        .bind("blur", function () {
            if (checkRePass($(this).val())) {
                showTips($(this), $("#repassTips"), true, "cancel");
            }
        })
        .bind("focus", function () {
            //$("#txtRepass").attr('class', "inputStyle");
            showTips($(this), $("#repassTips"), true, "cancel");
            if ($(this).val() == "") {
                //$("#repassTips").text("请再次输入密码");
                //$("#repassTips").show();
            }
        });

    /* 注册 集团代码*/
    $("#textGroupCode")
        .bind("blur", function () {
            if ($(this).val() == group_tips_hint || $(this).val() == "") {
                $(this).attr('class', "inputStyle1");
                $(this).val(group_tips_hint);
            }
        })
        .bind("focus", function () {
            $(this).attr('class', "inputStyle1");
            if ($(this).val() == group_tips_hint) {
                $(this).val("");
            }
        });

    /* 注册 立即注册*/
    $("#btnReg")
        .bind("click", function () {
            //alert(0);
            var username = ($("#txtRegUserName").val())
              , pass = ($("#txtRegPass").val())
              , repass = ($("#txtRepass").val())
              , authCode = "wap"
              , txtGroupCode = ($("#textGroupCode").val())
              , agrent = ($("#chkAgrent").attr("checked")); //alert(1);
            if (checkSubmit()) {
                //alert(2);
                $.post("RegCheckUser.aspx",
                  {
                      type: 0,
                      user: username,
                      pass: pass,
                      repass: repass,
                      authCode: authCode,
                      groupCode: txtGroupCode,
                      agrent: agrent
                  },
                  function (data, status) {
                      switch (data) {
                          case "0":
                              //showTips($("#userTips"), false, "");
                              //popupMsg("恭喜成功注册为美亚道路安全行用户!<br>您可以用该账号登录美亚道路安全行网页。");
                              alert("恭喜成功注册为美亚道路安全行用户!\r\n您可以用该账号登录美亚道路安全行网页。");
                              document.location.href = "login.html";
                              break;
                          case "-1":
                              alert("请输入完整信息！");
                              break;
                          case "-2":
                              //alert("该用户已存在，请重新输入");
                              showTips($("#txtRegUserName"), $("#userTips"), false, "该用户已存在，请重新输入");
                              break;
                          case "-3":
                              alert("非法访问！");
                              break;
                          case "-4":
                              //showTips($("#txtRegUserName"), $("#authcodeTips"), false, "验证码错误");
                              break;
                          case "-5":
                              showTips($("#txtRegUserName"), $("#userTips"), false, "用户名不能超过50个字符！");
                              //alert("用户名不能超过50个字符！");
                              break;
                      }
                  });
            }
        });

    function checkSubmit() {
        var flag = true;
        var username = ($("#txtRegUserName").val())
          , pass = ($("#txtRegPass").val())
          , repass = ($("#txtRepass").val())
          , agrent = ($("#chkAgrent").attr("checked"));

        if (!checkUserName($("#txtRegUserName"), username))
            flag = false;

        if (!checkPass(pass))
            flag = false;

        if (!checkRePass(repass))
            flag = false;
        //alert(agrent.toString());
        if (flag && (agrent.toString() == 'false')) {
            //alert("您必须先同意用户注册协议！");
            showTips(null, $("#chkAgrentTips"), true, "您必须先同意用户注册协议!");
            flag = false;
        }

        return flag;
    }

    $("#chkAgrent")
        .bind("click", function () {
            var agrent = ($("#chkAgrent").attr("checked"));
            if (agrent.toString() == 'false') {
                showTips(null, $("#chkAgrentTips"), false, "您必须先同意用户注册协议!");
            }
            else {
                showTips(null, $("#chkAgrentTips"), true, "cancel");
            }
        });
});