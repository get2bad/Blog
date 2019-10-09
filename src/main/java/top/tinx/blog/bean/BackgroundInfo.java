package top.tinx.blog.bean;

import java.io.Serializable;

public class BackgroundInfo implements Serializable {

    private int articalCount;
    private int viewCount;
    private int commentCount;
    private int userCount;
    private int noteCount;
    private int categoryCount;
    private int fileCount;

    private String jdkVersion;
    private String userName;
    private String systemInfo;
    private String serverIpAddress;
    private String systemArch;
    private String cpuType;
    private String cpuSpeed;
    private String memory;
    private String disk;

    public int getArticalCount() {
        return articalCount;
    }

    public void setArticalCount(int articalCount) {
        this.articalCount = articalCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo) {
        this.systemInfo = systemInfo;
    }

    public String getServerIpAddress() {
        return serverIpAddress;
    }

    public void setServerIpAddress(String serverIpAddress) {
        this.serverIpAddress = serverIpAddress;
    }

    public String getSystemArch() {
        return systemArch;
    }

    public void setSystemArch(String systemArch) {
        this.systemArch = systemArch;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public String getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    @Override
    public String toString() {
        return "BackgroundInfo{" +
                "articalCount=" + articalCount +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                ", userCount=" + userCount +
                ", noteCount=" + noteCount +
                ", categoryCount=" + categoryCount +
                ", fileCount=" + fileCount +
                ", jdkVersion='" + jdkVersion + '\'' +
                ", userName='" + userName + '\'' +
                ", systemInfo='" + systemInfo + '\'' +
                ", serverIpAddress='" + serverIpAddress + '\'' +
                ", systemArch='" + systemArch + '\'' +
                ", cpuType='" + cpuType + '\'' +
                ", cpuSpeed='" + cpuSpeed + '\'' +
                ", memory='" + memory + '\'' +
                ", disk='" + disk + '\'' +
                '}';
    }
}
