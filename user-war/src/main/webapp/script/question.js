var real_name = '真实姓名';
var real_name_not_null = '姓名不能为空';
var real_name_max_length = '姓名长度不能超过20';
var real_name_tips = '请输入真实姓名';
var real_name_must_be_CHN = '姓名只能为汉字';

var contact_mobile_email = '邮箱/手机号码';
var contact_mobile_email_tips = '请输入邮箱/手机号码';

var userName_hint = "邮箱/手机号码";
var userName_tips_hint = "请输入邮箱/手机号码";
var group_tips_hint = '个人用户无需填写';
var password_tips_hint = '6-20位数字、字母、字符';
var input_authcode_tips_hint = "请输入验证码";

var user_name_input_tips = '请填写邮箱或手机号码';
var user_name_type_tips = '只能填写邮箱或手机号码';
var user_name_not_null = '用户名不能为空';
var user_name_max_length = '用户名不能超过50个字符';

var nameRegex = new RegExp('^([\u4e00-\u9fa5]+)$', 'i');
//var userName_hint = '邮箱/手机号码';

$(document).ready(function () {
    $("#contactLink").bind("click", function () {
        //popUpMsg();
        $('#contactLayer').show();
        if (document.URL.indexOf('index.html') < 0) {
            $("#layerLogin").hide();
        }
        $("#layerReg").hide();
    });
    $("#txtName")
        .bind("blur", function () {
            if (checkQuestion())
                showTipsFunction($("#txtNameTips"), true, '');
            if ($("#txtName").val() == null || $("#txtName").val() == '') {
                $(this).removeClass().addClass("input");
                $("#txtName").attr('value', real_name);
            }
        })
        .bind("focus", function () {
            $(this).removeClass().addClass("user");
            if ($("#txtName").val() == real_name || $("#txtName").val() == '') {
                $("#txtName").attr('value', '');
                showDefaultFunction($("#txtNameTips"), real_name_tips);
            }
        });

    $("#txtMobile")
        .bind("blur", function () {
            if (checkUserNameFunction($.trim($("#txtMobile").val()), $("#txtMobileTips")))
                showTipsFunction($("#txtMobileTips"), true, '');
            if ($("#txtMobile").val() == null || $("#txtMobile").val() == '') {
                $(this).removeClass().addClass("input");
                $("#txtMobile").attr('value', contact_mobile_email);
            }
        })
        .bind("focus", function () {
            $(this).removeClass().addClass("user");
            if ($("#txtMobile").val() == contact_mobile_email || $("#txtMobile").val() == '') {
                $("#txtMobile").attr('value', '');
                showDefaultFunction($("#txtMobileTips"), contact_mobile_email_tips);
            }
            else {
                showDefaultFunction($("#txtMobileTips"), '');
            }
        });
    /**/
    $("#txtContents")
        .bind("blur", function () {
            if ($("#txtContents").val() == null || $("#txtContents").val() == '') {
                $(this).removeClass().addClass("textareaInput");

            }
        })
        .bind("focus", function () {
            $(this).removeClass().addClass("textareaClass");
        });
    /**/
    function checkQuestion() {
        //alert(1);
        var name = $.trim($("#txtName").val())
              , sGender = $.trim($("#selectedGender").val())
              , tMobile = $.trim($("#txtMobile").val())
              , tEmail = ""//  $.trim($("#txtEmail").val())
              , tContents = $.trim($("#txtContents").val());
        //var flag = true;
        if (name == real_name) {

            showTipsFunction($("#txtNameTips"), false, real_name_not_null);

            return false;
        }
        else if (name.length <= 0) {

            showTipsFunction($("#txtNameTips"), false, real_name_not_null);

            return false;
        }
        else if (name.length > 20) {
            showTipsFunction($("#txtNameTips"), false, real_name_max_length);

            return false;
        }
        else if (!nameRegex.test(name)) {
            //alert(escape(name).replace('%', ''));
            showTipsFunction($("#txtNameTips"), false, real_name_must_be_CHN);

            return false;
        }
        return true;
    }

    $("#closeContactLayer").bind("click", function () {
        $("#contactLayer").hide();
        closeContactPage();
    });

    function closeContactPage() {
        $("#txtName").removeClass().addClass("input");
        $("#txtName").val(real_name);
        showDefaultFunction($("#txtNameTips"), "");
        $("#txtMobile").removeClass().addClass("input");
        $("#txtMobile").val(contact_mobile_email);
        showDefaultFunction($("#txtMobileTips"), "");
        $("#selectedGender").val('-- 请选择 --');
        $("#txtContents").val('');
        $("#txtContents").removeClass().addClass("textareaInput");
    }

    $("#btnContactSubmit").bind("click", function () {
        var username = $.trim($("#txtName").val())
              , sGender = $.trim($("#selectedGender").val())
              , tMobile = $.trim($("#txtMobile").val())
              , tEmail = $.trim($("#txtEmail").val())
              , tContents = $.trim($("#txtContents").val());

        if (!checkQuestion() || (!checkUserNameFunction(tMobile, $("#txtMobileTips")))) return;
        if (tMobile.indexOf('@') < 0) {
            tEmail = '';
        }
        else {
            tEmail = tMobile;
            tMobile = '';
        }
        $.post("Admin/Users/SubmitQuestion.aspx",
            {
                user: escape(username),
                gender: escape(sGender),
                mobile: escape(tMobile),
                email: escape(tEmail),
                contents: escape(tContents)
            },
            function (data, status) {

                var datas = data.split(',');

                switch (datas[0]) {
                    case "0":
                        alert("您的问题已提交成功！");
                        $("#closeContactLayer").click();
                        break;
                    case "-1":
                        //alert(".....");
                        break;
                    case "-2":
                        //alert(".....");
                        break;
                    case "-5":
                        //alert(".....");
                        break;
                }
            });
    });

});

    /* 显示提示消息 */
    function showTipsFunction(tip, ico, msg) {
        if (msg == 'cancel') return;

        if (ico) {
            //tip.css('background', 'url(../images/gou.png) no-repeat');
            tip.css('color', '#9FDF0A');
        }
        else {
            //tip.css('background', 'url(../images/no.png) no-repeat');
            tip.css('color', '#ff0000');
        }

        tip.html(msg);
    }

    function showDefaultFunction(tip, msg) {
        tip.css('background', '');
        tip.css('color', '#BEBEBE');
        tip.html(msg);
    }

    function checkUserNameFunction(v, tipsJqueryObj) {//alert(1);
        var flag = true;
        if ("" == v || v == userName_hint) {
            showTipsFunction(tipsJqueryObj, false, user_name_input_tips); flag = false;
        } else if (!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i.test(v)
                && !/^(13[0-9]|15[0-9]|18[0-9])([0-9]{8})$/i.test(v)) {
            showTipsFunction(tipsJqueryObj, false, user_name_type_tips); flag = false;
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
            //alert(50);
            showTipsFunction(tipsJqueryObj, false, user_name_max_length); flag = false;
        }

        return flag;
    }