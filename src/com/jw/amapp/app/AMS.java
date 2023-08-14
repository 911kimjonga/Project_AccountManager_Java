package com.jw.amapp.app;

import com.jw.amapp.gui.AMSFrame;

import java.io.IOException;

/**
 * AMS 시스템 실행을 위한 Main 클래스
 * @author 김종원
 *
 */
public class AMS {
    
    public static void main(String[] args) {
        AMSFrame frame;
        try {
            frame = new AMSFrame("AMS");
            frame.init();
            frame.addEventListener();
            frame.setSize(500, 450);
            frame.setResizable(false);
//                frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
}
