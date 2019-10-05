'use strict';
var ajaxInfo;
var startComment = 0;
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
    //先获取一次评论（前8条）
    getAllComment(startComment);

    scrollBottomToGetMoreInfo();

    getAllCategory();
});

function getAllComment(startComments){
    var jsonString = '{"start":'+startComments+'}';
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
        data : jsonString,
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
                if(result.data!= null &&result.data[0].isAll ===1){
                    $('#pageTips').text("😅 留言区已经全部展示咯~");
                }
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

function scrollBottomToGetMoreInfo(){
    window.onscroll = function () {
        if (getScrollTop() + getClientHeight() == getScrollHeight()) {
            //alert("到达底部");
            //浏览器滑到底部执行ajax动态获取数据  动态获取前8条的数据
            getAllComment(startComment+7);
        }
    }
}

//获取滚动条当前的位置
function getScrollTop() {
    var scrollTop = 0;
    if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
    }
    else if (document.body) {
        scrollTop = document.body.scrollTop;
    }
    return scrollTop;
}

//获取当前可是范围的高度
function getClientHeight() {
    var clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
    }
    else {
        clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
    }
    return clientHeight;
}

//获取文档完整的高度
function getScrollHeight() {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}