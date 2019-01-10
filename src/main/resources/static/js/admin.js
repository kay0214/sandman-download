$("#login").click(function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var login = loginConfirm(username,password);
    if(login){
        $.ajax({
            type: "post",
            url: "/admin/login",
            data: {
                "username": username,
                "password": password
            },
            async: false,
            success: function (data) {
                console.info(data);
                if(data.status !== '000'){
                    layer.msg(data.statusDesc,{icon: 5});
                }else{
                    // 登录成功，跳转admin
                    location.href = "/admin/index";
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