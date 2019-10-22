'use strict';
var ajaxInfo;
var start = 0;
var ifFullScroll = 0;
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
            //$('#pageCounter').css('margin-left','15%');
        }else{
            //大于1200px将左边div缩小到70%
            $('.content-left').css('width','70%');
            //将右边div的display设置为block
            $('.content-right').css('display','block');
            //修改登陆地址
            $('#loginAddr').attr('href','#0');
            //$('#pageCounter').css('margin-left','35%');
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
    //
    $('#city').text(returnCitySN.cname);
    //$('#ipAddress').text(returnCitySN.cip);
    var visitTime = $.cookie('visitTime');

    if(typeof(visitTime) ==="undefined"){
        $.cookie('visitTime',1);
        visitTime = 1;
        $('#visitTime').text(visitTime);
    }else{
        visitTime = parseInt(visitTime);
        visitTime +=1;
        $.cookie('visitTime',visitTime);
        $('#visitTime').text(visitTime);
    }
    getUserInfo(1);
    getHotArtical(start);

    scrollBottomToGetMoreInfo();

    getAllCategory();
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

//获取热门文章
function getHotArtical(start) {
    ajaxInfo = Object.create(ajaxInfos);
    var jsonString = '{"start":'+start+'}';
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllPassUrl,
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
                if(ifFullScroll ===0){
                    $("#scrollItems").append('<div class="item active">\n' +
                        '                        <img src="../../pic/pic'+getRandomNum()+'.jpg" alt="..." style="width: 100%;" />\n' +
                        '                        <div class="carousel-caption">\n' +
                        '                            <div class="jumbotron visible-lg visible-md">\n' +
                        '                                <h2>'+result.data[0].articalTitle+'</h2>\n' +
                        '                                <p>\n' +
                        '                                    '+result.data[0].articalIntroduce+'\n' +
                        '                                </p>\n' +
                        '                            </div>\n' +
                        '                            <div class="visible-xs visible-sm"><p>Java发展态势</p></div>\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                    <div class="item">\n' +
                        '                        <img src="../../pic/pic'+getRandomNum()+'.jpg" alt="..." style="width: 100%;" />\n' +
                        '                        <div class="carousel-caption">\n' +
                        '                            <div class="jumbotron visible-lg visible-md">\n' +
                        '                                <h2>'+result.data[1].articalTitle+'</h2>\n' +
                        '                                <p>\n' +
                        '                                    '+result.data[1].articalIntroduce+'\n' +
                        '                                </p>\n' +
                        '                            </div>\n' +
                        '                            <div class="visible-xs visible-sm"><p>PHP介绍</p></div>\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                    <div class="item">\n' +
                        '                        <img src="../../pic/pic'+getRandomNum()+'.jpg" alt="..." style="width: 100%;" />\n' +
                        '                        <div class="carousel-caption">\n' +
                        '                            <div class="jumbotron visible-lg visible-md">\n' +
                        '                                <h2>'+result.data[2].articalTitle+'</h2>\n' +
                        '                                <p>\n' +
                        '                                    '+result.data[2].articalIntroduce+'\n' +
                        '                                </p>\n' +
                        '                            </div>\n' +
                        '                            <div class="visible-xs visible-sm"><p>Python介绍</p></div>\n' +
                        '                        </div>\n' +
                        '                    </div>');

                    ifFullScroll ++;
                }
                $.each(result.data,function(index,data){
                    //tips(data.picIntroduceUrl,"middleCenter");
                    $("#articalPanel").append("<div id='pageInfo' class=\"col-md-12\">\n" +
                        "                                    <!--第1篇文章-->\n" +
                        "                                    <div class=\"artical\">\n" +
                        "                                        <a href=\"/content/"+data.articalId+".html\" target=\"_blank\">\n" +
                        "                                            <div class=\"artical-left pull-left hidden-xs\">\n" +
                        "                                                <img src=\""+data.picIntroduceUploadUrl+"\" style='width: 64px;height: 64px;'/>\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"artical-right pull-left\">\n" +
                        "                                                <h4 class=\"pull-left\">"+data.articalTitle+"</h4>\n" +
                        "                                                <p class=\"pull-left\">\n" +
                        "                                                    "+data.articalIntroduce.substring(0,90)+"...."+"\n" +
                        "                                                </p>\n" +
                        "                                                <div class=\"clearfix\"></div>\n" +
                        "                                                <div class=\"infoTips hidden-xs\">\n" +
                        "                                                    <i class=\"iconfont icon-bokeyuan\">"+data.categoryName+"</i>\n" +
                        "                                                    <i class=\"iconfont icon-reloadtime\">"+data.postTime.substring(0,19)+"</i>\n" +
                        "                                                    <i class=\"iconfont icon-yanjing\">"+data.viewCount+"</i>\n" +
                        "                                                    <i class=\"iconfont icon-ziyuan\">"+data.articalCommentCount+"</i>\n" +
                        "                                                </div>\n" +
                        "                                            </div>\n" +
                        "                                        </a>\n" +
                        "                                    </div>\n" +
                        "                                </div>");
                });
                if(result.data[0].isAll ===1){
                    $('#pageTips').text("😅 文章已经全部展示咯~");
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

function scrollBottomToGetMoreInfo(){
    window.onscroll = function () {
        if (getScrollTop() + getClientHeight() == getScrollHeight()) {
            //alert("到达底部");
            //浏览器滑到底部执行ajax动态获取数据
            getHotArtical(start+7);
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

function getRandomNum() {
    return Math.floor((Math.random()*10)+1);
}




