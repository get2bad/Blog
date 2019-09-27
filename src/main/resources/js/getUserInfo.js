'use strict';

/*
* 获取用户基本信息，用于填充页面，隐藏登陆按钮
* 当页面创建时，向后台发送ajax，后台使用
* SecurityUtils.getSubject().getSession().getID()
* 获取sessionID，然后去redis中验证sessionID是否存在
* 如果存在，则说明session处在有效期，向前端页面发送json（user的基本信息）
* 如果不存在，则说明session已经失效，要求用户重新登陆
* */
function getUserInfo(sign){
    var ajaxInfo = Object.create(ajaxInfos);
    var sessionID = $.cookie('UserRedisSessionID');
    var userId = $.cookie('UserID');
    console.log(sessionID);
    //手动组装json字符串，内容是cookie中存储的sessionID
    var token = '{"token":"'+sessionID+'","userId":"'+userId+'"}';
    $.ajax({
        //请求方式
        type: ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url: ajaxInfo.getUserInfoUrl,
        //是否异步执行命令
        async: ajaxInfo.limitAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data: token,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success: function (result) {
            console.log(result.data['userInfo']);
            if (result.code === 1) {
                //隐藏登陆按钮
                $("#loginBtn").css('display','none');
                $("#userIcon").css('display','inline-block');
                $("#userOptions").css('display','inline-block');
                $('#setUserName').text(result.data['userInfo'].userName);

                if(sign ===1){
                    //设置欢迎标语
                    tips(result.data['msg'], 'middleCenter');
                }
                //设置相关信息
            } else {
                //隐藏头像按钮
                $("#userIcon").css('display','none');
                $("#userOptions").css('display','none');
                $("#loginBtn").css('display','inline-block');
                //设置提示标语
                tips(result.data, 'middleCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            tips('请求失败，请您检查！', 'middleCenter');
            //location.href = "/login";
        }
    });
}