'use strict';
var base = +new Date(2019, 8, 6);
var oneDay = 24 * 3600 * 1000;
var date = [];

var data = [Math.random() * 150];
var now = new Date(base);

var ajaxInfo;
$(function(){
    ajaxInfo = Object.create(ajaxInfos);

    initGaugeCharts(1,'cpuInfo','CPU','gauge','cpu占用');
    initGaugeCharts(2,'memoryInfo','内存','gauge','内存占用');
    initGaugeCharts(3,'diskInfo','硬盘','gauge','硬盘占用');
    //initGaugeCharts(4,'picInfo','上传','gauge','文件占用');
    initCategoryCharts('articalPie');

    var location = window.location+"";
    var realLocation = location.split("/");
    $('#locations').text(realLocation[0]+"//"+realLocation[2]);

    //设置IP地址
    $('#ipLocation').text(returnCitySN.cname);
    $('#ipAddress').text(returnCitySN.cip);
    //设置所有的基本信息
    getAllInfos();
    getUserInfo(2);
});
function initCategoryCharts(elementId) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(elementId));
    //从后台获取相关数据
    var v = getData();
    //重建数组
    var nameArray = new Array();
    var categoryValue = new Array();
    $.each(v,function(index,data){
        nameArray.push(data.categoryName);
        var category = new Object();
        category.value = (data.count) *1;
        category.name = data.categoryName;
        categoryValue.push(category);
    });
    var option = {
        title : {
            text: '所有文章分类占比',
            subtext: '来自后台统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>分类名称:{b}共{c}篇(占总比{d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: nameArray
        },
        series : [
            {
                name: '网站文章组成',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:categoryValue,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    //结束
    myChart.setOption(option);
}
//获取数据的方法
function getData(){
    var value = [];
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : 'POST',
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getArticalPie,
        //是否异步执行命令
        async: ajaxInfo.limitAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        //data : jsonString,
        //dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                value = result.data;
            }else{
                tips(result.msg,'topCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            tips('请求失败，请您检查！','topCenter');
        }
    });

    return value;
}
function initGaugeCharts(options,elementId,chartsTitle,chartsType,dataName){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(elementId));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: chartsTitle, //标题文本内容
        },
        toolbox: { //可视化的工具箱
            show: false,
            feature: {
                restore: { //重置
                    show: false
                },
                saveAsImage: {//保存图片
                    show: false
                }
            }
        },
        tooltip: { //弹窗组件
            formatter: "{a}:{c}%"
        },
        series: [{
            name: dataName,
            type: chartsType,
            detail: {formatter:'{value}%'},
            data: [{value: 0, name: ''}]
        }]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    setInterval(function(){//把option.series[0].data[0].value的值使用random()方法获取一个随机数
        var values = getGaugeInfo(options);
        option.series[0].data[0].value = values;
        myChart.setOption(option, true);
    }, 1000);
}

function getGaugeInfo(options){
    var num = 0.0;
    var jsonUrl = '';
    if(options === 1){
        jsonUrl = ajaxInfo.getCpuInfo;
    }else if(options ===2){
        jsonUrl = ajaxInfo.getMemInfo;
    }else if (options ===3){
        jsonUrl = ajaxInfo.getDiskInfo;
    }else{

    }
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : 'POST',
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : jsonUrl,
        //是否异步执行命令
        async: ajaxInfo.limitAsyc,
        //不进行缓存
        cache: ajaxInfo.limitCache,
        //数据，json字符串
        //data : jsonString,
        //dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                num = (result.data)*1;
            }else{
                tips(result.msg,'topCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            tips('请求失败，请您检查！','topCenter');
        }
    });

    return num;
}

function getAllInfos(){
    //页面初始化，进行ajax请求
    $.ajax({
        //请求方式
        type : ajaxInfo.postType,
        //请求的媒体类型
        contentType: ajaxInfo.jsonRequestContentType,
        //请求地址
        url : ajaxInfo.getAllBaseInfos,
        //是否异步执行命令
        async: ajaxInfo.allowAsyc,
        //不进行缓存
        cache: ajaxInfo.allowCache,
        //数据，json字符串
        //data : jsonString,
        dataType: ajaxInfo.jsonDataType,
        //请求成功
        success : function(result) {
            if(result.code ==1){
                //设置相应的信息
                $('#articalCount').text(result.data.articalCount);
                $('#viewCount').text(result.data.viewCount);
                $('#commentCount').text(result.data.commentCount);
                $('#userCount').text(result.data.userCount);
                $('#noteCount').text(result.data.noteCount);
                $('#categoryCount').text(result.data.categoryCount);
                $('#fileCount').text(result.data.fileCount);
                $('#jdkVersion').text(result.data.jdkVersion);
                $('#articalCommentCount').text(result.data.commentCount);
                $('#systemInfo').text(result.data.systemInfo);
                $('#serverIpAddress').text(result.data.serverIpAddress);
                $('#systemArch').text(result.data.systemArch);
                $('#cpuType').text(result.data.cpuType);
                $('#cpuSpeed').text(result.data.cpuSpeed);
                $('#memory').text(result.data.memory);
                $('#disk').text(result.data.disk);
            }else{
                tips(result.msg,'topCenter');
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            tips('请求失败，请您检查！','topCenter');
        }
    });
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