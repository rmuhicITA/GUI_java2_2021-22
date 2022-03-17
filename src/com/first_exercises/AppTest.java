package com.first_exercises;

import javax.swing.*;

public class AppTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        /*JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(new JButton("INNER START"), BorderLayout.PAGE_START);
        jPanel.add(new JButton("INNER LINE START"), BorderLayout.LINE_START);
        jPanel.add(new JButton("INNER CENTER"), BorderLayout.CENTER);
        jPanel.add(new JButton("INNER LINE_END"), BorderLayout.LINE_END);
        jPanel.add(new JButton("INNER PAGE_END"), BorderLayout.PAGE_END);*/

        //JButton jButton1 = new JButton("Button 1");
        /*JButton jButton2 = new JButton("Hello");
        JButton jButton3 = new JButton("World");
        JButton jButton4 = new JButton("Button 4");
        JButton jButton5 = new JButton("Button 5");*/
        //Container frameContentPane = jFrame.getContentPane();
        //BoxLayout boxLayout = new BoxLayout(jFrame.getContentPane(), BoxLayout.X_AXIS);
        //BoxLayout boxLayout = new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS);
        //jFrame.add(jButton1, BorderLayout.NORTH);
        //jFrame.add(jPanel, BorderLayout.NORTH);
        /*jFrame.add(jButton2, BorderLayout.LINE_START);
        jFrame.add(jButton3, BorderLayout.CENTER);
        jFrame.add(jButton3, BorderLayout.CENTER);
        jFrame.add(jButton4, BorderLayout.LINE_END);
        jFrame.add(jButton5, BorderLayout.PAGE_END);
        SpringLayout springLayout = new SpringLayout();
        jFrame.setLayout(springLayout);
        jFrame.add(jButton2);
        jFrame.add(jButton3);
        springLayout.putConstraint(SpringLayout.WEST, jButton2, 50, SpringLayout.WEST, jFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, jButton2, 50, SpringLayout.NORTH, jFrame.getContentPane());
        jFrame.add(jButton4);
        jFrame.add(jButton5);*/
        //jFrame.setLayout(boxLayout);


        //grid
        /*GridLayout gridLayout = new GridLayout(2,2);
        jFrame.getContentPane().setLayout(gridLayout);
        jFrame.add(new JButton("1"));
        jFrame.add(new JButton("2"));
        jFrame.add(new JButton("3"));
        jFrame.add(new JButton("4"));*/
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
