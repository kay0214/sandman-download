var subtips;
$("a[name='wechat']").mouseover(function () {
    subtips = layer.tips("<img width='150px' height='150px' src='/images/wechat.png'>", $(this),{tips:[1,"#FFFFFF"],time: 30000});
});
$("a[name='wechat']").mouseout(function () {
    layer.close(subtips);
});
