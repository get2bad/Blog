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
/*
function login() {
    $("#vaptchaCode1").attr('value', token);
    $("#loginFormData").submit();
}
*/