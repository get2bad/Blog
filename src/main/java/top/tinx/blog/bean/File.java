package top.tinx.blog.bean;

import java.io.Serializable;

public class File implements Serializable {

    private int fileId;
    private String filePath;
    private String filePathLocation;
    private String fileType;
    private String fileDescription;

    public File(String filePath, String filePathLocation, String fileType, String fileDescription) {
        this.filePath = filePath;
        this.filePathLocation = filePathLocation;
        this.fileType = fileType;
        this.fileDescription = fileDescription;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getFilePathLocation() {
        return filePathLocation;
    }

    public void setFilePathLocation(String filePathLocation) {
        this.filePathLocation = filePathLocation;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", filePath='" + filePath + '\'' +
                ", filePathLocation='" + filePathLocation + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}
