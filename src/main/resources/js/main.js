'use strict';
//创建配置类对象
var vaptchaCode;
var ajaxInfo;
$(function(){
    vaptchaCode = Object.create(vaptchaBaseInfo);
    ajaxInfo = Object.create(ajaxInfos);
    /*时钟效果*/
    window.setInterval(function () {
        time();
        ampm();
        whatDay();
    },1000);
    //激活动态效果
    $('.category').bootstrapDropdownOnHover({
        mouseOutDelay: 50,
        //在宽度为多少像素时自动隐藏效果
        responsiveThreshold: 888,
        hideBackdrop: true
    });
    /*屏幕宽度小于1200px时将左边的width设置为100%右边div自动隐藏*/
    var width = 0;
    function resize(){
        width = $(window).width();
        var isSmallScreen = width < 1200;
        if(isSmallScreen){
            //当小于1200px时将左边div放大到100%
            $('.content-left').css('width','100%');
            //将右边div的display设置为none
            $('.content-right').css('display','none');
            //去除点击事件→直接点击，然后连接到login界面
            $('.main-nav').unbind('click');
            //修改登陆地址
            $('#loginAddr').attr('href','/login');
        }else{
            //大于1200px将左边div缩小到70%
            $('.content-left').css('width','70%');
            //将右边div的display设置为block
            $('.content-right').css('display','block');
            //修改登陆地址
            $('#loginAddr').attr('href','#0');
        }
        scrollNavTabs();
    }
    //将屏幕变动注册到window.onresize函数 //自动触发使用trigger函数
    $(window).on('resize',resize).trigger('resize');
    //控制特色产品介绍导航标签容器的宽度
    function scrollNavTabs() {
        var $ulWapper = $('#nav-tabs');
        //因为最开始的li有padding-left = 20 算在宽度中
        var li_Width = 25;
        $ulWapper.children().each(function(index,element){
            li_Width += element.clientWidth;
        });
        if(li_Width > width){
            $ulWapper.css('width',li_Width).parent().css('overflow-x','scroll');
        }
    }
    getUserInfo();
});

//gets current time and changes html to reflect it
function time(){
    var date = new Date(),
        hours = date.getHours(),
        minutes = date.getMinutes(),
        seconds = date.getSeconds();

    //make clock a 12 hour clock instead of 24 hour clock
    /*hours = (hours > 12) ? (hours - 12) : hours;
    hours = (hours === 0) ? 12 : hours;*/

    //invokes function to make sure number has at least two digits
    hours = addZero(hours);
    minutes = addZero(minutes);
    seconds = addZero(seconds);

    //changes the html to match results
    document.getElementsByClassName('hours')[0].innerHTML = hours;
    document.getElementsByClassName('minutes')[0].innerHTML = minutes;
    document.getElementsByClassName('seconds')[0].innerHTML = seconds;
}

//turns single digit numbers to two digit numbers by placing a zero in front
function addZero (val){
    return (val <= 9) ? ("0" + val) : val;
}

//lights up either am or pm on clock
function ampm(){
    var date = new Date(),
        hours = date.getHours(),
        am = document.getElementsByClassName("am")[0].classList,
        pm = document.getElementsByClassName("pm")[0].classList;


    (hours >= 12) ? pm.add("light-on") : am.add("light-on");
    (hours >= 12) ? am.remove("light-on") : pm.remove("light-on");
}

//lights up what day of the week it is
function whatDay(){
    var date = new Date(),
        currentDay = date.getDay(),
        days = document.getElementsByClassName("day");

    //iterates through all divs with a class of "day"
    for (var x in days){
        //list of classes in current div
        var classArr = days[x].classList;

        (classArr !== undefined) && ((x == currentDay) ? classArr.add("light-on") : classArr.remove("light-on"));
    }
}

function about(){
    alert("本网站所有者Wills,大家有任何需要交流的，请您点击 '联系我们'");
}

function advertisement(){
    alert("广告合作，请您联系邮箱loveing490@qq.com 或者请您点击 '联系我们'");
}

function disclaimer(){
    alert("本前端页面归属权归Wills所有，如若您需要前端页面的源码，请您移步至github去clone我的仓库，感谢!");
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
var token = "";
function setVaptchaContainer(vaptchaContainerId,sceneText){
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
//登陆按钮单击事件
function loginAccount(){
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
                    $.cookie("UserRedisSessionID",result.data['sessionId']);
                    $.cookie("UserID",result.data['userId']);
                    //然后跳转到首页
                    location.href="/";
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

function resetPwd() {
    if((token ==null)||(token =='')){
        //如果为空说明没有进行人机验证操作
        tips("请您进行人机验证,验证通过后方可进行后续操作！","topCenter");
    }else{
        //不为空说明进行了人机操作，进行表单提交
        $("#vaptchaCode3").attr('value',token);

    }
}
function judgeTokenIsEmpty(){
    if((token ==null)||(token =='')){
        tips("请您进行人机验证,验证通过后方可进行后续操作！","topCenter");
    }else{
        $("#vaptchaCode3").val(token);

    }
}
function chooseBirthday() {
    rd.show();

}
//发送验证码按钮点击事件
function sendAuthCode(btn, time) {
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
                console.log(result);
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

function checkInputValueLength(value,errorObj){
    if(value.length ==0){
        $('.'+errorObj).addClass('is-visible');
    }else{
        $('.'+errorObj).removeClass('is-visible');
    }
}