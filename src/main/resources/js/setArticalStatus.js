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
        url : ajaxInfo.getArticalUrl,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        //data : ,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            var panel = $("#judgeArticalPanel");
            if(result.code ==1){
                $.each(result.data,function(index,data){
                    //tips(data.postTime.substring(0,19),"middleCenter");
                    //将获取的数据迭代处理，显示出来
                    panel.append("<tr>" +
                        "    <td>"+(index+1)+"</td>" +
                        "    <td>"+data.articalTitle+"</td>" +
                        "    <td>"+data.articalIntroduce+"</td>" +
                        "    <td>"+data.userName+"</td>" +
                        //"    <td><code>"+data.articalContent+"</code></td>" +
                        "    <td>"+data.postTime+"</td>" +
                        "    <td>" +
                        //"        <a onclick='viewArtical("+data.articalId+")' href='#' class='iconfont icon-yanjing' title='查看'></a>" +
                        "        <a onclick='passArtical("+data.articalId+")' href='#' class='iconfont icon-check-circle' title='审核通过'></a>" +
                        "        <a onclick='faildArtical("+data.articalId+")' href='#' class='iconfont icon-frown'  title='删除'></a>" + //style='margin-right: 8px'
                        "    </td>" +
                        "</tr>");
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

function viewArtical(id) {
    var jsonString = "{\"id\":\""+id+"\"}";
    ///artical/view
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.viewArticalUrl,
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
                tips(result.data,"middleCenter");
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

function passArtical(id) {
    var jsonString = "{\"id\":\""+id+"\"}";
    ///artical/pass
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.passArticalUrl,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ===1){
                tips(result.data,'middleCenter');
                setTimeout(function(){
                    location.href = "/setArticalStatus";
                },500);
            }else{
                tips(result.msg,'middleCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            tips('请求失败，请您检查！','topCenter');
        }
    });
}

function faildArtical(id) {
    var jsonString = "{\"id\":\""+id+"\"}";
    ///artical/faild
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.faildArticalUrl,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ===1){
                tips(result.data,'middleCenter');
                setTimeout(function(){
                    location.href = "/setArticalStatus";
                },500);
            }else{
                tips(result.msg,'middleCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            tips('请求失败，请您检查！','middleCenter');
        }
    });
}