"use strict";
var ajaxInfo;
$(function(){
    ajaxInfo = Object.create(ajaxInfos);
    var id = $.cookie('UserID');
    var jsonString = '{"id":'+id+'}';
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getUserInfoById,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                //tips(result.data.userName,"middleCenter");
                //设置值
                if($("#userNameInput").length >0){
                    $("#userNameInput").text(result.data.userName);
                }else{
                    //userNameText userLevel userEmail userDescription userIcon
                    $("#userNameText").attr('value',result.data.userName);
                    //TODO 暂时不实现，日后功能优化进行整改
                    //$("#userLevel").text();
                    $("#userEmail").attr('value',result.data.email);
                    $("#userDescription").text(result.data.description);
                    //TODO 暂时不实现，日后功能优化进行整改
                    $("#input-file-now").attr('data-default-file','../../articalPic/little_animal_18.png');
                }

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

function submitChangePwd() {
    $('#userId').attr('value',$.cookie('UserID'));
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#newPwd").val();
    var confirmPwd = $("#confirmPwd").val();
    var jsonString = JSON.stringify($('#changePwdForm').serializeJson());
    console.log(jsonString);
    if(oldPwd ==='' || newPwd ===''){
        tips("请您输入原密码/新密码",'topCenter');
        return;
    }
    if(newPwd !== confirmPwd){
        tips("前后输入的密码不一致哦~！",'topCenter');
        return;
    }
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.changeUserPwd,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                tips(result.data,'topCenter');
                setTimeout(function () {
                    location.href = "/password";
                },3000)
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

//格式化json字符串 将form表单进行json化
(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);