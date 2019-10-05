'use strict';
var ajaxInfo;
$(function () {
    ajaxInfo = Object.create(ajaxInfos);
    getAllCategorys();
});

function getAllCategorys() {
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllCategory,
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
                    $("#categoryPanel").append('<tr>\n' +
                        '                    <td>'+(index+1)+'</td>\n' +
                        '                    <td>'+data.categoryName+'</td>\n' +
                        '                    <td>'+data.categoryUrl+'</td>\n' +
                        '                    <td>\n' +
                        '                        <a href="#" class="iconfont icon-xiangqing" style="margin-right: 8px" title="编辑"></a>\n' +
                        '                        <a data-toggle="modal" data-target="#confirmDelete" onclick="setCategoryId('+data.categoryId+')"  class="iconfont icon-close-circle" title="删除"></a>\n' +
                        '                    </td>\n' +
                        '                </tr>');
                });
                //href="/category/deleteCategoryById?id='+data.categoryId+'"
            }else{
                tips(result.msg,'topCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            //tips('请求失败，请您检查！','topCenter');
        }
    });
}

function setCategoryId(id) {
    $("#categoryId").val(id);
}
