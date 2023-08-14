package com.jw.amapp.util;

/**
 * 계좌 종류 Enum 클래스
 * @author 김종원
 *
 */
public enum AccountType {
    ACCOUNT_ALL("전체"), ACCOUNT_GENERAL("입출금계좌"), ACCOUNT_MINUS("마이너스계좌");

    private final String name;
    
    private AccountType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
