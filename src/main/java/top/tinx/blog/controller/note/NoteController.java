package top.tinx.blog.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.tinx.blog.bean.JsonData;
import top.tinx.blog.bean.Note;
import top.tinx.blog.service.NoteService;
import top.tinx.blog.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @PostMapping("insertNote")
    public String insertNote(@RequestParam("userId")String userId, @RequestParam("notePostIP")String notePostIP,
                             @RequestParam("noteContent")String noteContent){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Note note = new Note(Integer.parseInt(userId),sim.format(new Date()),noteContent,notePostIP);
        noteService.insertNote(note);
        return "redirect:/comment";
    }

    @PostMapping("getAllNote")
    @ResponseBody
    public JsonData getAllNote(){
        List<Note> allNote = noteService.getAllNote();
        for (Note note : allNote) {
            note.setUserName(userService.findUserByUserId(note.getUserId()).getUserName());
        }
        return JsonData.buildSuccess(allNote,1);
    }
}
