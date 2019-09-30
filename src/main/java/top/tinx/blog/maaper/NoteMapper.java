package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.tinx.blog.bean.Note;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("select * from tb_note")
    public List<Note> getAllNote();

    @Select("select * from tb_note where note_id = #{id}")
    public Note getAllNoteById(@Param("id")String id);

    @Insert("insert into tb_note(user_id,note_postTime,note_content,note_postIP) values(#{note.userId},#{note.notePostTime}," +
            "#{note.noteContent},#{note.notePostIP})")
    public void insertNote(@Param("note")Note note);
}
