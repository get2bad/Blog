'use strict';
$(function() {
    /*屏幕宽度小于900px时将左边的导航栏设置为小条*/
    var width = 0;
    function resize(){
        width = $(window).width();
        var isSmallScreen = width < 900;
        if(isSmallScreen){
            //显示导航栏控制按钮
            $('#toggle-sidebar').css('display','block');
            $('#toggle-sidebar').click();
            $('.pageContent').css('left','40px');
        }else{
            //隐藏显示点击按钮，侧边栏出现
            $('#toggle-sidebar').css('display','block');
            $('#toggle-sidebar').css('color','#D5D6E2 !important');
            $('#toggle-sidebar').click();
            $('.pageContent').css('left','300px');
        }
    }
    //将屏幕变动注册到window.onresize函数 //自动触发使用trigger函数
    $(window).on('resize',resize).trigger('resize');
});

function  toggleNav() {
    if(($('#floatNav').hasClass('toggled'))){
        $('#toggle-sidebar').css('display','block');
        //$('#toggle-sidebar').click();
        $('.pageContent').css('left','40px');
    }else{
        $('#toggle-sidebar').css('display','block');
        $('#toggle-sidebar').css('color','#D5D6E2 !important');
        //$('#toggle-sidebar').click();
        $('.pageContent').css('left','300px');
    }
}