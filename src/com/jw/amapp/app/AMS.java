package com.jw.amapp.app;

import java.util.Scanner;

import com.jw.amapp.domain.Account;
import com.jw.amapp.domain.MinusAccount;
import com.jw.amapp.domain.AccountUtil;
import com.jw.amapp.domain.AccountRepository;
import com.jw.amapp.domain.MemoryAccountRepository;
import com.jw.amapp.exception.AccountException;

/**
 * AMS 시스템 실행을 위한 Main 클래스
 *
 * @author 김종원
 * @since  1.0
 */
public class AMS {

    private static AccountRepository repository = new MemoryAccountRepository();
    private static AccountUtil accountUtil = new AccountUtil();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("*****************************************");
        System.out.println("** " + Account.BANK_NAME + "은행 계좌 관리 애플리케이션 **");
        System.out.println("*****************************************");

        // 가상데이터 임시 등록
        repository.createAccount(new Account("홍길동", 1234, 500000));
        repository.createAccount(new Account("강길동", 2345, 100000));
        repository.createAccount(new MinusAccount("이길동", 3456, 10000, 10000000));
        repository.createAccount(new MinusAccount("박길동", 4567, 50000, 10000000));

        boolean run = true;

        while (run) {
            System.out.println("----------------------------------------------------");
            System.out.println(" 1.계좌생성 | 2.계좌목록 | 3.입금 | 4.출금 | 5.종료 ");
            System.out.println("----------------------------------------------------");
            System.out.print("선택> ");
            int selectNo = Integer.parseInt(scanner.nextLine());

            if (selectNo == 1) {
                // 계좌 생성 및 등록
                createAccount();
            } else if (selectNo == 2) {
                // 계좌목록
                listAccounts();
            } else if (selectNo == 3) {
                // 입금
                depositAccount();
            } else if (selectNo == 4) {
                // 출금
                withdrawAccount();
            } else if (selectNo == 5) {
                run = false;
            } else {
                System.err.println("메뉴를 다시 골라주세요.");
            }
        }
        scanner.close();
        System.out.println("계좌관리 애플리케이션을 종료합니다.");
    }
    
    /**
     * 계좌 생성 기능 실행
     */
    private static void createAccount() {

        System.out.println("-------------------------------");
        System.out.println(" 1.입출금계좌 | 2.마이너스계좌 ");
        System.out.println("-------------------------------");
        
        System.out.print("계좌종류: ");
        String kind = scanner.nextLine();

        if (kind.equals("입출금계좌") || kind.equals("1")) {

            System.out.print("예금주명: ");
            String owner = scanner.nextLine();

            System.out.print("비밀번호: ");
            int passwd = Integer.parseInt(scanner.nextLine());

            System.out.print("입금액: ");
            long inputMoney = Long.parseLong(scanner.nextLine());

            Account account = new Account(owner, passwd, inputMoney);

            // AccountRepository에 계좌등록
            repository.createAccount(account);
            menuHeadLine();
            System.out.println(account);
            System.out.println("---------------------------------------------------------");
            System.out.println("※ 계좌 정상 등록 처리되었습니다.");

        } else if (kind.equals("마이너스계좌") || kind.equals("2")) {

            System.out.print("예금주명: ");
            String owner = scanner.nextLine();

            System.out.print("비밀번호: ");
            int passwd = Integer.parseInt(scanner.nextLine());

            System.out.print("입금액: ");
            long inputMoney = Long.parseLong(scanner.nextLine());

            System.out.print("대출한도: ");
            long inputBorrowMoney = Long.parseLong(scanner.nextLine());

            MinusAccount account = new MinusAccount(owner, passwd, inputMoney, inputBorrowMoney);

            // AccountRepository에 계좌등록
            repository.createAccount(account);
            menuHeadLine();
            System.out.println(account);
            System.out.println("---------------------------------------------------------");
            System.out.println("※ 계좌 정상 등록 처리되었습니다.");

        } else {
            System.err.println("해당 종류의 계좌는 존재하지 않습니다.");
        }
    }

    /**
     * 계좌 조회 기능 실행
     */
    private static void listAccounts() {
        Account[] list = repository.getAccounts();
        if (list != null) {
            menuHeadLine();
            for (int i = 0; i < repository.getCount(); i++) {
                System.out.println(list[i]);
            }
            System.out.println("---------------------------------------------------------");
        } else {
            System.out.println("생성된 계좌가 없습니다.");
        }
    }

    /**
     * 입금 기능 실행
     */
    private static void depositAccount() {
        // 계좌번호 확인
        System.out.print("계좌번호: ");
        String number = scanner.nextLine();
        Account searchAccount = repository.searchAccount(number);
        if (searchAccount != null) {
            menuHeadLine();
            System.out.println(searchAccount);
            System.out.println("---------------------------------------------------------");

            // 입금액 확인
            System.out.print("입금액: ");
            long inputMoney = Long.parseLong(scanner.nextLine());
            try {
                accountUtil.deposit(searchAccount, inputMoney);
                menuHeadLine();
                System.out.println(searchAccount);
                System.out.println("---------------------------------------------------------");
            } catch (AccountException e) {
                System.err.println("입력하신 입금액이 올바르지 않습니다.");
            }
        } else {
            System.err.println("계좌번호에 해당하는 계좌가 존재하지 않습니다.");
        }
    }

    /**
     * 출금 기능 실행
     */
    private static void withdrawAccount() {
        // 계좌번호 확인
        System.out.print("계좌번호: ");
        String number = scanner.nextLine();

        Account searchAccount = repository.searchAccount(number);
        if (searchAccount != null) {
            // 패스워드 확인
            System.out.print("비밀번호: ");
            int passwd = Integer.parseInt(scanner.nextLine());
            try {
                accountUtil.checkPasswd(searchAccount, passwd);
                menuHeadLine();
                System.out.println(searchAccount);
                System.out.println("---------------------------------------------------------");
                // 출금 확인
                System.out.print("출금액: ");
                long inputMoney = Long.parseLong(scanner.nextLine());
                try {
                    accountUtil.withdraw(searchAccount, inputMoney);
                    menuHeadLine();
                    System.out.println(searchAccount);
                    System.out.println("---------------------------------------------------------");
                } catch (AccountException e) {
                    System.err.println("입력하신 출금액이 올바르지 않습니다.");
                }
            } catch (AccountException e) {
                System.err.println("비밀번호가 틀렸습니다. 다시 시도해 주세요.");
            }
        } else {
            System.out.println("계좌번호에 해당하는 계좌가 존재하지 않습니다.");
        }

    }

    /**
     * 메뉴 헤드라인
     */
    private static void menuHeadLine() {
        System.out.println("---------------------------------------------------------");
        System.out.printf(" %1$-6s | %2$-8s | %3$-4s | %4$-4s | %5$-11s | %6$-10s \n", "　　계좌종류", "　　　계좌번호", "예금주명", "비밀번호", "잔액", "대출한도");
        System.out.println("---------------------------------------------------------");
    }

}
