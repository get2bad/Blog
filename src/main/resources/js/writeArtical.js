'use strict';
var editor;
$(function(){
    //富文本编辑器
    var E = window.wangEditor;
    editor = new E('#editor');
    // 关闭粘贴内容中的样式
    editor.customConfig.pasteFilterStyle = false
    // 忽略粘贴内容中的图片
    editor.customConfig.pasteIgnoreImg = true
    // 图片上传配置
    editor.customConfig.uploadFileName = 'articalContentPic'; //设置文件上传的参数名称
    editor.customConfig.uploadImgServer = '/artical/upload';  // 上传图片到服务器
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;  //限制上传图片的大小 限制3mb
    // 限制一次最多上传 10张图片
    editor.customConfig.uploadImgMaxLength = 10;
    // 自定义菜单配置
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    editor.create();

    /*防止xxs攻击配置
    // 使用函数名 filterXSS，用法一样
    var options = {};  // 自定义规则
    myxss = new s.FilterXSS(options);
    // 以后直接调用 myxss.process() 来处理即可
    html = myxss.process("alert('111');");*/

    $('.dropify').dropify({
        messages: {
            'default': '点击这里上传文章封面',
            'replace': '点击或拖拽文件到这里来替换文件',
            'remove':  '移除文件',
            'error':   '对不起，你上传的文件大于3M'
        }
    });

});

function getValuee() {
    //获取富文本编辑器中的内容（包含html代码）
    var content = editor.txt.html();
    var inputValue = $('#content_text');
    inputValue.val(content);
    var formValue = $('#articalContent').serializeArray();
    formValue.push({name:'content',value:content});
    var uploadFile = $('#input-file-now').val();
    formValue.push({name:'picIntroduceUpload',value:uploadFile});
    var jsonContent = JSON.stringify(formValue);
    console.log(jsonContent);
    document.getElementById('articalContent').submit();
}