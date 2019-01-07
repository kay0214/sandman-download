//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
var send_success = false;//同步请求，检查用户名是否已被占用
var email_available = false;//同步请求，检查email是否已被占用
$(".next").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();

    if($("fieldset").index(current_fs) == 0){
        // 写入用户名，发送邮箱验证码
        var send_success = firstPageConfirm();
        if(!send_success){
            animating = false;
            return false;
        }
    }else{
        // 绑定邮箱页
        var email = secondPageConfirm();
        if(!email){
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

$(".submit").click(function(){
	return false;
});

// 第一个页面检查
function firstPageConfirm() {
    // 输入用户名发送邮件
    var username = $("#username").val();
    if(username === null || username === undefined || username === ''){
        layer.msg('用户名不能为空',{icon: 5});
        return false;
    }
    $.ajax({
        type: "post",
        url: "/forget/send_email",
        data: {
            "username": username
        },
        async: false,
        success: function (data) {
            if(data.status !== '000'){
                // 给用户发送验证码失败
                layer.msg(data.statusDesc,{icon: 5});
                return false;
            }
        }

    });
    // 给用户发送验证码成功 true
    send_success = true;
    return true;
}

//第二个页面检查
function secondPageConfirm() {
    //同步发送请求，检查是否可以发送邮件，也即email没有被占用
    email_valid();
    if(email_available){
        //发送邮件
        return send_email();
    }else{
        return false;
    }
}

function email_valid() {
    var email_regex = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
    var email = $("#email").val();
    if(email_regex.test(email)){
        $.ajax({
            type: "get",
            url: "/register/email_valid",
            data: "email=" + email,
            async: false,
            success: function (data) {
                if(data.data){
                    // 可以发送邮件
                    email_available = true;
                }
            }
        });
    }else{
        layer.msg('邮箱不正确',{icon: 5});
    }
}

function send_email() {
    var email = $("#email").val();
    var username = $("#username").val();
    var password = $("#password").val();
    console.info("准备发送邮件::::username=" + username + ";;;password=" + password + ";;;;email = " + email);
    $("#emailConfirm").html("<b><i>" + email + "</i></b>");
    // ajax发送邮件
    $.ajax({
        type: "post",
        url: "/register/register",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "username": username,
            "password": password,
            "email":email
        })
    });
    return true;

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

function username_valid() {
	//验证username是否有重复
	var username = $("#username").val();
	if(username == null || username == undefined || username == ''){
        layer.msg('用户名不能为空',{icon: 5});
        username_available = false;
		return;
	}else if(username.length < 6){
        layer.msg('用户名必须为字母,数字或下划线组合，最低6位',{icon: 5});
        username_available = false;
        return;
    }

	$.ajax({
        type: "get",
        url: "/register/username_valid",
        data: "username=" + username,
        async: false,
        success: function (data) {
            if(!data.data){
                layer.msg('用户名已被占用',{icon: 5});
                username_available = false;
            }else{
                username_available = true;
            }
        }
    });
}