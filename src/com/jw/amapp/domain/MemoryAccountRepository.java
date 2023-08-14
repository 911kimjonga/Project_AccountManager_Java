package com.jw.amapp.domain;

/**
 * 메모리(배열)를 통한 은행계좌 목록 저장 및 관리 (생성, 검색, 삭제) 구현체
 * 
 * @author 김종원
 * @since  1.0
 */
public class MemoryAccountRepository implements AccountRepository {

    // 필드
    private Account[] accounts;
    private int count;

    // 생성자
    public MemoryAccountRepository() {
        this(100);
    }
    
    public MemoryAccountRepository(int capacity) {
        this.accounts = new Account[capacity];
    }

    // get/set 메소드
    /**
     * 전체 계좌 목록 갯수
     * @return 계좌 목록 수
     */
    public int getCount() {
        return count;
    }
    
    /**
     * 전체 계좌 목록 조회
     * @return 전체 계좌 목록
     */
    public Account[] getAccounts() {
        return accounts;
    }
    
    // 기능 메소드
    /**
     * 신규 계좌 등록 기능
     * @param account 신규 등록할 계좌
     * @return 등록 여부
     */
    public boolean createAccount(Account account) {
//      데이터 검증이 필요하지만 편의상 생략
        accounts[count++] = account;
        return true;
    }
    
    /**
     * 계좌번호로 계좌 검색하는 기능
     * @param accountNum 검색할 계좌의 계좌번호
     * @return 검색된 계좌
     */
    public Account searchAccount(String accountNum) {
        for (int i = 0; i < count; i++) {
            String number = accounts[i].getAccountNum();
            if ( number.equals(accountNum)) { // 동일 계좌번호 여부 확인
                return accounts[i];
            }
        }
        return null;
    }
    
    /**
     * 예금주명으로 계좌 검색하는 기능
     * @param accountOwner 검색할 계좌의 예금주명
     * @return 검색된 계좌
     */
    public Account[] searchAccountByOwner(String accountOwner) {
        Account[] searchLists = null;
        
        // 동일 예금주 확인
        int searchCount = getCountByOwner(accountOwner);
        if(searchCount == 0) {
            return null;
        }
        
        searchLists = new Account[searchCount];
        int ListIndex = 0;
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountOwner().equals(accountOwner)) {
                searchLists[ListIndex++] = accounts[i];
            }
        }
        return searchLists;
    }
    
    // 헬퍼 메소드
    private int getCountByOwner(String accountOwner) {
        int searchCount = 0;
        for (int i = 0; i < count; i++) {
            String name = accounts[i].getAccountOwner();
            // 동일 예금주 여부 확인
            if(name.equals(accountOwner)) {
                searchCount++;
            }
        }
        return searchCount;
    }


    /**
     * 계좌를 삭제하는 기능
     * @param accountNum 삭제할 계좌의 계좌번호
     * @return 계좌 삭제 성공 여부
     */
    public boolean removeAccount(String accountNum) {
        for (int i = 0; i < count; i++) {
            String number = accounts[i].getAccountNum();
            if (number.equals(accountNum)) {
                for (int j = i; j < count - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                count--;
                return true;
            }
        }
        return false;
    }

}
