package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Note;
import top.tinx.blog.maaper.NoteMapper;
import top.tinx.blog.service.NoteService;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;


    @Override
    public List<Note> getAllNote(int start,int end) {
        return noteMapper.getAllNote(start,end);
    }

    @Override
    public Note getNoteById(String id) {
        return noteMapper.getAllNoteById(id);
    }

    @Override
    public void insertNote(Note note) {
        noteMapper.insertNote(note);
    }

    @Override
    public int getNoteCount() {
        return noteMapper.getNoteCount();
    }
}
