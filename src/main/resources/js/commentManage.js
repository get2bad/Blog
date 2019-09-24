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
        url : ajaxInfo.getAllComment,
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
                //tips(result.data,'middleCenter');
                $.each(result.data,function(index,data) {
                    $("#commentPanel").append("<tr>\n" +
                        "                <td>"+(index+1)+"</td>\n" +
                        "                <td>"+data.articalName+"</td>\n" +
                        "                <td>"+data.userName+"</td>\n" +
                        "                <td>"+data.commentContent+"</td>\n" +
                        "                <td>"+data.commentPostTime+"</td>\n" +
                        "                <td>"+(data.commentStatus===0?"未通过":"通过")+"</td>\n" +
                        "                <td>\n" +
                        "                    <a href=\"#\" onclick='passComment("+data.commentId+")' class=\"iconfont icon-check-circle\" title=\"审核通过\"></a>\n" +
                        "                    <a href=\"#\" onclick='faildComment("+data.commentId+")' class=\"iconfont icon-close-circle\" title=\"审核退回\"></a>\n" +
                        "                    <a href=\"#\" onclick='deleteComment("+data.commentId+")' class=\"iconfont icon-frown\" style=\"margin-right: 8px\" title=\"删除\"></a>\n" +
                        "                </td>\n" +
                        "            </tr>");
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

function passComment(id){
    var jasonString = '{"id":"'+id+'"}';
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.passComment,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jasonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                //tips(result.data,'middleCenter');
                window.location.reload();
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

function faildComment(id){
    var jasonString = '{"id":"'+id+'"}';
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.faildComment,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jasonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                //tips(result.data,'middleCenter');
                window.location.reload();
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

function deleteComment(id){
    var jasonString = '{"id":"'+id+'"}';
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.deleteComment,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jasonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                window.location.reload();
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