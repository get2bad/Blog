package top.tinx.blog.bean;

import java.io.Serializable;

public class Note implements Serializable {

    private int noteId;
    private int userId;
    private String userName;
    private String notePostTime;
    private String noteContent;
    private String notePostIP;
    private int isAll;

    public Note(int userId, String notePostTime, String noteContent, String notePostIP) {
        this.userId = userId;
        this.notePostTime = notePostTime;
        this.noteContent = noteContent;
        this.notePostIP = notePostIP;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNotePostTime() {
        return notePostTime;
    }

    public void setNotePostTime(String notePostTime) {
        this.notePostTime = notePostTime;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNotePostIP() {
        return notePostIP;
    }

    public void setNotePostIP(String notePostIP) {
        this.notePostIP = notePostIP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsAll() {
        return isAll;
    }

    public void setIsAll(int isAll) {
        this.isAll = isAll;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", notePostTime='" + notePostTime + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", notePostIP='" + notePostIP + '\'' +
                ", isAll=" + isAll +
                '}';
    }
}
