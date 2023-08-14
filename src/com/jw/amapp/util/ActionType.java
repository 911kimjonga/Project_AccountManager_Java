package com.jw.amapp.util;

/**
 * 기능 종류 Enum 클래스
 * @author 김종원
 *
 */
public enum ActionType {
    ACTION_ALL("기능선택"), ACCOUNT_ADD("등록"), ACCOUNT_CHECK("조회"), ACCOUNT_SEARCH("검색"), ACCOUNT_DELETE("삭제");

    private final String name;
    
    private ActionType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
