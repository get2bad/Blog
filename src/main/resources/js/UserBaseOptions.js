'use strict';
var token = "";
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
            judgeTokenIsEmpty();
            //secondResult(sceneText,token);
        })
        vaptchaObj.listen('close', function() {
            //验证弹窗关闭触发
        })
        $('input[type="reset"]').on('click',function(){
            vaptchaObj.reset();
        })
    })
}
//登陆按钮单击事件
function loginAccount(url){
    var ajaxInfo = Object.create(ajaxInfos);
    var userName = $('#signin-userName').val();
    var password = $('#signin-password').val();
    if(userName.length ==0){
        tips("请您输入用户名然后进行后续操作！", "topCenter");
        $('.loginUserNameWarning').addClass('is-visible');
        return;
    }
    if(password.length ==0){
        tips("请您输入密码然后进行后续操作！", "topCenter");
        $('.loginPwdWarning').addClass('is-visible');
        return;
    }
    if (token.length ==0) {
        //如果为空说明没有进行人机验证操作
        tips("请您进行人机验证,验证通过后方可进行后续操作！","topCenter");
    }else{
        //不为空说明进行了人机操作，进行表单提交
        $("#vaptchaCode1").attr('value',token);
        var params = $("#login-form").serializeJson();
        console.log(JSON.stringify(params));
        $.ajax({
            //请求方式
            type : ajaxInfo.postType,
            //请求的媒体类型
            contentType: ajaxInfo.jsonRequestContentType,
            //请求地址
            url : ajaxInfo.loginUrl,
            //是否异步执行命令
            async: ajaxInfo.limitAsyc,
            //不进行缓存
            cache: ajaxInfo.limitCache,
            //数据，json字符串
            data : JSON.stringify(params),
            dataType: ajaxInfo.jsonDataType,
            //请求成功
            success : function(result) {
                console.log(result);
                if(result.code ==1){
                    //tips(result.data['msg'],'topCenter');
                    //设置cookie的属性（用于保存sessionID） ×存活时间为半个小时的时间 ,{expires:0.021}×
                    // 暂时不使用本方法，sessionID在redis过期后，查不到就要求重新登陆
                    $.cookie("UserRedisSessionID",result.data['sessionId'],{expires:0.021});
                    $.cookie("UserID",result.data['userId'],{expires:0.021});
                    //然后跳转到首页
                    //tips(url,'middleCenter');
                    location.href=url;
                }else{
                    tips(result.data,'topCenter');
                }
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                //_obj.reset(); //重置验证码
                tips('请求失败，请您检查！','topCenter');
                location.href="/login";
            }
        });
    }
}
//注册按钮的单击事件
function regist() {
    var ajaxInfo = Object.create(ajaxInfos);
    var pwd = $("#password").val();
    var confirmPwd = $("#confirmPassword").val();
    if(pwd != confirmPwd){
        tips("对不起，您输入的密码/确认密码不一致，请您重新填写",'middleCenter');
        return;
    }
    if(($("#accept-terms").prop("checked"))==false){
        tips("请您点击我同意条款","middleCenter");
    }else{
        if (token.length ==0) {
            //如果为空说明没有进行人机验证操作
            tips("请您进行人机验证,验证通过后方可进行后续操作！", "topCenter");
        } else {
            $("#vaptchaCode2").attr('value', token);
            //序列化表单成JSON格式
            var data = $("#regist").serializeJson();
            //不为空说明进行了人机操作，进行表单AJAX提交
            $.ajax({
                //请求方式
                type: ajaxInfo.postType,
                //请求的媒体类型
                contentType: ajaxInfo.jsonRequestContentType,
                //请求地址
                url: ajaxInfo.regUrl,
                //是否异步执行命令
                async: ajaxInfo.limitAsyc,
                //不进行缓存
                cache: ajaxInfo.allowCache,
                //数据，json字符串
                data: JSON.stringify(data),
                dataType: ajaxInfo.jsonDataType,
                //请求成功
                success: function (result) {
                    if (result.code == 1) {
                        tips(result.data['msg'], 'topCenter');
                        $.cookie("UserRedisSessionID",result.data['sessionId']);
                        $.cookie("UserID",result.data['userId']);
                        location.href = "/";
                    } else {
                        tips(result.data, 'topCenter');
                    }
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    //_obj.reset(); //重置验证码
                    tips('请求失败，请您将控制台信息发送邮件给loveing490@qq.com！', 'topCenter');
                    //location.href = "/login";
                }
            });

        }
    }
}
//重置密码按钮的单击事件
function resetPwd() {
    tips("对不起，本功能暂未上线，请稍后再试！",'middleCenter');
    /*
    var ajaxInfo = Object.create(ajaxInfos);
    return;
    if((token ==null)||(token =='')){
        //如果为空说明没有进行人机验证操作
        tips("请您进行人机验证,验证通过后方可进行后续操作！","topCenter");
    }else{
        //不为空说明进行了人机操作，进行表单提交
        $("#vaptchaCode3").attr('value',token);

    }
     */
}
//发送验证码按钮点击事件
function sendAuthCode(btn, time) {
    var ajaxInfo = Object.create(ajaxInfos);
    //正则表达式
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]*)*)$/;

    //组装jason字符串
    var emails = $('#email').val();
    var data = $("#regist").serializeJson();
    console.log(JSON.stringify(data));
    if (reg.test(emails)) {
        //向输入框填写的内容发送激活码
        $.ajax({
            //请求方式
            type: ajaxInfo.postType,
            //请求的媒体类型
            contentType: ajaxInfo.jsonRequestContentType,
            //请求地址
            url: ajaxInfo.regEmialUrl,
            //是否异步执行命令
            async: ajaxInfo.allowAsyc,
            //不进行缓存
            cache: ajaxInfo.limitCache,
            //数据，json字符串
            data: JSON.stringify(data),
            dataType: ajaxInfo.jsonDataType,
            //请求成功
            success: function (result) {
                var intervalId ;
                if (result.code == 1) {
                    tips(result.data, 'middleCenter');
                    intervalId = sendAuthCodeChangeBtnValue(btn, time);
                    //location.href="/";
                } else {
                    tips(result.data, 'middleCenter');
                    //clearTimeout(intervalId);
                    //btn.disabled = false;
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                sendAuthCodeChangeBtnValue(btn, time);
                //_obj.reset(); //重置验证码
                /*
                 */
                tips('请求失败，请您检查！', 'topCenter');
                //location.href="/login";
            }
        });
    } else {
        tips('您填写的邮箱格式不正确！','middleCenter')
    }

}
//按钮30秒后才可以重新发送验证码
function sendAuthCodeChangeBtnValue(obj, time) {
    obj.disabled = true;
    var intervalId = setTimeout(function () {
        var x = setInterval(function () {
            time = time - 1000; //reduce each second
            obj.innerHTML = time / 1000;
            if (time == 0) {
                clearInterval(x);
                obj.innerHTML = "重新发送";
                obj.disabled = false;
            }
        }, 1000);
    }, time - 10000);
    return intervalId;
}
//检查输入的input长度问题 使用onkeyup事件
function checkInputValueLength(value,errorObj){
    if(value.length ==0){
        $('.'+errorObj).addClass('is-visible');
    }else{
        $('.'+errorObj).removeClass('is-visible');
    }
}
//检查vaptcha返回的token是否为空
function judgeTokenIsEmpty(){
    if((token ==null)||(token =='')){
        tips("请您进行人机验证,验证通过后方可进行后续操作！","topCenter");
    }else{
        $("#vaptchaCode3").val(token);

    }
}
//激活选择日期控件
function chooseBirthday() {
    rd.show();
}

//二次验证vaptcha的结果
function secondResult(scene,tokens) {
    var vaptcha = Object.create(vaptchaBaseInfo);
    var jsonString = '{"id":'+vaptcha.vid+',"secretkey":'+vaptcha.key+',"scene":'+scene+',"token":'+tokens+',"ip":'+returnCitySN.cip+'}';
    //向输入框填写的内容发送激活码
    $.ajax({
        //请求方式
        type: ajaxInfo.postType,
        //增加请求头
        headers: {
            'Access-Control-Allow-Origin':'*'
        },
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url: ajaxInfo.secondResult,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        data: jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success: function (result) {
            if (result.code == 1) {
                tips(result.data, 'middleCenter');
            } else {
                tips(result.data, 'middleCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            tips('请求失败，请您检查！', 'topCenter');
        }
    });
}

function logout() {
    //删除cookies信息，然后跳转到logout连接
    $.cookie('UserID',null,{expires:-1});
    //alert($.cookie('UserID'));
    $.cookie('UserRedisSessionID', null,{expires:-1});
    //alert($.cookie('UserRedisSessionID'));
    location.href='/logout';
}

function getAllCategory() {
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
                    var categorys = $("#categorys");
                    if(typeof(categorys) !=='undefined'){
                        categorys.append("<div class=\" col-md-6 marginButton10px\"><a href=\""+data.categoryUrl+"\">"+data.categoryName+"</a></div>");
                    }
                    var headerGuide = $("#headerGuide");
                    if(typeof(headerGuide) !=='undefined'){
                        headerGuide.append('<li><a href="'+data.categoryUrl+'">'+data.categoryName+'</a></li>');
                    }
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