package com.example.kpaas.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Post_Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

    // Getter 및 Setter 메서드들
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(Long category) {
        this.categoryId = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category) {
        this.categoryName = category;
    }
}
