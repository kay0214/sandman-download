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
                if(data.status !== '000'){
                    layer.msg(data.statusDesc,{icon: 5});
                }else{
                    console.info("登录成功，跳转");
                    layer.msg(data.statusDesc,{icon: 6});
                    //TODO:这里改成动态的
                    location.href = "http://localhost:8080/";
                    //var retUrl = [[${session.retUrl}]];
                    //console.info("retUrl=" + retUrl);
                    //location.href=retUrl;
                }
            }
        });
    }
    console.info("login click -> username:" + username + ";password:" + password);
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