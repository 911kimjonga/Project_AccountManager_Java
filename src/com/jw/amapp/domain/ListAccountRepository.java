package com.jw.amapp.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 메모리(리스트, 콜렉션)를 통한 은행계좌 목록 저장 및 관리 (생성, 검색, 삭제) 구현체
 * 
 * @author 김종원
 * @since  2.0
 *
 */
public class ListAccountRepository implements AccountRepository {

    // 필드
    private List<Account> accounts;

    // 생성자
    public ListAccountRepository() {
        this.accounts = new LinkedList<>();
    }

    // get/set 메소드
    /**
     * 전체 계좌 목록 갯수
     * @return 계좌 목록 수
     */
    public int getCount() {
        return accounts.size();
    }
    
    /**
     * 전체 계좌 목록 조회
     * @return 전체 계좌 목록
     */
    public List<Account> getAccounts() {
        return accounts;
    }
    
    // 기능 메소드
    /**
     * 신규 계좌 등록 기능
     * @param account 신규 등록할 계좌
     * @return 등록 여부
     */
    public boolean createAccount(Account account) {
        accounts.add(account);
        return true;
    }
    
    /**
     * 계좌번호로 계좌 검색하는 기능
     * @param accountNum 검색할 계좌의 계좌번호
     * @return 검색된 계좌
     */
    public Account searchAccount(String accountNum) {
        for (Account account : accounts) {
            if (account.getAccountNum().equals(accountNum)) {
                return account;
            }
        }
        return null;
    }

    
    /**
     * 예금주명으로 계좌 검색하는 기능
     * @param accountOwner 검색할 계좌의 예금주명
     * @return 검색된 계좌
     */
    public List<Account> searchAccountByOwner(String accountOwner) {
        
        List<Account> searchAccounts = new ArrayList<>();
        boolean accountFound = false; 
        for (Account account : accounts) {
            if (account.getAccountOwner().equals(accountOwner)) {
                searchAccounts.add(account);
                accountFound = true;
            }   
        }
        
        if (!accountFound) {
            return null;
        }
        return searchAccounts;
    }


    /**
     * 계좌를 삭제하는 기능
     * @param accountNum 삭제할 계좌의 계좌번호
     * @return 계좌 삭제 성공 여부
     */
    public boolean removeAccount(String accountNum) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNum().equals(accountNum)) {
                accounts.remove(i);
                return true;
            }
        }
        return false;
    }

}
