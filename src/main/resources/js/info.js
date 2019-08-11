'use strict';
var base = +new Date(2019, 8, 6);
var oneDay = 24 * 3600 * 1000;
var date = [];

var data = [Math.random() * 150];
var now = new Date(base);

$(function(){
    initGaugeCharts('cpuInfo','Cpu','gauge','cpu占用','');
    initGaugeCharts('memoryInfo','内存','gauge','内存占用','');
    initGaugeCharts('diskInfo','硬盘','gauge','硬盘占用','');
    initGaugeCharts('picInfo','上传','gauge','文件占用','');
    initCategoryCharts('networkInfo','category','网络吞吐量');
});
function initCategoryCharts(elementId,chartType,chartName) {
        var Chart=echarts.init(document.getElementById(elementId));//初始化
        //用户等待
        Chart.showLoading(
            {text: '获取数据中...'  }
        );
        //自定义变量
        var times=[];
        var legend=[];
        var chartDatas = [];
        //var nodeid = ${nodeid};
        //getData(Chart,elementId); //先执行一次，以防等十秒后才出现图表
        setInterval(function(){//定时器
            getData(Chart, elementId);
        }, 1*1000);//每隔10秒刷新一次
}
//获取数据的方法
function getData(Chart,elementId){
    /*
    //发送Ajax请求
    $.ajax({
        url : "/echarts/getFsLinechart",
        dataType:"json",
        data:{nodeid:elementId},
        type:'post',
        success:function(json){//
            console.log("获取图表数据");
            console.log(json);
            times = json.times;  //拿到后台的数据
            legend = json.legend;
            chartDatas = json.chartDatas;   //拿到后台的数据
            var option = {
                title: {
                    text: '风速统计'
                },

                tooltip: {
                    trigger: 'axis'
                },

                legend: {
                    data:legend
                },

                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },

                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data:times
                },

                yAxis: {
                    type: 'value'
                },
                series : chartDatas
            };*/
            var option = {
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [820, 932, 901, 934, 1290, 1330, 1320],
                    type: 'line',
                    areaStyle: {}
                }]
            };
            //结束
            Chart.hideLoading();
            Chart.setOption(option);
}
function initGaugeCharts(elementId,chartsTitle,chartsType,dataName,jsonRequestUrl){
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
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [{
            name: dataName,
            type: chartsType,
            detail: {formatter:'{value}%'},
            data: [{value: 50, name: ''}]
        }]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    setInterval(function(){//把option.series[0].data[0].value的值使用random()方法获取一个随机数
        option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
        myChart.setOption(option, true);
    }, 2000);
}