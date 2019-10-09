"use strict";
var ajaxInfo;
$(function () {
    ajaxInfo = Object.create(ajaxInfos);
    getAllArtical();
});
function getAllArtical() {
    var jsonString = '{"option":"all","start":"0"}';
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType, //
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllPassUrl,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data : jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            var panel = $("#passArticalPanel");
            if(result.code ==1){
                $('.articalInfos').remove();
                $.each(result.data,function(index,data){
                    //tips(data.isLock===1,"middleCenter");
                    //将获取的数据迭代处理，显示出来
                    panel.append("<tr class='articalInfos'>\n" +
                        "                <td>"+(index+1)+"</td>\n" +
                        "                <td>"+data.articalTitle+"</td>\n" +
                        "                <td>"+data.userName+"</td>\n" +
                        "                <td>"+data.articalIntroduce+"</td>\n" +
                        "                <td>"+data.postTime.substring(0,19)+"</td>\n" +
                        "                <td>0</td>\n" +
                        "                <td>"+ (data.isLock===1?'私有':'公开') +"</td>\n" + //
                        "                <td>" +
                        "                    <a onclick='rewrite("+data.articalId+")' href=\"#\" class=\"iconfont icon-xiangqing\" style=\"margin-right: 8px\" title=\"编辑\"></a>" +
                        "                    <a onclick='deleteArtical("+data.articalId+")' href=\"#\" class=\"iconfont icon-close-circle\" title=\"退回审核状态\"></a>" +
                        "                </td>" +
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

function rewrite(id) {
    var jsonString = "{\"id\":"+id+"}";
    $.ajax({
        //请求方式
        type : ajaxInfo.postType, //
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllPassUrl,
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
                tips(result.data,'middleCenter');
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

function deleteArtical(id) {
    var jsonString = "{\"id\":"+id+"}";
    $.ajax({
        //请求方式
        type : ajaxInfo.postType, //
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.backJudgeArtical,
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
                tips(result.data,'middleCenter');
                setTimeout(function(){
                    location.href = "/manageArtical";
                },3000);
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

function searchArtical() {
    //获取各项信息，然后组装json字符串，使用ajax传送到后台然后返回数据
    var category = $('#category').val();
    var articalAuth = $('#articalAuth').val();
    var judgeStatus = $('#judgeStatus').val();
    var articalContent = $('#articalContent').val();
    //json字符串
    var jsonString = '{"category":"'+category+'","articalAuth":"'+articalAuth+'","judgeStatus":"'+judgeStatus+'","articalContent":"%'+articalContent+'%"}';
    $.ajax({
        //请求方式
        type : ajaxInfo.postType, //
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getArticalByConditions,
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
                if(typeof(result.data[0]) ==='undefined'){
                    tips('暂时没有搜索结果哦~','middleCenter');
                }else{
                    //tips(data.isLock===1,"middleCenter");
                    //清除之前迭代的信息
                    $('.articalInfos').remove();
                    $.each(result.data,function(index,data){
                        //将获取的数据迭代处理，显示出来
                        $('#passArticalPanel').append("<tr class='articalInfos'>\n" +
                            "                <td>"+(index+1)+"</td>\n" +
                            "                <td>"+data.articalTitle+"</td>\n" +
                            "                <td>"+data.userName+"</td>\n" +
                            "                <td>"+data.articalIntroduce+"</td>\n" +
                            "                <td>"+data.postTime+"</td>\n" +
                            "                <td>"+data.articalCommentCount+"</td>\n" +
                            "                <td>"+ (data.isLock===1?'私有':'公开') +"</td>\n" + //
                            "                <td>" +
                            "                    <a onclick='rewrite("+data.articalId+")' href=\"#\" class=\"iconfont icon-xiangqing\" style=\"margin-right: 8px\" title=\"编辑\"></a>" +
                            "                    <a onclick='deleteArtical("+data.articalId+")' href=\"#\" class=\"iconfont icon-close-circle\" title=\"退回审核状态\"></a>" +
                            "                </td>" +
                            "            </tr>");
                    });
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
}