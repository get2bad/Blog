'use strict';
$(function() {
    //getAllCategory();
    //激活动态效果
    $('.category').bootstrapDropdownOnHover({
        mouseOutDelay: 50,
        //在宽度为多少像素时自动隐藏效果
        responsiveThreshold: 888,
        hideBackdrop: true
    });
    getUserInfo(0);
});