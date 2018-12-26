
/*签出begin*/
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
//                        $("#login").show();
//                        $("#logined").hide();
//                        $("#btnLogin").show();
//                        $("#loginEntry").show();

//                        $("#txtUserName").attr('disabled', false);
//                        $("#txtPass").attr('disabled', false);
//                        $("#btnLogin").attr('disabled', false);

                        window.location.reload();
                        //document.location.href=(document.URL+ (new Date()).getTime() + '=' + (new Date()).getTime());
                        break;
                }
            });
        });
/*签出end*/

;$(document).ready(function () {
    var expanded = false;
    $('.menuLink').hover(
        function () {
            //alert(1);
            var $this = $(this);
            var angle = 0;
            var t = setInterval(function () {
                if (angle == 360) {
                    clearInterval(t);
                    return;
                }
                angle += 12;
                $this.stop().animate({ rotate: '+=-12deg' }, 0);
            }, 10);
        },
        function () {
            //alert(2);
            //            var $this = $(this);
            //            var angle = 360;
            //            
            //            var t = setInterval(function () {
            //                if (angle == 0) {
            //                    clearInterval(t);
            //                    return;
            //                }
            //                angle -= 12;
            //                $this.stop().animate({ rotate: '+=12deg' }, 0);
            //            }, 10);
        }
    );
//    $('.item').hover(
//                function () {
//                    var $this = $(this);
//                    expand($this);
//                },
//                function () {
//                    var $this = $(this);
//                    collapse($this);
//                }
//            );
    function expand($elem) {
        var angle = 0;
        var t = setInterval(function () {
            if (angle == 360) {
                clearInterval(t);
                return;
            }
            angle += 12;
            $('.icon_home', $elem).stop().animate({ rotate: '+=-12deg' }, 0);
        }, 10);
        $elem.stop().animate({ width: '300px' }, 1000)
                .find('.item_content').fadeIn(1000);
        expanded = true;
    }
    function collapse($elem) {
        var angle = 360;
        var t = setInterval(function () {
            if (angle == 0) {
                clearInterval(t);
                return;
            }
            angle -= 12;
            $('.menuLink', $elem).stop().animate({ rotate: '+=12deg' }, 0);
        }, 10);
        $elem.stop().animate({ width: '56px' }, 500)
                .find('.item_content').stop(true, true).fadeOut();
        expanded = false;
    }
    $('#menuLinkSpan').bind('click', function () {
        if (expanded) { collapse($('.item')); }
        else { expand($('.item')); }
     });
});