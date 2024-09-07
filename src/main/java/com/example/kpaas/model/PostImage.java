package com.example.kpaas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Posts_Image")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)  // post_id는 필수
    private Post post;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_type", nullable = false)
    private String fileType;


    // 기본 생성자
    public PostImage() {}

    public PostImage(Post post, String fileName, String filePath, String fileType, String fileSize) {
        this.post = post;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    // Getters and Setters
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

}
