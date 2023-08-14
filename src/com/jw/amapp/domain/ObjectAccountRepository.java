package com.jw.amapp.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 파일 입출력 (ObjectIOStream)을 이용한 은행계좌 목록 저장 및 관리 (생성, 검색, 삭제) 구현체
 * 
 * @author 김종원
 * @since  4.0
 *
 */
public class ObjectAccountRepository implements AccountRepository {

    // 필드
    private static final String FILE_PATH = "account.dat";

    private List<Account> accounts;
    
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // 생성자
    /**
     * 계좌파일 내의 정보를 읽어들이는 기능
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ObjectAccountRepository() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            accounts = new LinkedList<Account>();
        } else {
            in = new ObjectInputStream(new FileInputStream(file));
            accounts = (List<Account>) in.readObject();
            in.close();
        }
    }

    /**
     * 계좌를 파일에 저장하는 기능
     * @throws IOException
     */
    public void saveFile() throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        out.writeObject(accounts);
        out.flush();
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

        String lastAccountNum = getLastAccountNumber();
        long newAccountNum = Long.parseLong(lastAccountNum) + 1;
        account.setAccountNum(String.valueOf(newAccountNum));

        accounts.add(account);

        // 계좌목록에 계좌 저장
        try {
            saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    // 마지막으로 등록된 계좌의 계좌번호 반환하는 기능
    private String getLastAccountNumber() {
        if (accounts.isEmpty()) {
            return String.valueOf(Account.getAccountId());
        } else {
            Account lastAccount = accounts.get(accounts.size() - 1);
            return lastAccount.getAccountNum();
        }
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
     * @param accountNum 삭제할 계좌 계좌번호
     * @param passwd 삭제할 계좌 비밀번호
     * @return 계좌 삭제 성공 여부
     */
    public boolean removeAccount(String accountNum, int passwd) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNum().equals(accountNum)) {
                if (account.getPasswd() == passwd) {
                    accounts.remove(i);
                    try {
                        saveFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 리소스 반환 기능
     */
    public void close() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
