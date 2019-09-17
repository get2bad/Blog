package top.tinx.blog.service;

import top.tinx.blog.bean.File;

import java.util.List;

public interface FileService {

    List<File> getAllFiles();

    void insertFile(File file);
}
