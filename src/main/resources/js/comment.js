'use strict';
$(function(){
    getUserInfo(2);
});

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