"use strict";
var ajaxInfo;
$(function () {
    ajaxInfo = Object.create(ajaxInfos);
    //激活动态效果
    $('.category').bootstrapDropdownOnHover({
        mouseOutDelay: 50,
        //在宽度为多少像素时自动隐藏效果
        responsiveThreshold: 888,
        hideBackdrop: true
    });
    function sleep(d){
        for(var t = Date.now();Date.now() - t <= d;);
    }
    $(function () {
        $('.content-left').css('display','block');
        $('.content-left').fadeOut(0);
        $('.content-left').fadeIn(1000);
        //sleep(5000);
        $('.content-right').css('display','block');
        $('.content-right').fadeOut(0);
        $('.content-right').fadeIn(3000);
    });
    /*
    if($.cookie('UserRedisSessionID')===null){
        //tips($.cookie('UserRedisSessionID'),'middleCenter');
        $("#userInfo").css('display','none');
        $("#loginBtn").css('display','block');
    }else{
        $("#userInfo").css('display','block');
        $("#loginBtn").css('display','none');
    }*/
    getAllComment($("#articalId").text());

    getUserInfo(2);
});

function getAllComment(id) {
    var jsonString = '{"id":"'+id+'"}';
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllCommentByArticalId,
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
                //tips(result.data,'middleCenter');
                $.each(result.data,function(index,data){
                    $("#commentPanel").append("<div class=\"media commonContent\">\n" +
                        "                            <div class=\"media-left pull-left\">\n" +
                        "                                <img src=\"https://static.runoob.com/images/mix/img_avatar.png\" class=\"media-object\">\n" +
                        "                            </div>\n" +
                        "                            <div class=\"media-body\">\n" +
                        "                                <h5 class=\"media-heading\">"+data.userName+"</h5>\n" +
                        "                                <p>"+data.commentContent+"</p>\n" +
                        "                                <small>"+data.commentPostTime+"</small>\n" +
                        "                            </div>\n" +
                        "                        </div>");
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

function submitComment(id) {
    var comment = $("#comment").val();
    var jsonString = '{"articalId":'+id+',"comment":"'+comment+'","userId":'+$.cookie("UserID")+'}';
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.submitComment,
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
                setTimeout(function () {
                    window.location.reload();
                },1000);
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

function getUserIsLogin() {
    
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

//设置vaptcha验证码的控件
function setVaptchaContainer(vaptchaContainerId,sceneText){
    var vaptchaCode = Object.create(vaptchaBaseInfo);
    //vaptcha验证码加载
    vaptcha({
        //配置参数
        // 验证单元id
        vid: vaptchaCode.vid,
        lang:vaptchaCode.lang,
        // 展现类型 点击式
        type: vaptchaCode.type,
        scene:sceneText,
        // 按钮容器，可为Element 或者 selector
        container: '#'+vaptchaContainerId
    }).then(function (vaptchaObj) {
        // 调用验证实例 vaptchaObj 的 render 方法加载验证按钮
        vaptchaObj.render()
        vaptchaObj.listen('pass', function() {
            // 验证成功， 进行登录操作
            //如果token为空说明没有通过验证，如果token不为空说明通过了验证
            token = vaptchaObj.getToken();
            console.log(token);
            judgeTokenIsEmpty();
        })
        vaptchaObj.listen('close', function() {
            //验证弹窗关闭触发
        })
        $('input[type="reset"]').on('click',function(){
            vaptchaObj.reset();
        })
    })
}