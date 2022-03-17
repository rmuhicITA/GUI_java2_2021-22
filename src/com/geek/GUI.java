package com.geek;

import javax.swing.*;

public class GUI {
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });*/
        //SwingUtilities.invokeLater(()->createGUI());
        SwingUtilities.invokeLater(GUI::createGUI);
    }

    static void createGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GeekPanel geekPanel = new GeekPanel();
        frame.setContentPane(geekPanel);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
