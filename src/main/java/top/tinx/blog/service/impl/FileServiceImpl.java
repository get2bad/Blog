package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.File;
import top.tinx.blog.maaper.FileMapper;
import top.tinx.blog.service.FileService;

import java.util.List;
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<File> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    @Override
    public void insertFile(File file) {
        fileMapper.insertFile(file);
    }
}
