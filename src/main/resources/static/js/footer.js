var subtips;
$("a[name='wechat']").mouseover(function () {
    subtips = layer.tips("嘿嘿，这是微信图片", $(this),{tips:[1,'#ff0000'],time: 30000});
});
$("a[name='wechat']").mouseout(function () {
    layer.close(subtips);
});
