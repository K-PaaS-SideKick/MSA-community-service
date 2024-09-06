package com.example.kpaas.dto.request;

import java.util.List;

public class SearchRequest {

    private List<String> category; // 검색할 카테고리 목록
    private String query;          // 검색 쿼리

    // Getters and Setters
    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
