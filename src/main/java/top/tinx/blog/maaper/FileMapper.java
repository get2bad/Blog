package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.tinx.blog.bean.File;

import java.util.List;
@Mapper
public interface FileMapper {

    @Select("select * from tb_file")
    public List<File> getAllFiles();

    @Insert("insert into tb_file(file_path,file_path_location,file_type,file_description) values(#{file.filePath},#{file.filePathLocation},#{file.fileType},#{file.fileDescription})")
    public void insertFile(@Param("file") File file);
}
