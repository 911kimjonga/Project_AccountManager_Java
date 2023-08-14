package com.jw.amapp.gui;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;

import com.jw.amapp.app.AMS;
import com.jw.amapp.domain.Account;
import com.jw.amapp.domain.MinusAccount;
import com.jw.amapp.exception.AccountException;
import com.jw.amapp.util.AccountType;
import com.jw.amapp.util.AccountValidator;
import com.jw.amapp.util.ActionType;

/**
 * AMS 시스템 GUI 환경 구축
 * 
 * @author 김종원
 *
 */

public class AMSFrame extends Frame {

    // 필드
    Label accountKindL, accountNumL, accountOwnerL, pwdL, accountListL, unitWonL, depositL, borrowMoneyL, actionTypeL;
    Button checkB, deleteB, searchB, registB, totalViewB;
    TextField inputAccountNumTF, inputAccountOwnerTF, inputPwdTF, inputDepositTF, inputBorrowMoneyTF;
    Choice choiceAccountTypeC, choiceActionTypeC;
    TextArea accountListTA;

    GridBagLayout gridBagLayout;
    GridBagConstraints gridBagConstraints;

    // 생성자
    public AMSFrame() {
        this("기본 타이틀");
    }

    public AMSFrame(String title) {
        super(title);

        // Label 영역
        accountKindL = new Label("계좌종류", Label.CENTER);
        accountNumL = new Label("계좌번호", Label.CENTER);
        accountOwnerL = new Label("예금주명", Label.CENTER);
        pwdL = new Label("비밀번호", Label.CENTER);
        depositL = new Label("입금금액", Label.CENTER);
        borrowMoneyL = new Label("대출한도", Label.CENTER);
        accountListL = new Label("계좌목록", Label.CENTER);
        unitWonL = new Label("(단위 : 원)", Label.CENTER);
        actionTypeL = new Label("기능종류", Label.CENTER);

        // Choice 영역
        choiceAccountTypeC = new Choice();
        // 유연한 사용, 수정을 위해 Enum 사용
        AccountType[] accountTypes = AccountType.values();
        for (AccountType accountType : accountTypes) {
            choiceAccountTypeC.add(accountType.getName());
        }
        choiceActionTypeC = new Choice();
        ActionType[] actionTypes = ActionType.values();
        for (ActionType actionType : actionTypes) {
            choiceActionTypeC.add(actionType.getName());
        }

        // TextField 영역
        inputAccountNumTF = new TextField();
        inputAccountOwnerTF = new TextField();
        inputPwdTF = new TextField();
        inputDepositTF = new TextField();
        inputBorrowMoneyTF = new TextField();

        // Button 영역
        checkB = new Button("조회");
        deleteB = new Button("삭제");
        searchB = new Button("검색");

        registB = new Button("신규등록");
        totalViewB = new Button("전체조회");

        // TextArea 영역
        accountListTA = new TextArea();

        // GridBag 생성
        gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();

    }

    /**
     * 컴포넌트 기본 속성
     * 
     * @param c 컴포넌트
     * @param x 컴포넌트 x 위치
     * @param y 컴포넌트 y 위치
     * @param w 컴포넌트 넓이
     * @param h 컴포넌트 높이
     */
    private void addComponent(Component c, int x, int y, int w, int h) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; // 채우기 스타일
        gridBagLayout.setConstraints(c, gridBagConstraints);
        add(c);
    }

    /**
     * 컴포넌트 추가 속성
     * 
     * @param c 컴포넌트
     * @param x 컴포넌트 x 위치
     * @param y 컴포넌트 y 위치
     * @param w 컴포넌트 넓이
     * @param h 컴포넌트 높이
     * @param weightX 컴포넌트 x 가중치
     * @param padX 컴포넌트 x 내부여백
     */
    private void addDiffComponent(Component c, int x, int y, int w, int h, double weightX, int padX) {
        gridBagConstraints.weightx = weightX;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.ipadx = padX;
        gridBagConstraints.ipady = 0;
        addComponent(c, x, y, w, h);
    }

    /**
     * 컴포넌트 배치 현황
     */
    public void init() {

        setLayout(gridBagLayout);

        addComponent(accountKindL, 0, 0, 1, 1);
        addComponent(accountNumL, 0, 1, 1, 1);
        addComponent(accountOwnerL, 0, 2, 1, 1);
        addComponent(pwdL, 0, 3, 1, 1);
        addComponent(borrowMoneyL, 0, 4, 1, 1);
        addComponent(accountListL, 0, 5, 1, 1);
        addComponent(unitWonL, 5, 5, 1, 1);
        addComponent(depositL, 3, 3, 1, 1);
        addComponent(actionTypeL, 3, 0, 1, 1);

        addDiffComponent(choiceAccountTypeC, 1, 0, 1, 1, 1.0, 30);
        addDiffComponent(choiceActionTypeC, 4, 0, 1, 1, 1.0, 30);

        addDiffComponent(inputAccountNumTF, 1, 1, 2, 1, 0.0, 50);
        addDiffComponent(inputAccountOwnerTF, 1, 2, 2, 1, 0.0, 50);
        addDiffComponent(inputPwdTF, 1, 3, 2, 1, 0.0, 50);
        addDiffComponent(inputDepositTF, 4, 3, 2, 1, 1.0, 50);
        addDiffComponent(inputBorrowMoneyTF, 1, 4, 2, 1, 1.0, 0);

        addComponent(checkB, 3, 1, 1, 1);
        addComponent(deleteB, 4, 1, 1, 1);
        addComponent(searchB, 3, 2, 1, 1);

        addComponent(registB, 3, 4, 1, 1);
        addComponent(totalViewB, 4, 4, 1, 1);

        addComponent(accountListTA, 0, 6, 6, 5);

    }

    /**
     * 초이스 버튼에 따른 입력창 및 버튼 활성화 여부
     */

    // 모두 입력 불가
    public void allInputDisable() {
        inputAccountNumTF.setEnabled(false);
        inputAccountOwnerTF.setEnabled(false);
        inputDepositTF.setEnabled(false);
        inputPwdTF.setEnabled(false);
        inputBorrowMoneyTF.setEnabled(false);
        choiceActionTypeC.setEnabled(false);
        checkB.setEnabled(false);
        deleteB.setEnabled(false);
        searchB.setEnabled(false);
        registB.setEnabled(false);
        textFieldReset();
    }

    // 타입 선택
    public void typeInputDisable() {
        inputAccountNumTF.setEnabled(false);
        inputAccountOwnerTF.setEnabled(false);
        inputDepositTF.setEnabled(false);
        inputPwdTF.setEnabled(false);
        inputBorrowMoneyTF.setEnabled(false);
        choiceActionTypeC.setEnabled(true);
        checkB.setEnabled(false);
        deleteB.setEnabled(false);
        searchB.setEnabled(false);
        registB.setEnabled(false);
        textFieldReset();
    }

    // 입출금계좌 등록 입력 상태
    public void generalInputEnable() {
        inputAccountNumTF.setEnabled(false);
        inputAccountOwnerTF.setEnabled(true);
        inputDepositTF.setEnabled(true);
        inputPwdTF.setEnabled(true);
        inputBorrowMoneyTF.setEnabled(false);
        choiceActionTypeC.setEnabled(true);
        checkB.setEnabled(false);
        deleteB.setEnabled(false);
        searchB.setEnabled(false);
        registB.setEnabled(true);
        textFieldReset();
    }

    // 마이너스계좌 등록 입력 상태
    public void minusInputEnable() {
        inputAccountNumTF.setEnabled(false);
        inputAccountOwnerTF.setEnabled(true);
        inputDepositTF.setEnabled(true);
        inputPwdTF.setEnabled(true);
        inputBorrowMoneyTF.setEnabled(true);
        choiceActionTypeC.setEnabled(true);
        checkB.setEnabled(false);
        deleteB.setEnabled(false);
        searchB.setEnabled(false);
        registB.setEnabled(true);
        textFieldReset();
    }

    // 계좌 조회 (계좌번호)
    public void checkEnable() {
        inputAccountNumTF.setEnabled(true);
        inputAccountOwnerTF.setEnabled(false);
        inputDepositTF.setEnabled(false);
        inputPwdTF.setEnabled(false);
        inputBorrowMoneyTF.setEnabled(false);
        choiceActionTypeC.setEnabled(true);
        checkB.setEnabled(true);
        deleteB.setEnabled(false);
        searchB.setEnabled(false);
        registB.setEnabled(false);
        textFieldReset();
    }

    // 계좌 검색 (예금주명)
    public void searchEnable() {
        inputAccountNumTF.setEnabled(false);
        inputAccountOwnerTF.setEnabled(true);
        inputDepositTF.setEnabled(false);
        inputPwdTF.setEnabled(false);
        inputBorrowMoneyTF.setEnabled(false);
        choiceActionTypeC.setEnabled(true);
        checkB.setEnabled(false);
        deleteB.setEnabled(false);
        searchB.setEnabled(true);
        registB.setEnabled(false);
        textFieldReset();
    }

    // 계좌 삭제
    public void deleteEnable() {
        inputAccountNumTF.setEnabled(true);
        inputAccountOwnerTF.setEnabled(false);
        inputDepositTF.setEnabled(false);
        inputPwdTF.setEnabled(true);
        inputBorrowMoneyTF.setEnabled(false);
        choiceActionTypeC.setEnabled(true);
        checkB.setEnabled(false);
        deleteB.setEnabled(true);
        searchB.setEnabled(false);
        registB.setEnabled(false);
        textFieldReset();
    }

    /**
     * 모든 입력창 공란으로 리셋
     */
    public void textFieldReset() {
        inputAccountNumTF.setText("");
        inputAccountOwnerTF.setText("");
        inputDepositTF.setText("");
        inputPwdTF.setText("");
        inputBorrowMoneyTF.setText("");
    }

    /**
     * 계좌 종류별 선택창 및 버튼 활성화 여부 (등록 기능 만)
     */
    public void choiceAccountAddEnable() {
        if (choiceAccountTypeC.getSelectedItem().equals("전체")) {
            typeInputDisable();
        } else if (choiceAccountTypeC.getSelectedItem().equals("입출금계좌")) {
            generalInputEnable();
        } else if (choiceAccountTypeC.getSelectedItem().equals("마이너스계좌")) {
            minusInputEnable();
        }
    }

    /**
     * 선택 기능별 선택창 및 버튼 활성화 여부 (등록 기능 제외)
     */
    public void choiceActionTypeEnable() {
        if (choiceActionTypeC.getSelectedItem().equals("기능선택")) {
            typeInputDisable();
        } else if (choiceActionTypeC.getSelectedItem().equals("조회")) {
            checkEnable();
        } else if (choiceActionTypeC.getSelectedItem().equals("검색")) {
            searchEnable();
        } else if (choiceActionTypeC.getSelectedItem().equals("삭제")) {
            deleteEnable();
        }
    }

    /**
     * 계좌종류 초이스 선택시 입력창 및 버튼 활성화 여부
     * 
     * @param accountType 선택된 계좌 종류
     */
    public void selectAccountType(AccountType accountType) {
        switch (accountType) {
        case ACCOUNT_ALL:
            allInputDisable();
            choiceActionTypeC.select("기능선택");
            break;
        case ACCOUNT_GENERAL:
            if (choiceActionTypeC.getSelectedItem().equals("등록")) {
                generalInputEnable();
            }
            choiceActionTypeEnable();
            break;
        case ACCOUNT_MINUS:
            if (choiceActionTypeC.getSelectedItem().equals("등록")) {
                minusInputEnable();
            }
            choiceActionTypeEnable();
            break;
        }
    }

    /**
     * 기능별 초이스 선택시 입력창 및 버튼 활성화 여부
     * 
     * @param actionType 선택된 기능
     */
    public void selectActionType(ActionType actionType) {
        switch (actionType) {
        case ACTION_ALL:
            typeInputDisable();
            break;
        case ACCOUNT_ADD:
            choiceAccountAddEnable();
            break;
        case ACCOUNT_CHECK:
            checkEnable();
            break;
        case ACCOUNT_SEARCH:
            searchEnable();
            break;
        case ACCOUNT_DELETE:
            deleteEnable();
            break;
        }
    }

    /**
     * 계좌 조회 헤드라인
     */
    private void printHeadLine() {
        accountListTA.append("--------------------------------------------------------------------------\n");
        String Headline = String.format(" %1$-10s | %2$-6s | %3$-7s | %4$-4s | %5$-11s | %6$-10s \n", "계좌종류", "계좌번호",
                "이름", "비밀번호", "잔액", "대출한도");
        accountListTA.append(Headline);
        accountListTA.append("==========================================================================\n");
    }

    /**
     * 전체 계좌 출력
     */
    public void allList() {
        accountListTA.setText("");
        List<Account> list = AMS.repository.getAccounts();
        printHeadLine();
        for (Account account : list) {
            accountListTA.append(account.toString() + "\n");
        }
    }

    /**
     * 계좌 등록
     */
    public void createAccount() {

        Account account = null;

        String accountOwner = inputAccountOwnerTF.getText();
        String stringPasswd = inputPwdTF.getText();
        String stringInputDoposit = inputDepositTF.getText();
        String stringInputBorrow = inputBorrowMoneyTF.getText();

        String selectedItem = choiceAccountTypeC.getSelectedItem();

        int check = 0;

        // 이름 입력 유효성 검사
        if (AccountValidator.hasText(accountOwner) == false) {
            check += 1;
            inputAccountOwnerTF.setText("이름을 입력하시오.");
        } else if (AccountValidator.isName(accountOwner) == false) {
            check += 1;
            inputAccountOwnerTF.setText("한글명을 입력하시오.");
        }

        // 패스워드 입력 유효성 검사
        if (AccountValidator.hasText(stringPasswd) == false) {
            check += 1;
            inputPwdTF.setText("패스워드를 입력하시오.");
        } else if (AccountValidator.isNumber(stringPasswd) == false) {
            check += 1;
            inputPwdTF.setText("네자리 숫자를 입력하시오.");
        } else if (AccountValidator.isPasswd(stringPasswd) == false) {
            check += 1;
            inputPwdTF.setText("숫자를 네자리만 입력하시오.");
        }

        // 입금 금액 입력 유효성 검사
        if (AccountValidator.hasText(stringInputDoposit) == false) {
            stringInputDoposit = "0";
            inputDepositTF.setText("0");
        } else if (AccountValidator.isNumber(stringInputDoposit) == false) {
            check += 1;
            inputDepositTF.setText("숫자를 입력하시오.");
        }

        // 대출 금액 입력 유효성 검사
        if (AccountValidator.hasText(stringInputBorrow) == false) {
            if (selectedItem.equals(AccountType.ACCOUNT_MINUS.getName())) {
                stringInputBorrow = "0";
                inputBorrowMoneyTF.setText("0");
            }
        } else if (AccountValidator.isNumber(stringInputBorrow) == false) {
            check += 1;
            inputDepositTF.setText("숫자를 입력하시오.");
        }

        // 유효성 검사 통과시
        if (check == 0) {
            // 기능 실행
            accountListTA.setText(""); // TextArea 초기화

            int passwd = Integer.parseInt(stringPasswd);
            long inputDoposit = Long.parseLong(stringInputDoposit);

            // 입출금 계좌 개설
            if (selectedItem.equals(AccountType.ACCOUNT_GENERAL.getName())) {
                account = new Account(accountOwner, passwd, inputDoposit);

                // 마이너스 계좌 개설
            } else if (selectedItem.equals(AccountType.ACCOUNT_MINUS.getName())) {
                long inputBorrow = Long.parseLong(stringInputBorrow);
                account = new MinusAccount(accountOwner, passwd, inputDoposit, inputBorrow);
            }

            AMS.repository.createAccount(account);
            textFieldReset(); // TextField 초기화
            allList(); // 계좌 전체 조회
            JOptionPane.showMessageDialog(this, "정상 등록 처리되었습니다.");

        }
    }

    /**
     * 계좌 삭제
     *
     * @throws AccountException
     */
    public void removeAccount() throws AccountException, NullPointerException {

        String accountNum = inputAccountNumTF.getText();
        String stringPasswd = inputPwdTF.getText();
        int check = 0;

        // 계좌번호 입력 유효성 검사
        if (AccountValidator.hasText(accountNum) == false) {
            check += 1;
            inputAccountNumTF.setText("계좌번호를 입력하시오.");
        } else if (AccountValidator.isNumber(accountNum) == false) {
            check += 1;
            inputAccountNumTF.setText("숫자를 입력하시오.");
        }

        // 패스워드 입력 유효성 검사
        if (AccountValidator.hasText(stringPasswd) == false) {
            check += 1;
            inputPwdTF.setText("패스워드를 입력하시오.");
        } else if (AccountValidator.isNumber(stringPasswd) == false) {
            check += 1;
            inputPwdTF.setText("네자리 숫자를 입력하시오.");
        } else if (AccountValidator.isPasswd(stringPasswd) == false) {
            check += 1;
            inputPwdTF.setText("숫자를 네자리만 입력하시오.");
        }

        // 유효성 검사 통과 시
        if (check == 0) {
            // 기능 실행
            accountListTA.setText(""); // TextArea 초기화
            textFieldReset(); // TextField 초기화

            int passwd = Integer.parseInt(stringPasswd);
            Account searchAccount = AMS.repository.searchAccount(accountNum);

            // 입출금계좌, 마이너스계좌 구분
            String selectedItem = choiceAccountTypeC.getSelectedItem();

            if (searchAccount != null) {
                // 입출금계좌 선택시 입출금계좌일 경우만 삭제 진행
                if (selectedItem.equals(AccountType.ACCOUNT_GENERAL.getName())) {
                    if (!(searchAccount instanceof MinusAccount)) {
                        AMS.repository.removeAccount(accountNum, passwd);
                        JOptionPane.showMessageDialog(this, accountNum + " 계좌 삭제 정상 처리되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(this, "입력하신 계좌번호에 해당하는 입출금계좌가 존재하지 않습니다.");
                    }

                // 입출금계좌 선택시 입출금계좌일 경우만 삭제 진행
                } else if (selectedItem.equals(AccountType.ACCOUNT_MINUS.getName())) {
                    if (searchAccount instanceof MinusAccount) {
                        AMS.repository.removeAccount(accountNum, passwd);
                        JOptionPane.showMessageDialog(this, accountNum + " 계좌 삭제 정상 처리되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(this, "입입력하신 계좌번호에 해당하는 마이너스계좌가 존재하지 않습니다.");
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        printHeadLine();
        allList(); // 삭제 후 전체 계좌 출력
    }

    /**
     * 계좌번호로 계좌 검색
     * 
     * @throws AccountException
     */
    public void searchAccount() throws AccountException, NullPointerException {
        
        String accountNum = inputAccountNumTF.getText();
        int check = 0;

        // 계좌번호 입력 유효성 검사
        if (AccountValidator.hasText(accountNum) == false) {
            check += 1;
            inputAccountNumTF.setText("계좌번호를 입력하시오.");
        } else if (AccountValidator.isNumber(accountNum) == false) {
            check += 1;
            inputAccountNumTF.setText("숫자를 입력하시오.");
        }

        // 유효성 검사 통과시
        if (check == 0) {
            // 기능 실행
            Account searchAccount = AMS.repository.searchAccount(accountNum);

            accountListTA.setText(""); // TextArea 초기화
            textFieldReset(); // TextField 초기화
            printHeadLine();

            // 입출금계좌, 마이너스계좌 구분
            String selectedItem = choiceAccountTypeC.getSelectedItem();

            if (searchAccount != null) {
                // 입출금계좌 선택시 입출금계좌일 경우만 조회
                if (selectedItem.equals(AccountType.ACCOUNT_GENERAL.getName())) {
                    if (!(searchAccount instanceof MinusAccount)) {
                        accountListTA.append(searchAccount.toString() + "\n");
                        JOptionPane.showMessageDialog(this, accountNum + " 입출금계좌 검색 완료 되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(this, "입력하신 계좌번호에 해당하는 입출금계좌가 존재하지 않습니다.");
                        allList();
                    }

                // 마이너스계좌 선택시 마이너스계좌일 경우만 조회
                } else if (selectedItem.equals(AccountType.ACCOUNT_MINUS.getName())) {
                    if (searchAccount instanceof MinusAccount) {
                        accountListTA.append(searchAccount.toString() + "\n");
                        JOptionPane.showMessageDialog(this, accountNum + " 마이너스계좌 검색 완료 되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(this, "입력하신 계좌번호에 해당하는 마이너스계좌가 존재하지 않습니다.");
                        allList();
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }
    }

    /**
     * 예금주명으로 계좌 검색
     * 
     * @throws AccountException
     */
    public void searchAccountByOwner() throws AccountException, NullPointerException {

        String accountOwner = inputAccountOwnerTF.getText();
        int check = 0;
        boolean hasAccounts = false;

        // 이름 입력 유효성 검사
        if (AccountValidator.hasText(accountOwner) == false) {
            check += 1;
            inputAccountOwnerTF.setText("이름을 입력하시오.");
        } else if (AccountValidator.isName(accountOwner) == false) {
            check += 1;
            inputAccountOwnerTF.setText("한글명을 입력하시오.");
        }

        // 유효성 검사 통과시
        if (check == 0) {
            // 기능 실행
            List<Account> searchAccounts = AMS.repository.searchAccountByOwner(accountOwner);

            accountListTA.setText(""); // TextArea 초기화
            textFieldReset(); // TextField 초기화
            printHeadLine();

            // 입출금계좌, 마이너스계좌 구분
            String selectedItem = choiceAccountTypeC.getSelectedItem();

            // 입출금계좌 선택시 입출금계좌일 경우만 조회
            if (selectedItem.equals(AccountType.ACCOUNT_GENERAL.getName())) {
                for (Account account : searchAccounts) {
                    if (account instanceof Account && !(account instanceof MinusAccount)) {
                        accountListTA.append(account.toString() + "\n");
                        hasAccounts = true;
                    }
                }

                // 조회 유무에 따른 메시지창 출력
                if (hasAccounts) {
                    JOptionPane.showMessageDialog(this, accountOwner + "님의 입출금계좌 검색 완료 되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(this, "입력하신 예금주명에 해당하는 입출금계좌가 존재하지 않습니다.");
                    allList();
                }

            // 마이너스계좌 선택시 마이너스계좌일 경우만 조회
            } else if (selectedItem.equals(AccountType.ACCOUNT_MINUS.getName())) {
                for (Account account : searchAccounts) {
                    if (account instanceof MinusAccount) {
                        accountListTA.append(account.toString() + "\n");
                        hasAccounts = true;
                    }
                }

                // 조회 유무에 따른 메시지창 출력
                if (hasAccounts) {
                    JOptionPane.showMessageDialog(this, accountOwner + "님의 마이너스계좌 검색 완료 되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(this, "입력하신 예금주명에 해당하는 마이너스계좌가 존재하지 않습니다.");
                    allList();
                }
            }
        }
    }


    /**
     * 종료 메소드
     */
    private void exit() {
        setVisible(false);
        dispose();
        System.exit(0);
    }

    /**
     * 이벤트 처리 메소드
     */
    public void addEventListener() {

        // 액션이벤트 내부클래스
        class ActionHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object eventSource = e.getSource();
                
                // 조회 버튼 이벤트
                if (eventSource == checkB) {
                    try {
                        searchAccount();
                    } catch (AccountException e1) {
                        JOptionPane.showMessageDialog(checkB, "해당 계좌번호와 일치하는 계좌가 존재하지 않습니다.");
                        allList();
                        textFieldReset();
                    } catch (NullPointerException e1) {
                        JOptionPane.showMessageDialog(checkB, "해당 계좌번호와 일치하는 계좌가 존재하지 않습니다.");
                        allList();
                        textFieldReset();
                    }
                    
                // 검색 버튼 이벤트
                } else if (eventSource == searchB) {
                    try {
                        searchAccountByOwner();
                    } catch (AccountException e1) {
                        JOptionPane.showMessageDialog(checkB, "해당 계좌번호와 일치하는 계좌가 존재하지 않습니다.");
                        allList();
                        textFieldReset();
                    } catch (NullPointerException e1) {
                        JOptionPane.showMessageDialog(checkB, "해당 계좌번호와 일치하는 계좌가 존재하지 않습니다.");
                        allList();
                        textFieldReset();
                    }
                    
                // 삭제 버튼 이벤트
                } else if (eventSource == deleteB) {
                    try {
                        removeAccount();
                    } catch (AccountException e1) {
                        JOptionPane.showMessageDialog(deleteB, "해당 계좌번호와 일치하는 계좌가 존재하지 않거나, 비밀번호가 일치 하지않습니다.");
                        allList();
                        textFieldReset();
                    } catch (NullPointerException e1) {
                        JOptionPane.showMessageDialog(checkB, "해당 계좌번호와 일치하는 계좌가 존재하지 않습니다.");
                        allList();
                        textFieldReset();
                    }
                    
                // 계좌 등록 버튼 이벤트
                } else if (eventSource == registB) {
                    createAccount();
                    
                // 전체 조회 버튼 이벤트
                } else if (eventSource == totalViewB) {
                    allList();
                }
            }
        }
        ActionListener actionHandler = new ActionHandler();

        // 종료 처리
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        // 창이 열릴때
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                allInputDisable();
                allList();
            }
        });

        // 계좌등록
        registB.addActionListener(actionHandler);

        // 전체계좌 조회
        totalViewB.addActionListener(actionHandler);

        // 계좌번호 조회
        checkB.addActionListener(actionHandler);

        // 예금주명 조회
        searchB.addActionListener(actionHandler);

        // 계좌 삭제
        deleteB.addActionListener(actionHandler);

        // 계좌 종류 초이스 선택
        choiceAccountTypeC.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (choiceAccountTypeC.getSelectedItem().equals("전체")) {
                        selectAccountType(AccountType.ACCOUNT_ALL);
                    } else if (choiceAccountTypeC.getSelectedItem().equals("입출금계좌")) {
                        selectAccountType(AccountType.ACCOUNT_GENERAL);
                    } else if (choiceAccountTypeC.getSelectedItem().equals("마이너스계좌")) {
                        selectAccountType(AccountType.ACCOUNT_MINUS);
                    }
                }
            }
        });
        
        // 기능 종류 초이스 선택
        choiceActionTypeC.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (choiceActionTypeC.getSelectedItem().equals("기능선택")) {
                        selectActionType(ActionType.ACTION_ALL);
                    } else if (choiceActionTypeC.getSelectedItem().equals("등록")) {
                        selectActionType(ActionType.ACCOUNT_ADD);
                    } else if (choiceActionTypeC.getSelectedItem().equals("조회")) {
                        selectActionType(ActionType.ACCOUNT_CHECK);
                    } else if (choiceActionTypeC.getSelectedItem().equals("검색")) {
                        selectActionType(ActionType.ACCOUNT_SEARCH);
                    } else if (choiceActionTypeC.getSelectedItem().equals("삭제")) {
                        selectActionType(ActionType.ACCOUNT_DELETE);
                    }
                }
            }
        });

    }
}