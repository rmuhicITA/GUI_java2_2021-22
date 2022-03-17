package com.three_buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

public class ButtonPanel extends JPanel {
    private JButton leftButton;
    private JButton middleButton;
    private JButton rightButton;

    public ButtonPanel() {
        ButtonActionListener buttonActionListener = new ButtonActionListener();
        ImageIconLoader imageIconLoader = new ImageIconLoader();


        this.leftButton = new JButton("Disable middle button");
        this.leftButton.setToolTipText("This is my left button");
        this.leftButton.setActionCommand("disable");
        this.leftButton.setMnemonic(KeyEvent.VK_D);
        ImageIcon rightIcon = imageIconLoader.load("right.png");
        this.leftButton.setHorizontalTextPosition(SwingConstants.LEADING);
        this.leftButton.setVerticalTextPosition(SwingConstants.CENTER);
        this.leftButton.setIcon(rightIcon);
        this.leftButton.addActionListener(buttonActionListener);

        this.middleButton = new JButton("Victim");
        this.middleButton.setToolTipText("This is my victim button");
        ImageIcon middleIcon = imageIconLoader.load("middle.png");
        this.middleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        this.middleButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.middleButton.setIcon(middleIcon);

        this.rightButton = new JButton("Enable middle button");
        this.rightButton.setToolTipText("This is my right button");
        this.rightButton.setActionCommand("enable");
        this.rightButton.setMnemonic(KeyEvent.VK_E);
        ImageIcon leftIcon = imageIconLoader.load("left.png");
        this.rightButton.setIcon(leftIcon);
        this.rightButton.addActionListener(buttonActionListener);

        add(leftButton);
        add(middleButton);
        add(rightButton);
    }

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Is this ok?");
            //middleButton.setEnabled(false);
            if (e.getActionCommand().equals("disable")) {
                middleButton.setEnabled(false);
            } else {
                middleButton.setEnabled(true);
            }
        }
    }

    private static class ImageIconLoader {

        public ImageIcon load(String path) {
            URL imageUrl = ButtonPanel.class.getResource(path);
            if (imageUrl != null) {
                Image image= new ImageIcon(imageUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                return new ImageIcon(image);
            } else {
                System.err.println("There is no picture in path: " + path);
                return null;
            }
        }

    }
}
