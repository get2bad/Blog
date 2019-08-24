'use strict';
//vaptcha普通配置类的声明→做全局配置，项目上线后只需改动本代码即可实现上线生产环境
var vaptchaBaseInfo = {
    /*
    var localVid = '5d5a268dfc650e2da0799d17';
    var virtualVid = '5d5d04a9fc650ec7fc7154a1';
    var realVid = '5d59048afc650eaffc3fe204';
    var localKey = 'd979ba5d304240fea37ea0c9ec6f7c91';
    var virtualKey = '40c29540d31e489b97b5c88a9c91b7fe';
    var realKey = '03679a2265e743b5a4867d0079ac6364';
    */
    vid: '5d5d04a9fc650ec7fc7154a1',
    key: 'd979ba5d304240fea37ea0c9ec6f7c91',
    type:'click',//embed invisible
    lang:'zh-CN',//en zh-TW
    https:false,//true
    registScene:'01',
    loginScene:'02',
    commentScene:'03',
    forgetScane:'04'
}
var locations = '192.168.0.102:8080'; //localhost:8080
//ajax基本配置
var ajaxInfos = {
    loginUrl : 'http://'+locations+'/user/login',
    regEmialUrl:'http://'+locations+'/user/regEmial',
    regUrl:'http://'+locations+'/user/reg',
    getUserInfoUrl:'http://'+locations+'/user/getUserInfo',
    postType: 'POST',
    getType: 'GET',
    jsonRequestContentType: 'application/json;charset=UTF-8',
    allowAsyc:true,
    limitAsyc:false,
    allowCache:true,
    limitCache:false,
    jsonDataType:'json',
}


//日期插件基本配置
var rd = new Rolldate({
    el: '#birthday',
    format: 'YYYY-MM-DD',
    beginYear: 1949,
    endYear: 2049,
    minStep:1,
    vale: '1990-01-01',
    lang:{title:'请您选择出生日期',cancle:'取消',confirm:'确认',year:'年',month:'月',day:'日',hour:'时',min:'分',sec:'秒'},
    trigger:'tap',
    init: function() {
        tips("请正确选择您的出生日期","middleCenter");
    },
    moveEnd: function(scroll) {
        //console.log('滚动结束');
    },
    confirm: function(date) {
        //console.log('确定按钮触发');
    },
    cancel: function() {
        //console.log('插件运行取消');
    }
})