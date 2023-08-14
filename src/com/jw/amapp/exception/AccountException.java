package com.jw.amapp.exception;

/**
 * 계좌 관리를 위한 사용자 정의 예외 클래스
 *
 * @author 김종원
 */
public class AccountException extends Exception {

    private int errorCode;
    
    public AccountException() {
        super("예기치 않은 오류가 발생하였습니다. 관리자에게 문의하세요.");
    }
    
    public AccountException(String Message) {
        super(Message);
    }
    
    public AccountException(String Message, int errorCode) {
        super(Message);
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    
    @Override
    public String toString() {
        return getMessage() + " [에러코드 : " + errorCode + "]";
    }

}
