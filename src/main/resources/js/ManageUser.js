"use strict";
var ajaxInfo;
$(function(){
    ajaxInfo = Object.create(ajaxInfos);
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllUserInfo,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        //data : ,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            var panel = $("#userPanel");
            if(result.code ==1){
                $.each(result.data,function(index,data){
                    //tips(data.postTime.substring(0,19),"middleCenter");
                    //将获取的数据迭代处理，显示出来
                    panel.append("<tr>\n" +
                        "                <td>"+(index+1)+"</td>\n" +
                        "                <td>"+data.userName+"</td>\n" +
                        "                <td>whwh2008</td>\n" +
                        "                <td class=\"visible-md visible-lg\">"+data.birthday+"</td>\n" +
                        "                <td>"+(data.sex ===1?'男':'女')+"</td>\n" +
                        "                <td>"+data.email+"</td>\n" +
                        "                <td>"+data.phone+"</td>\n" +
                        "                <td class=\"visible-md visible-lg\">"+data.description+"</td>\n" +
                        "                <td>"+data.registDate+"</td>\n" +
                        "                <td>暂未实现</td>\n" +
                        "                <td class=\"visible-md visible-lg\">暂未实现</td>\n" +
                        "                <td>\n" +
                        "                    <a href=\"#\" class=\"iconfont icon-xiangqing\" style=\"margin-right: 8px\" title=\"编辑\"></a>\n" +
                        "                    <a href=\"#\" class=\"iconfont icon-close-circle\" style=\"margin-right: 8px\" title=\"删除\"></a>\n" +
                        "                    <a href=\"#\" class=\"iconfont icon-shandian\" title=\"封号\"></a>\n" +
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