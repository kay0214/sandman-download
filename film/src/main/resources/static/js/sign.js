var signDesc;
$("#sign").click(function () {
    var check = checkLogin();
    if(!check){
        // 未登录状态
        return false;
    }
    $.ajax({
        type: "get",
        url: "/sign_in/sign",
        async: false,
        success: function (data) {
            if(data.status === '000'){
                // 签到成功刷新当前页面
                layer.msg(data.statusDesc,{icon: 6});
                window.location.reload();
            }else{
                layer.msg(data.statusDesc,{icon: 5});
            }
        }
    });
});
$("#calendar").click(function(){
    var check = checkLogin();
    if(!check){
        // 未登录状态
        return false;
    }
    layer.open({
        type: 1
        ,offset: 'auto'
        ,title: false
        ,area: ['275px', '282px']
        ,id: 'signCalendar' //防止重复弹出
        ,content: '<div id="cal" style="text-align: center"></div>'
        ,closeBtn: 1
        ,shadeClose: true
        ,yes: function(){
            layer.closeAll();
        }
    });
    $.ajax({
        type: "get",
        url: "/sign_in/get_sign",
        async: false,
        success: function (data) {
            if(data.status === '000'){
                // 初始化日历
                laydate.render({
                    elem: '#cal'
                    ,position: 'static'
                    ,theme: 'grid'
                    ,showBottom: false
                    ,mark:data.data
                });
            }else{
                layer.msg(data.statusDesc,{icon: 5});
            }

        }
    });

});
function checkLogin() {
    var userId = $("input[name='login']").val();
    if(userId !== null && userId !== undefined && userId !== ''){
        return true;
    }else{
        layer.msg("您未登录",{icon: 5});
        return false;
    }
}

$("#signDesc").mouseover(function () {
    signDesc = layer.tips("<span class='sign-description'>每日签到可以获取下载积分,每人每天进行签到可获取1积分奖励</span>", $(this),{tips:[1,"#FFFFFF"],time: 30000});
});
$("#signDesc").mouseout(function () {
    layer.close(signDesc);
});