'use strict';
var ajaxInfo;
$(function(){
    ajaxInfo = Object.create(ajaxInfos);
    getUserInfo(2);

    //向隐藏的input设置值
    var userId = $.cookie('UserID');
    if(typeof(userId) ==='undefined'){
        userId = 0;
    }
    $('.userIdInput').val(parseInt(userId));
    $('.postIPInput').val(returnCitySN.cip);

    getAllComment();
});

function getAllComment(){
    //ajax获取
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllNote,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        //data : jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                $.each(result.data,function(index,data){
                    //tips(data.picIntroduceUrl,"middleCenter");
                    $("#notePanel").append("<div class=\"media commonContent\">\n" +
                        "                    <div class=\"media-left pull-left\">\n" +
                        "                    <img src=\"https://static.runoob.com/images/mix/img_avatar.png\" class=\"media-object\">\n" +
                        "                        </div>\n" +
                        "                    <div class=\"media-body\">\n" +
                        "                        <h5 class=\"media-heading\">"+data.userName+"</h5>\n" +
                        "                        <small>"+data.notePostTime+"</small>\n" +
                        "                        <p>"+data.noteContent+"</p>\n" +
                        "                    </div>\n" +
                        "                </div>");

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
}

//提示框
function tips(alertText,position){
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