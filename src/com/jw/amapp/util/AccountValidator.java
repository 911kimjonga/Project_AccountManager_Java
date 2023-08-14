package com.jw.amapp.util;

/**
 * 입력데이터 유효성 검증 공통 기능 정의 유틸리티
 * @author 김종원
 *
 */
public class AccountValidator {
    
    /**
     * 입력데이터가 공백만 있는지 여부 체크
     * @param input 입력문자열
     * @return 유효여부
     */
    public static boolean hasText(String input) {
        if (input != null && input.trim().length() != 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 숫자 입력 여부
     * @param number 입력문자열
     * @return 유효여부
     */
    public static boolean isNumber(String number) {
        return number.matches("\\d+");
    }
    
    /**
     * 이름 입력 여부 (2글자 이상 한글)
     * @param input 이름 입력문자열
     * @return 유효여부
     */
    public static boolean isName(String input) {
        return input.matches(".*[가-힣]{2,}.*");
    }
    
    /**
     * 패스워드 입력 여부(4자리 숫자)
     * @param number 패스워드 입력문자열
     * @return 유효여부
     */
    public static boolean isPasswd(String number) {
        return number.matches("\\d{4}");
    }
    
    /**
     * 입금, 출금 금액 범위 설정
     * @param number 입금 및 출금 금액
     * @return 유효여부
     */
    public static boolean moneyRange(String number) {
        try {
            long value = Long.parseLong(number);
            return value <= 100000000;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
