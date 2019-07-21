'use strict';
$(function(){
    /*萤火虫特效开启*/
    $.firefly({
        color: '#f7e1b5',
        minPixel: 1,
        maxPixel: 3,
        total : 99,
        on: 'body',
        borderRadius: 50
    });
    $('#loginForm').css('display','block')
    $('body').fadeOut(0);
    $('body').fadeIn(3000);
    /*
    var num = 0;
    function getRandomNumber(){
         num = Math.ceil(Math.random()*20);
    }
    function setDivBackground(){
        var $body = $('body');
        var src = '';
        getRandomNumber();
        switch (num) {
            case 1 : src = '../pic/pic1.jpg';break;
            case 2 : src = '../pic/pic2.jpg';break;
            case 3 : src = '../pic/pic3.jpg';break;
            case 4 : src = '../pic/pic4.jpg';break;
            case 5 : src = '../pic/pic5.jpg';break;
            case 6 : src = '../pic/pic6.jpg';break;
            case 7 : src = '../pic/pic7.jpg';break;
            case 8 : src = '../pic/pic8.jpg';break;
            case 9 : src = '../pic/pic9.jpg';break;
            case 10 : src = '../pic/pic10.jpg';break;
            case 11 : src = '../pic/pic11.jpg';break;
            case 12 : src = '../pic/pic12.jpg';break;
            case 13 : src = '../pic/pic13.jpg';break;
            case 14 : src = '../pic/pic14.jpg';break;
            case 15 : src = '../pic/pic15.jpg';break;
            case 16 : src = '../pic/pic16.jpg';break;
            default : src = '../pic/pic1.jpg' ;break;
        }
        //$body.css('backgroundImage','url("'+src+'")');

        $body.fadeOut(0);
        $body.fadeIn(4000,function(){
            $body.css('backgroundImage','url("'+src+'")');
        });
    }
    setInterval(setDivBackground,5000);*/
});