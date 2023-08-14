package com.jw.amapp.domain;

/**
 * 마이너스 계좌 추상화
 *
 * @author 김종원
 */
public class MinusAccount extends Account {

    // 필드
    private long borrowLimit;

    private String accountType = "M";

    // 생성자
    public MinusAccount() {
        super();
    }

    public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowLimit) {
        super(accountNum, accountOwner, passwd, restMoney);
        this.borrowLimit = borrowLimit;
    }

    public MinusAccount(String accountOwner, int passwd, long restMoney, long borrowLimit) {
        super(accountOwner, passwd, restMoney);
        this.borrowLimit = borrowLimit;
    }

    // get/set 메소드
    public long getBorrowLimit() {
        return borrowLimit;
    }

    public void setBorrowLimit(long borrowLimit) {
        this.borrowLimit = borrowLimit;
    }

    // 기능 메소드
    /**
     * 마이너스계좌 정보 출력
     * @return 마이너스계좌 정보
     */
    @Override
    public String toString() {
        return String.format(" %1$6s | %2$12s | %3$5s | %4$4s | %5$,12d | %6$,12d ", "마이너스계좌", super.getAccountNum(), super.getAccountOwner(), "　****　", super.getRestMoney(), borrowLimit);
    }

}