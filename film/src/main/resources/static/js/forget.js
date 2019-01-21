//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
var send_success = false;//同步请求，检查用户名是否存在并发送找回密码邮件
var right_code = false;//同步请求，检查邮箱验证码是否正确
var c = 30;//计时器
var t;//timeOut节点
$(".next").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();

    if($("fieldset").index(current_fs) == 0){
        // 写入用户名，发送邮箱验证码
        firstPageConfirm();
        if(!send_success){
            animating = false;
            return false;
        }
    }else{
        // 绑定邮箱页
        secondPageConfirm();
        if(!right_code){
            animating = false;
            return false;
        }
    }

	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'transform': 'scale('+scale+')'});
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".previous").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();
	
	//de-activate current step on progressbar
	$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
	
	//show the previous fieldset
	previous_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

// 第一个页面检查
function firstPageConfirm() {
    // 输入用户名发送邮件
    var username = $("#username").val();
    if(username === null || username === undefined || username === ''){
        layer.msg('用户名不能为空',{icon: 5});
        return false;
    }
    return send_email(username);
}

//第二个页面检查
function secondPageConfirm() {
    var username = $("#username").val();
    var code = $("#code").val();
    if(code === null || code === undefined || code === ''){
        layer.msg('邮箱验证码不能为空',{icon: 5});
        return false;
    }
    // 请求后台，校验验证码正确性
    $.ajax({
        type: "post",
        url: "/forget/right_code",
        data: {
            "username": username,
            "code":code
        },
        async: false,
        success: function (data) {
            if(data.status === '000'){
                right_code = true;
                return true;
            }else{
                // 给用户发送验证码失败
                layer.msg(data.statusDesc,{icon: 5});
                return false;
            }
        }

    });
}

function send_email(username) {
    $.ajax({
        type: "post",
        url: "/forget/send_email",
        data: {
            "username": username
        },
        async: false,
        success: function (data) {
            if(data.status === '000'){
                // 这里做个30s计时器，按钮disabled
                timeCount();
                send_success = true;
                return true;
            }else{
                // 给用户发送验证码失败
                layer.msg(data.statusDesc,{icon: 5});
                return false;
            }
        }

    });
}

$("#re_send").click(function () {
    var reSend = $("#re_send");
    if(reSend.hasClass("action-button")){
        var username = $("#username").val();
        send_email(username);
    }
});

$("#submit").click(function () {
    var pass = password_same();
    if(!pass){
        return false;
    }
    var password = $("#password").val();
    var username = $("#username").val();
    $.ajax({
        type: "post",
        url: "/forget/modify",
        data: {
            "username": username,
            "password": password
        },
        async: false,
        success: function (data) {
            if(data.status === '000'){
                window.location.href = "/";
            }else{
                // 修改密码出现错误
                layer.msg(data.statusDesc,{icon: 5});
                return false;
            }
        }

    });
});

function timeCount() {
    var reSend = $("#re_send");
    if(c>0){
        c = c - 1;
        // 如果class中有action-button，就替换成禁用按钮
        if(reSend.hasClass("action-button")){
            reSend.attr("class","disabled-button");
        }
        reSend.val("(" + c + ")秒后可重发");
        t = setTimeout(function () {timeCount();},1000);
    }else{
        // 如果class中有disabled-button，就替换成可用按钮
        if(reSend.hasClass("disabled-button")){
            reSend.attr("class","action-button");
        }
        reSend.val("重新发送");
        clearTimeout(t);
        c = 30;
    }
}

function password_same() {
    var password = $("#password").val();
    var confirm_password = $("#confirm_password").val();
    // 判断密码是否为空
    if(password !== null && password !== undefined && password !== '' && confirm_password !== null && confirm_password !== undefined && confirm_password !== ''){
        // 判断密码位数是否符合要求
        if(password.length < 6){
            layer.msg('密码必须为字母,数字或下划线组合，最低6位',{icon: 5});
            return false;
        }else{
            // 判断两次输入密码是否一致
            if(password === confirm_password){
                return true;
            }else{
                layer.msg('两次输入不一致',{icon: 5});
                return false;
            }
        }
    }else{
        layer.msg('密码不能为空',{icon: 5});
        return false;
    }
}