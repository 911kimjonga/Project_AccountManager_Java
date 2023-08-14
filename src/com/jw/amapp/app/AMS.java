package com.jw.amapp.app;

import com.jw.amapp.domain.Account;
import com.jw.amapp.domain.MinusAccount;
import com.jw.amapp.domain.AccountRepository;
import com.jw.amapp.domain.ListAccountRepository;

import com.jw.amapp.gui.AMSFrame;


/**
 * AMS 시스템 실행을 위한 Main 클래스
 * @author 김종원
 *
 */
public class AMS {

    public static AccountRepository repository = new ListAccountRepository();

    public static void main(String[] args) {
        AMSFrame frame = new AMSFrame("AMS");
        frame.init();
        frame.addEventListener();
        frame.setSize(500, 450);
        frame.setResizable(false);
//        frame.pack();
        frame.setVisible(true);
        
        // 가상데이터 임시 등록
        repository.createAccount(new Account("홍길동", 1234, 500000)); //1000
        repository.createAccount(new Account("강길동", 2345, 100000));
        repository.createAccount(new MinusAccount("이길동", 3456, 10000, 10000000));
        repository.createAccount(new MinusAccount("박길동", 4567, 50000, 10000000));
    }
    
}
