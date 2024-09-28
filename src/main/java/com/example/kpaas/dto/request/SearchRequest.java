package com.example.kpaas.dto.request;

import java.util.List;

public class SearchRequest {
    private List<String> category;
    private String query;
    private int page;

    // Getters and setters
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
