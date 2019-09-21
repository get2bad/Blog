"use strict";
var ajaxInfo;
$(function () {
    ajaxInfo = Object.create(ajaxInfos);
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllFiles,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        //data : ,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            var panel = $("#filePanel");
            if(result.code ==1){
                $.each(result.data,function(index,data){
                    //tips(data.postTime.substring(0,19),"middleCenter");
                    //将获取的数据迭代处理，显示出来 style='height: 200px;width: 200px'
                    //TODO 此处未写完！回来继续完善附件管理功能！
                    panel.append("<div class=\"col-lg-2 col-xs-12 col-sm-6 col-md-3 fileDiv\">" +
                        "                <img src='"+data.filePath+"' alt='"+data.fileType+"' style='height: 200px;width: 100%'>" +
                        //"                <div class='col-md-6 hidden-xs hidden-sm'><img src='"+data.filePath+"' alt='"+data.fileType+"' style='height: 200px;width: 200px'></div>" +
                        "            </div>");
                });
            }else{
                tips(result.msg,'topCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            tips('请求失败，请您检查！','topCenter');
        }
    });
});

//提示框
function tips(alertText, position) {
    new NoticeJs({
        text: alertText,
        //topLeft topCenter topRight middleLeft middleCenter middleRight bottomLeft bottomCenter bottomRight
        position: position,
        animation: {
            open: 'animated bounceIn',
            close: 'animated bounceOut'
        }
    }).show();
}