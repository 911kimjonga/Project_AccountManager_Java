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
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.jw.amapp.domain.Account;
import com.jw.amapp.domain.AccountRepository;
import com.jw.amapp.domain.ObjectAccountRepository;
import com.jw.amapp.domain.MinusAccount;
import com.jw.amapp.exception.AccountException;
import com.jw.amapp.util.AccountType;
import com.jw.amapp.util.AccountValidator;
import com.jw.amapp.util.ActionType;

/**
 * AMS 시스템 GUI 환경 구축
 * 
 * @author 김종원
 */

public class AMSFrame extends Frame {

    // 필드
    Label accountKindL, accountNumL, accountOwnerL, pwdL, accountListL, unitWonL, depositL, borrowMoneyL, actionTypeL;
    Button searchNumB, deleteB, searchOwnerB, registB, totalViewB;
    TextField inputAccountNumTF, inputAccountOwnerTF, inputPwdTF, inputDepositTF, inputBorrowMoneyTF;
    Choice choiceAccountTypeC, choiceActionTypeC;
    TextArea accountListTA;

    GridBagLayout gridBagLayout;
    GridBagConstraints gridBagConstraints;

    AccountRepository repository;
    
    // 생성자
    public AMSFrame() throws IOException, ClassNotFoundException {
        this("기본 타이틀");
    }

    public AMSFrame(String title) throws IOException, ClassNotFoundException {
        super(title);

        // Label 영역
        accountKindL = new Label("계좌종류", Label.CENTER);
        accountNumL = new Label("계좌번호", Label.CENTER);
        accountOwnerL = new Label("예금주명", Label.CENTER);
        pwdL = new Label("비밀번호", Label.CENTER);
        depositL = new Label("입금금액", Label.CENTER);
        borrowMoneyL = new Label("대출금액", Label.CENTER);
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
        searchNumB = new Button("조회");
        deleteB = new Button("삭제");
        searchOwnerB = new Button("검색");

        registB = new Button("신규등록");
        totalViewB = new Button("전체조회");

        // TextArea 영역
        accountListTA = new TextArea();

        // GridBag 생성
        gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();

        // AccountRepository 생성
        repository = new ObjectAccountRepository();

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
    private void addCom(Component c, int x, int y, int w, int h) {
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
    private void addComWP(Component c, int x, int y, int w, int h, double weightX, int padX) {
        gridBagConstraints.weightx = weightX;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.ipadx = padX;
        gridBagConstraints.ipady = 0;
        addCom(c, x, y, w, h);
    }

    /**
     * 컴포넌트 배치 현황
     */
    public void init() {

        setLayout(gridBagLayout);

        addCom(accountKindL, 0, 0, 1, 1);
        addCom(accountNumL, 0, 1, 1, 1);
        addCom(accountOwnerL, 0, 2, 1, 1);
        addCom(pwdL, 0, 3, 1, 1);
        addCom(borrowMoneyL, 0, 4, 1, 1);
        addCom(accountListL, 0, 5, 1, 1);
        addCom(unitWonL, 5, 5, 1, 1);
        addCom(depositL, 3, 3, 1, 1);
        addCom(actionTypeL, 3, 0, 1, 1);

        addComWP(choiceAccountTypeC, 1, 0, 1, 1, 1.0, 30);
        addComWP(choiceActionTypeC, 4, 0, 1, 1, 1.0, 30);

        addComWP(inputAccountNumTF, 1, 1, 2, 1, 0.0, 50);
        addComWP(inputAccountOwnerTF, 1, 2, 2, 1, 0.0, 50);
        addComWP(inputPwdTF, 1, 3, 2, 1, 0.0, 50);
        addComWP(inputDepositTF, 4, 3, 2, 1, 1.0, 50);
        addComWP(inputBorrowMoneyTF, 1, 4, 2, 1, 1.0, 0);

        addCom(searchNumB, 3, 1, 1, 1);
        addCom(deleteB, 4, 1, 1, 1);
        addCom(searchOwnerB, 3, 2, 1, 1);

        addCom(registB, 3, 4, 1, 1);
        addCom(totalViewB, 4, 4, 1, 1);

        addCom(accountListTA, 0, 6, 6, 5);
    }

    /**
     * 모든 입력창 공란으로 리셋하는 기능
     *
     */
    public void textFieldReset() {
        inputAccountNumTF.setText("");
        inputAccountOwnerTF.setText("");
        inputPwdTF.setText("");
        inputDepositTF.setText("");
        inputBorrowMoneyTF.setText("");
        
    }
    
    /**
     * 모든 입력창 및 버튼 사용 가능 여부
     * @param enable 활성화 여부
     */
    public void allInputEnable(boolean enable) {
        inputAccountNumTF.setEnabled(enable);
        inputAccountOwnerTF.setEnabled(enable);
        inputDepositTF.setEnabled(enable);
        inputPwdTF.setEnabled(enable);
        inputBorrowMoneyTF.setEnabled(enable);
        choiceActionTypeC.setEnabled(enable);
        searchNumB.setEnabled(enable);
        deleteB.setEnabled(enable);
        searchOwnerB.setEnabled(enable);
        registB.setEnabled(enable);
        textFieldReset();
    }

    /**
     * 기능 종류 선택 가능 여부
     * @param enable 활성화 여부
     */
    public void typeInputEnable(boolean enable) {
        choiceActionTypeC.setEnabled(enable);
    }

    /**
     * 입출금계좌 등록시
     * @param enable 활성화 여부
     */
    public void generalInputEnable(boolean enable) {
        inputAccountOwnerTF.setEnabled(enable);
        inputDepositTF.setEnabled(enable);
        inputPwdTF.setEnabled(enable);
        choiceActionTypeC.setEnabled(enable);
        registB.setEnabled(enable);
    }

    /**
     * 마이너스계좌 등록시
     * @param enable 활성화 여부
     */
    public void minusInputEnable(boolean enable) {
        inputAccountOwnerTF.setEnabled(enable);
        inputDepositTF.setEnabled(enable);
        inputPwdTF.setEnabled(enable);
        inputBorrowMoneyTF.setEnabled(enable);
        choiceActionTypeC.setEnabled(enable);
        registB.setEnabled(enable);
    }

    /**
     * 계좌번호로 계좌 조회시
     * @param enable 활성화 여부
     */
    public void checkEnable(boolean enable) {
        inputAccountNumTF.setEnabled(enable);
        choiceActionTypeC.setEnabled(enable);
        searchNumB.setEnabled(enable);
    }

    /**
     * 예금주 명으로 계좌 검색시
     * @param enable 활성화 여부
     */
    public void searchEnable(boolean enable) {
        inputAccountOwnerTF.setEnabled(enable);
        choiceActionTypeC.setEnabled(enable);
        searchOwnerB.setEnabled(enable);
    }

    /**
     * 계좌 삭제시
     * @param enable 활성화 여부
     */
    public void deleteEnable(boolean enable) {
        inputAccountNumTF.setEnabled(enable);
        inputPwdTF.setEnabled(enable);
        choiceActionTypeC.setEnabled(enable);
        deleteB.setEnabled(enable);
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
        List<Account> list = repository.getAccounts();
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

            repository.createAccount(account);
            textFieldReset(); // TextField 초기화
            allList(); // 계좌 전체 조회
            JOptionPane.showMessageDialog(this, "정상 등록 처리되었습니다.");

        }
    }

    /**
     * 계좌 삭제
     *
     */
    public void removeAccount() {

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
            Account searchAccount = repository.searchAccount(accountNum);

            // 입출금계좌, 마이너스계좌 구분
            String selectedItem = choiceAccountTypeC.getSelectedItem();

            if (searchAccount != null) {
                // 입출금계좌 선택시 입출금계좌일 경우만 삭제 진행
                if (selectedItem.equals(AccountType.ACCOUNT_GENERAL.getName())) {
                    if (!(searchAccount instanceof MinusAccount)) {
                        repository.removeAccount(accountNum, passwd);
                        JOptionPane.showMessageDialog(this, accountNum + " 계좌 삭제 정상 처리되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(this, "입력하신 계좌번호에 해당하는 입출금계좌가 존재하지 않습니다.");
                    }

                // 입출금계좌 선택시 입출금계좌일 경우만 삭제 진행
                } else if (selectedItem.equals(AccountType.ACCOUNT_MINUS.getName())) {
                    if (searchAccount instanceof MinusAccount) {
                        repository.removeAccount(accountNum, passwd);
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
     */
    public void searchAccountByNum() {

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
            Account searchAccount = repository.searchAccount(accountNum);

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
     */
    public void searchAccountByOwner() {

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
            List<Account> searchAccounts = repository.searchAccountByOwner(accountOwner);

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
        ((ObjectAccountRepository) repository).close();
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
                if (eventSource == searchNumB) {
                    try {
                        searchAccountByNum();
                    } catch (RuntimeException e1) {
                        JOptionPane.showMessageDialog(searchNumB, "해당 계좌번호와 일치하는 계좌가 존재하지 않습니다.");
                        allList();
                        textFieldReset();
                    }
                    
                // 검색 버튼 이벤트
                } else if (eventSource == searchOwnerB) {
                    try {
                        searchAccountByOwner();
                    } catch (RuntimeException e1) {
                        JOptionPane.showMessageDialog(searchOwnerB, "해당 예금주명과 일치하는 계좌가 존재하지 않습니다.");
                        allList();
                        textFieldReset();
                    }
                    
                // 삭제 버튼 이벤트
                } else if (eventSource == deleteB) {
                    try {
                        removeAccount();
                    } catch (RuntimeException e1) {
                        JOptionPane.showMessageDialog(deleteB, "해당 계좌번호와 일치하는 계좌가 존재하지 않거나, 비밀번호가 일치 하지않습니다.");
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
                allInputEnable(false);
                allList();
            }
        });

        // 계좌등록
        registB.addActionListener(actionHandler);

        // 전체계좌 조회
        totalViewB.addActionListener(actionHandler);

        // 계좌번호 조회
        searchNumB.addActionListener(actionHandler);

        // 예금주명 조회
        searchOwnerB.addActionListener(actionHandler);

        // 계좌 삭제
        deleteB.addActionListener(actionHandler);
        
        // 계좌 종류 선택에 따른 입력칸 활성화 
        class ItemHandlerAcc implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItemA = choiceAccountTypeC.getSelectedItem();
                    String selectedItemB = choiceActionTypeC.getSelectedItem();
                    if (selectedItemA.equals(AccountType.ACCOUNT_ALL.getName())) {
                        allInputEnable(false);
                        choiceActionTypeC.select("기능선택");
                    } else if (selectedItemB.equals(ActionType.ACCOUNT_ADD.getName())) {
                        if (selectedItemA.equals(AccountType.ACCOUNT_GENERAL.getName())) {
                            allInputEnable(false);
                            generalInputEnable(true);
                        } else if (selectedItemA.equals(AccountType.ACCOUNT_MINUS.getName())) {
                            allInputEnable(false);
                            minusInputEnable(true);
                        }
                    } else {
                        typeInputEnable(true);
                    }
                }
            }
        }
        ItemListener itemListenerAcc = new ItemHandlerAcc();
        choiceAccountTypeC.addItemListener(itemListenerAcc);
        
        // 기능 종류 선택에 따른 입력칸 활성화 
        class ItemHandlerType implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItemA = choiceAccountTypeC.getSelectedItem();
                    String selectedItemB = choiceActionTypeC.getSelectedItem();
                    if (selectedItemB.equals(ActionType.ACTION_ALL.getName())) {
                        allInputEnable(false);
                        typeInputEnable(true);
                    } else if (selectedItemB.equals(ActionType.ACCOUNT_CHECK.getName())) {
                        allInputEnable(false);
                        checkEnable(true);
                    } else if (selectedItemB.equals(ActionType.ACCOUNT_SEARCH.getName())) {
                        allInputEnable(false);
                        searchEnable(true);
                    } else if (selectedItemB.equals(ActionType.ACCOUNT_DELETE.getName())) {
                        allInputEnable(false);
                        deleteEnable(true);
                    } else if (selectedItemB.equals(ActionType.ACCOUNT_ADD.getName())) {
                        if (selectedItemA.equals(AccountType.ACCOUNT_ALL.getName())) {
                            allInputEnable(false);
                            typeInputEnable(true);
                        } else if (selectedItemA.equals(AccountType.ACCOUNT_GENERAL.getName())) {
                            allInputEnable(false);
                            generalInputEnable(true);
                        } else if (selectedItemA.equals(AccountType.ACCOUNT_MINUS.getName())) {
                            allInputEnable(false);
                            minusInputEnable(true);  
                        }
                    }
                }
            }

        }
        ItemListener itemListenerType = new ItemHandlerType();
        choiceActionTypeC.addItemListener(itemListenerType);
        

    }
}