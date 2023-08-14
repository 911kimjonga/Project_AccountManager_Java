package com.jw.amapp.domain;

import com.jw.amapp.exception.AccountException;

/**
 * 은행 기능 구현체
 *
 * @author 김종원
 */
public class AccountUtil {
    
    /**
     * 입금 기능
     * @param account 입급할 계좌
     * @param money 입금할 금액
     * @return 입금 후 잔액
     * @throws AccountException
     */
    public long deposit(Account account, long money) throws AccountException {
        // 데이터 검증 및 예외처리
        if (money <= 0) {
            throw new AccountException("입금금액은 0이거나 음수일 수 없습니다.");
        } else if (money >= 100000000) {
            throw new AccountException("1억이상 입금할 수 없습니다.");
        }
        
        account.setRestMoney(account.getRestMoney() + money);
        return account.getRestMoney();
    }

    /**
     * 출금 기능
     * @param account 출금할 계좌
     * @param money 출금할 금액
     * @return 출금 후 잔액
     * @throws AccountException
     */
    public long withdraw(Account account, long money) throws AccountException {
        if (money >= 100000000) {
            throw new AccountException("출금금액은 1억이상 일 수 없습니다.");
        } else if (money <= 0) {
            throw new AccountException("출금금액은 0이거나 음수일 수 없습니다.");
        } else {
            if (account instanceof MinusAccount) {
                if ((account.getRestMoney() - money) < (((MinusAccount)account).getBorrowLimit() * (-1))) {
                    throw new AccountException("대출 한도를 초과하였습니다.");
                }
            } else {
                if (money > account.getRestMoney()) {
                    throw new AccountException("잔액이 부족합니다.");
                }
            }
        }
        
        account.setRestMoney(account.getRestMoney() - money);
        return account.getRestMoney();
    }

    /**
     * 비밀번호 확인 기능
     * @param account 비밀번호 확인할 계좌
     * @param pwd 입력된 비밀번호
     * @return 비밀번호 일치 여부
     * @throws AccountException
     */
    public boolean checkPasswd(Account account, int pwd) throws AccountException {
        if (account.getPasswd() != pwd) {
            throw new AccountException("비밀번호가 틀렸습니다. 다시 시도해 주세요.");
        }
        return account.getPasswd() == pwd;
    }
    
}
