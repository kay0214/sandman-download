$("#login").click(function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var login = loginConfirm(username,password);
    if(login){
        console.info("可以向后台发送登录请求");
        $.ajax({
            type: "post",
            url: "/login/login",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "username": username,
                "password": password
            }),
            async: false,
            success: function (data) {
                console.info(data);
                if(data.code !== 0){
                    layer.msg("登录失败",{icon: 5});
                }else{
                    layer.msg("登录成功",{icon: 6});
                    location.href = "/";
                }
            }
        });
    }
});
function loginConfirm(username,password) {
    if(username === null || username === undefined || username === ''){
        layer.msg('用户名不能为空',{icon: 5});
        return false;
    }else if(username.length<6){
        layer.msg('用户名不能小于6位字符',{icon: 5});
        return false;
    }
    if(password === null || password === undefined || password === ''){
        layer.msg('密码不能为空',{icon: 5});
        return false;
    }else if(password.length<6){
        layer.msg('密码不能小于6位字符',{icon: 5});
        return false;
    }
    return true;
}