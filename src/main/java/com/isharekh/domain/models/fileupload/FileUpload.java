package com.isharekh.domain.models.fileupload;

import javax.persistence.*;

/**
 * Created by : Ron Rith
 * Create Date: 01/28/2018.
 */
@Entity
@Table(name = "file_upload")
public class FileUpload {
    private Long id;
    private String fileName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileUpload(Long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public FileUpload() {
    }

    @Override
    public String toString() {
        return "FileUpload{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
