package com.animal;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Runnable runnable = App::createAndShowGui;
        SwingUtilities.invokeLater(runnable);
    }

    private static void createAndShowGui(){
        JFrame frame = new JFrame();
        frame.setTitle("Demo GUI");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //JPanel panel = new RadioButtonPanel();
        JPanel panel = new ComboBoxPanel2();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
