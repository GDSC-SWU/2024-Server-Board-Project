package com.example.board.domain.enums;

public enum Category {
    FREE("자유게시판"),
    SECRET("비밀게시판"),
    QUESTION("질문게시판"),
    PROMOTION("홍보게시판");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}