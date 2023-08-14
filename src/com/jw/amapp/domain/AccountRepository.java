package com.jw.amapp.domain;

/**
 * 은행계좌 목록 저장 및 관리 인터페이스
 * @author 김종원
 */
public interface AccountRepository {
    
    public int getCount();
    public Account[] getAccounts();
    public boolean createAccount(Account account);
    public Account searchAccount(String accountNum);
    public Account[] searchAccountByOwner(String accountOwner);
    public boolean removeAccount(String accountNum);
    
}
