package com.three_buttons;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("App");
        //jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //jFrame.setSize(400,400);
        /*Container container = jFrame.getContentPane();
        container.setBackground(Color.WHITE);*/
        //jFrame.getContentPane().setBackground(Color.WHITE);
        ButtonPanel buttonPanel = new ButtonPanel();
        jFrame.setContentPane(buttonPanel);
        /*BoxLayout boxLayout = new BoxLayout(jFrame.getContentPane(), BoxLayout.PAGE_AXIS);
        jFrame.setLayout(boxLayout);
        jFrame.add(new JButton("hello"));
        jFrame.add(Box.createRigidArea(new Dimension(0,20)));
        jFrame.add(new JButton("hello"));
        jFrame.add(Box.createGlue());
        jFrame.add(new JButton("world"));*/
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
