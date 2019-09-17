package top.tinx.blog.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 文件上传使用返回的json数据格式化
 */
public class FileUpload implements Serializable {

    // errno 即错误代码，0 表示没有错误。
    //       如果有错误，errno != 0，可通过下文中的监听函数 fail 拿到该错误码进行自定义处理
    private int errno;

    private List<String> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public FileUpload() {
    }

    public FileUpload(int errno, List<String> data) {
        this.errno = errno;
        this.data = data;
    }

    public FileUpload(int errno) {
        this.errno = errno;
    }

    @Override
    public String toString() {
        return "FileUpload{" +
                "errno=" + errno +
                ", data=" + data +
                '}';
    }

    public static FileUpload buildSuccesss(List<String> picLocation){
        return new FileUpload(0,picLocation);
    }

    public static FileUpload buildError(){
        return new FileUpload(1,null);
    }
}
