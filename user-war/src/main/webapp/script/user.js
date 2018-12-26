$(document).ready(function () {
    //我的预约链接
    $(divReserve).click(function () {
        //$(divReserveList).hide();
        //alert($('#divReserveList').css('display'));
        if ($(divReserveList).css('display') == 'block')
        {
            $(divReserveList).hide();
            $(imgReserve).attr("src", "images/arrowr_grey.png");
        }
        else if ($(divReserveList).css('display') == 'none')
        {
            $(divReserveList).show();
            $(imgReserve).attr("src", "images/arrowd_grey.png");
        }
    });

    //我的报告链接
    $(divReport).click(function () {
        //$(divReserveList).hide();
        //alert($('#divReserveList').css('display'));
        if ($(divReportList).css('display') == 'block') {
            $(divReportList).hide();
            $(imgReport).attr("src", "images/arrowr_grey.png");
        }
        else if ($(divReportList).css('display') == 'none') {
            $(divReportList).show();
            $(imgReport).attr("src", "images/arrowd_grey.png");
        }
    });

    //我的视频链接
    $(divVideo).click(function () {
        //$(divReserveList).hide();
        //alert($('#divReserveList').css('display'));
        if ($(divVideoList).css('display') == 'block') {
            $(divVideoList).hide();
            $(imgVideo).attr("src", "images/arrowr_grey.png");
        }
        else if ($(divVideoList).css('display') == 'none') {
            $(divVideoList).show();
            $(imgVideo).attr("src", "images/arrowd_grey.png");
        }
    });

    //退出
    $("#logout")
        .bind("click", function () {

            $.post("CheckLogin.aspx",
            {
                logout: 'true'
            },
            function (data, status) {

                var datas = data.split(',');

                switch (datas[0]) {
                    case "SignOut":
                        document.location.href = "login.html";
                        break;
                }
            });
        });

    //获取用户信息(用户名，等级)
    $.post("Admin/Users/UserInfo.aspx", { requestType: 'wap' },
                function (result, status) {
                    var objs = result.split('|||');
                    $(spanName).html(objs[1]);
                    $(spanUserLevel).html(objs[2]);
                    if (objs[2] == "游客")
                    {
                        document.location.href = "login.html";
                    }
                    else if (objs[2] == "VIP")
                    {
                        $("#btnUpgrade").hide();
                    }
                }
                );

    //获取用户预约信息
    $.post("Admin/Users/GetUserReserveInfo.aspx", { requestType: 'wap' },
                function (result, status) {
                    $(ulReserve).html(result);
                }
                );
    //获取用户报告信息和视频
    $.post("Admin/Users/GetUserActivityTrace.aspx", { requestType: 'wap' },
                function (result, status) {
                    var objs = result.split('|||');
                    $("#ulReport").html(objs[0]);
                    $("#ulVideoList").html(objs[1]);
                }
                );
































});
