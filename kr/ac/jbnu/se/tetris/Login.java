package kr.ac.jbnu.se.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Login extends JPanel {
    private Tetris tetris;

    private JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    private JPanel signUpPanel = new JPanel(new GridLayout(6, 2, 10, 10));
    private JLabel loginId = new JLabel("ID : ");
    private JLabel loginPw = new JLabel("Password : ");
    private JLabel signUpId = new JLabel("ID : ");
    private JLabel signUpPw = new JLabel("Password : ");
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("Sign Up");
    private JButton submitButton = new JButton("Submit");
    private JButton backButton = new JButton("Back");
    private JButton checkDuplicateButton = new JButton("ID 중복 확인");
    private JTextField loginIdField = new JTextField(12);
    private JPasswordField loginPwField = new JPasswordField(12);
    private JTextField signUpIdField = new JTextField(12);
    private JPasswordField signUpPwField = new JPasswordField(12);
    private JPasswordField signUpConfirmPwField = new JPasswordField(12);

    public Login(Tetris tetris){
        this.tetris = tetris;

        add(loginPanel);
        add(signUpPanel);

        loginPanel.setBorder(BorderFactory.createTitledBorder("로그인"));
        loginPanel.add(loginId);
        loginPanel.add(loginIdField);
        loginPanel.add(loginPw);
        loginPanel.add(loginPwField);
        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);

        signUpPanel.setBorder(BorderFactory.createTitledBorder("회원가입"));
        signUpPanel.add(signUpId);
        signUpPanel.add(signUpIdField);
        signUpPanel.add(new JLabel()); // 빈 라벨
        signUpPanel.add(checkDuplicateButton);
        signUpPanel.add(signUpPw);
        signUpPanel.add(signUpPwField);
        signUpPanel.add(new JLabel("Password 확인 : "));
        signUpPanel.add(signUpConfirmPwField);
        signUpPanel.add(submitButton);
        signUpPanel.add(backButton);

        signUpPanel.setVisible(false);

        loginButton.addActionListener(e -> login());
        signUpButton.addActionListener(e -> showSignUpPanel());

        checkDuplicateButton.addActionListener(e -> checkDuplicate());
        submitButton.addActionListener(e -> submitSignUp());
        backButton.addActionListener(e -> showLoginPanel());
    }

    // 로그인 로직
    private void login() {
        String id = loginIdField.getText();
        char[] pw = loginPwField.getPassword();
        if(id.isEmpty() || pw.length == 0){
            JOptionPane.showMessageDialog(null, "ID와 Password를 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
        } 
        //pw 확인 로직 추가
        // else if(){}
        else {
            // 로그인 성공
            tetris.setUserId(id);
            tetris.switchPanel(new MainMenu(tetris));
        }
    }

    // 회원가입 패널 표시
    private void showSignUpPanel() {
        clearFields();
        loginPanel.setVisible(false);
        signUpPanel.setVisible(true);
    }

    // ID 중복 확인
    private void checkDuplicate() {
        String id = signUpIdField.getText();
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "ID 중복 확인 실패", JOptionPane.ERROR_MESSAGE);
        } else {
            // ID 중복 확인 성공
            JOptionPane.showMessageDialog(null, "사용 가능한 ID입니다.", "ID 중복 확인 성공", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 회원가입 로직
    private void submitSignUp() {
        String id = signUpIdField.getText();
        char[] pw = signUpPwField.getPassword();
        char[] confirmPw = signUpConfirmPwField.getPassword();
        if(id.isEmpty() || pw.length == 0 || confirmPw.length == 0){
            JOptionPane.showMessageDialog(null, "ID와 Password를 입력해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
        } else if(!Arrays.equals(pw, confirmPw)){
            JOptionPane.showMessageDialog(null, "Password가 일치하지 않습니다.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
        } else {
            // 회원가입 성공
            JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
            showLoginPanel();
        }
    }

    // 로그인 패널 표시
    private void showLoginPanel() {
        clearFields();
        signUpPanel.setVisible(false);
        loginPanel.setVisible(true);
    }

    // id, pw, confirmPw 필드 초기화
    private void clearFields() {
        loginIdField.setText("");
        loginPwField.setText("");
        signUpIdField.setText("");
        signUpPwField.setText("");
        signUpConfirmPwField.setText("");
    }
}
