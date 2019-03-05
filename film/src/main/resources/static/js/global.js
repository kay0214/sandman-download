layui.define(['layer', 'form', 'element', 'upload', 'util'], function(exports){

    var $ = layui.jquery
        ,layer = layui.layer
        ,laydate = layui.laydate
        ,form = layui.form
        ,element = layui.element
        ,upload = layui.upload
        ,util = layui.util
        ,device = layui.device()

        ,DISABLED = 'layui-btn-disabled';

    //阻止IE7以下访问
/*    if(device.ie && device.ie < 8){
        layer.alert('IE浏览器兼容性较差，为了您的友好体验，请使用IE8以上版本');
    }*/

    layui.focusInsert = function(obj, str){
        var result, val = obj.value;
        obj.focus();
        if(document.selection){ //ie
            result = document.selection.createRange();
            document.selection.empty();
            result.text = str;
        } else {
            result = [val.substring(0, obj.selectionStart), str, val.substr(obj.selectionEnd)];
            obj.focus();
            obj.value = result.join('');
        }
    };

    //加载特定模块
    if(layui.cache.page && layui.cache.page !== 'index'){
        var extend = {};
        extend[layui.cache.page] = layui.cache.page;
        layui.extend(extend);
        layui.use(layui.cache.page);
    }

    //手机设备的简单适配
    var treeMobile = $('.site-tree-mobile')
        ,shadeMobile = $('.site-mobile-shade')

    treeMobile.on('click', function(){
        $('body').addClass('site-mobile');
    });

    shadeMobile.on('click', function(){
        $('body').removeClass('site-mobile');
    });
    // 点击了重启程序
    $("a[name='restart']").click(function () {
        layer.confirm('<input class="layui-input" required  lay-verify="required" name="restartPassword" placeholder="请输入重启密码">',{title:'正在重启应用,请谨慎操作!'}, function(index){
            var password = $("input[name='restartPassword']").val();
            if(password!==null && password!==undefined && password!==''){
                // 这里ajax请求
                $.ajax({
                    type: "post",
                    url: "/project/restart",
                    data:{"password": password},
                    async: false,
                    success: function (data) {
                        if(data.status !== '000'){
                            // 重启失败
                            layer.msg(data.statusDesc,{icon: 5});
                            return false;
                        }
                    }
                });
            }else{
                // 这里直接提示错误
                layer.msg("输入无效",{icon: 5});
            }
            layer.close(index);
        });
    });

});