# Project_AccountManager_Java

## 개요
* 계좌 관리 어플리케이션(Account Manager Application)

---
## 주요 기능
* 신규 계좌 등록 (입출금 전용 계좌 및 마이너스 계좌)
* 전체 계좌 정보 조회
* 입금
* 출금

---
## 제작 및 실행 환경
* JDK : Java SE 11
* IDE : Eclipse 및 IntelliJ IDEA

---
## 실행법
* 소스폴더(src 폴더) 내의 amapp.app 패키지 내의 AMS.java 실행

---
## 실행 시 주의사항
* 이클립스 : 프로젝트 실행환경 -> VM arguments 에 -Dfile.encoding=MS949 입력 -> 적용
* 인텔리제이 : 프로젝트 실행 구성 -> 어플리케이션 구성 추가 -> 실행 옵션에서 VM 옵션 추가 -> VM 옵션 란에 -Dfile.encoding=MS949 입력 -> 적용

---
## 소스 내 패키지
* amapp.app : 실행을 위한 메인 클래스
* amapp.domain : 프로그램 주요 기능
* amapp.exception : 프로그램 작동시 예외 처리용 클래스

---
## 기능 및 설명
* 신규 계좌 생성
  * 계좌 종류 선택 > 예금주명 입력 > 비밀번호 입력 > 초기 입금액 입력 > 완료
* 계좌 목록 조회
  * 현재 등록 된 전체 계좌 목록 조회
* 입금
  * 계좌번호 입력 > 입금액 입력 > 완료
* 출금
  * 계좌번호 입력 > 비밀번호 입력 > 출금액 입력 > 완료
* 어플리케이션 종료
  * 선택 시 어플리케이션 종료

---
## 업데이트 내역

### v2.0
* Array 구조를 Collection 구조로 변경 (List 사용)

### v1.0
* CLI(Command Line Interface), 콘솔 환경에서만 실행 가능
* 컴퓨터 메모리 상에서만 일시적으로 정보를 생성 및 저장, 수정, 출력, 삭제가 가능
* 단순 배열(Array) 구조만 사용하여 제작
* 추후 업데이트 및 프로그램 확장성을 위해 Interface 사용
