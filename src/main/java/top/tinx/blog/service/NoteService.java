package top.tinx.blog.service;

import top.tinx.blog.bean.Note;

import java.util.List;

public interface NoteService {

    public List<Note> getAllNote();

    public Note getNoteById(String id);

    public void insertNote(Note note);
}
