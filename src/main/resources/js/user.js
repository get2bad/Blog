'use strict';
//vaptcha自动生成的token
var token = '';
//vaptcha类对象
var vaptchaInfo;
var ajaxInfo;
$(function () {
    vaptchaInfo = Object.create(vaptchaBaseInfo);
    ajaxInfo = Object.create(ajaxInfos);
    /*萤火虫特效开启*/
    $.firefly({
        color: '#f7e1b5',
        minPixel: 1,
        maxPixel: 3,
        total: 99,
        on: 'body',
        borderRadius: 50
    });
    $('#loginForm').css('display', 'block')
    $('body').fadeOut(0);
    $('body').fadeIn(3000);
    vaptcha({
        //配置参数
        // 验证单元id
        vid: vaptchaInfo.vid,
        lang: vaptchaInfo.lang,
        // 展现类型 点击式
        type: vaptchaInfo.type,
        scene: vaptchaInfo.loginScene,
        // 按钮容器，可为Element 或者 selector
        container: '#vaptchaContainer1'
    }).then(function (vaptchaObj) {
        // 调用验证实例 vaptchaObj 的 render 方法加载验证按钮
        vaptchaObj.render()
        vaptchaObj.listen('pass', function () {
            // 验证成功， 进行登录操作
            //如果token为空说明没有通过验证，如果token不为空说明通过了验证
            token = vaptchaObj.getToken();
            console.log(token);
            judgeTokenIsEmpty();
        })
        vaptchaObj.listen('close', function () {
            //验证弹窗关闭触发
        })
        $('input[type="reset"]').on('click', function () {
            vaptchaObj.reset();
        })
    })
});

function judgeTokenIsEmpty() {
    if ((token == null) || (token == '')) {
        tips("请您进行人机验证,验证通过后方可进行后续操作！", "topCenter");
    } else {
        $("#vaptchaCode3").val(token);

    }
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

function login() {
    $("#vaptchaCode1").attr('value', token);
    $("#loginFormData").submit();
}

function setVaptchaContainer(vaptchaContainerId, sceneText) {
    //vaptcha验证码加载
    vaptcha({
        //配置参数
        // 验证单元id
        vid: vaptchaInfo.vid,
        lang: vaptchaInfo.lang,
        // 展现类型 点击式
        type: vaptchaInfo.type,
        scene: sceneText,
        // 按钮容器，可为Element 或者 selector
        container: '#' + vaptchaContainerId
    }).then(function (vaptchaObj) {
        // 调用验证实例 vaptchaObj 的 render 方法加载验证按钮
        vaptchaObj.render()
        vaptchaObj.listen('pass', function () {
            // 验证成功， 进行登录操作
            //如果token为空说明没有通过验证，如果token不为空说明通过了验证
            token = vaptchaObj.getToken();
            console.log(token);
            judgeTokenIsEmpty();
        })
        vaptchaObj.listen('close', function () {
            //验证弹窗关闭触发
        })
        $('input[type="reset"]').on('click', function () {
            vaptchaObj.reset();
        })
    })
}

(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

//登陆按钮单击事件
function loginAccount() {
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
        tips("请您进行人机验证,验证通过后方可进行后续操作！", "topCenter");
    } else {
        //不为空说明进行了人机操作，进行表单提交
        $("#vaptchaCode1").attr('value', token);
        var params = $("#login-form").serializeJson();
        console.log(JSON.stringify(params));
        $.ajax({
            //请求方式
            type: ajaxInfo.postType,
            //请求的媒体类型
            contentType: ajaxInfo.jsonRequestContentType,
            //请求地址
            url: ajaxInfo.loginUrl,
            //是否异步执行命令
            async: ajaxInfo.limitAsyc,
            //不进行缓存
            cache: ajaxInfo.limitCache,
            //数据，json字符串
            data: JSON.stringify(params),
            dataType: ajaxInfo.jsonDataType,
            //请求成功
            success: function (result) {
                console.log(result);
                if (result.code == 1) {
                    tips(result.data, 'topCenter');
                    location.href = "/";
                } else {
                    tips(result.data, 'topCenter');
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                //_obj.reset(); //重置验证码
                tips('请求失败，请您检查！', 'topCenter');
                //location.href = "/login";
            }
        });
    }
}

function regist() {
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
            console.log(JSON.stringify(data));
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
                    console.log(result);
                    if (result.code == 1) {
                        tips(result.data, 'topCenter');
                        //location.href = "/";
                    } else {
                        tips(result.data, 'topCenter');
                    }
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    //_obj.reset(); //重置验证码
                    tips('请求失败，请您检查！', 'topCenter');
                    //location.href = "/login";
                }
            });

        }
    }
}

function resetPwd() {
    if ((token == null) || (token == '')) {
        //如果为空说明没有进行人机验证操作
        tips("请您进行人机验证,验证通过后方可进行后续操作！", "topCenter");
    } else {
        //不为空说明进行了人机操作，进行表单提交
        $("#vaptchaCode3").attr('value', token);

    }
}

function chooseBirthday() {
    rd.show();
}

//发送验证码按钮点击事件
function sendAuthCode(btn, time) {
    //正则表达式
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]*)*)$/;
    ;

    //组装jason字符串
    var emails = $('#email').val();
    var data = $('#regist-form').serializeJson();
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
                sendAuthCodeChangeBtnValue(btn, time);
                console.log(result);
                if (result.code == 1) {
                    tips(result.data, 'middleCenter');
                    //location.href="/";
                } else {
                    tips(result.data, 'middleCenter');
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

function sendAuthCodeChangeBtnValue(obj, time) {
    obj.disabled = true;
    setTimeout(function () {
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
}

function checkInputValueLength(value,errorObj){
    if(value.length ==0){
        $('.'+errorObj).addClass('is-visible');
    }else{
        $('.'+errorObj).removeClass('is-visible');
    }
}