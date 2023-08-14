package com.jw.amapp.domain;

import java.io.Serializable;

/**
 * 은행 계좌 추상화
 *
 * @author 김종원
 */
public class Account implements Serializable {

    // 필드
    private String accountNum;
    private String accountOwner;
    private int passwd;
    private long restMoney;

    public static final String BANK_NAME;
    private static long accountId;

    static {
        BANK_NAME = "가나다라은행";
        accountId = 10000;
    }

    // 생성자
    public Account() {
    }

    public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
        this.accountNum = accountNum;
        this.accountOwner = accountOwner;
        this.passwd = passwd;
        this.restMoney = restMoney;
    }

    public Account(String accountOwner, int passwd, long restMoney) {
        this.accountNum = String.valueOf(accountId++);
        this.accountOwner = accountOwner;
        this.passwd = passwd;
        this.restMoney = restMoney;
    }

    // get/set 메소드
    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public int getPasswd() {
        return passwd;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }
    
    public static String getBankName() {
        return BANK_NAME;
    }

    public static long getAccountId() {
        return accountId;
    }

    /**
     * 잔액 조회 기능
     * @return 현재 잔액
     */
    public long getRestMoney() {
        return restMoney;
    }

    public void setRestMoney(long restMoney) {
        this.restMoney = restMoney;
    }

    // 기능 메소드
    /**
     * 입출금계좌 정보 출력
     * @return 입출금계좌 정보
     */
    @Override
    public String toString() {
        return String.format(" %1$6s | %2$12s | %3$5s | %4$4s | %5$,12d |", "　입출금계좌", accountNum, accountOwner, "　****　", restMoney);
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }

}
