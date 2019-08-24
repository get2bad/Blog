'use strict';
//创建配置类对象
$(function(){
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

//获取现在的时间
function time(){
    var date = new Date(),
        hours = date.getHours(),
        minutes = date.getMinutes(),
        seconds = date.getSeconds();

    //invokes function to make sure number has at least two digits
    hours = addZero(hours);
    minutes = addZero(minutes);
    seconds = addZero(seconds);

    //changes the html to match results
    document.getElementsByClassName('hours')[0].innerHTML = hours;
    document.getElementsByClassName('minutes')[0].innerHTML = minutes;
    document.getElementsByClassName('seconds')[0].innerHTML = seconds;
}

//小于10自动添加0
function addZero (val){
    return (val <= 9) ? ("0" + val) : val;
}

//高亮
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
