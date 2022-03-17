package com.geek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GeekPanel extends JPanel {
    private JCheckBox chinCheckBox;
    private JCheckBox glassesCheckBox;
    private JCheckBox hairCheckBox;
    private JCheckBox teethCheckBox;

    private JLabel pictureLabel;
    private StringBuffer choices; //cght -> -ght -> --ht -> ----

    /*public GeekPanel() {
        super(new BorderLayout());

        chinCheckBox = new JCheckBox("Chin");
        chinCheckBox.setMnemonic('c');
        chinCheckBox.setSelected(true);

        glassesCheckBox = new JCheckBox("Glasses");
        glassesCheckBox.setMnemonic('g');
        glassesCheckBox.setSelected(true);

        hairCheckBox = new JCheckBox("Hair");
        hairCheckBox.setMnemonic('h');
        hairCheckBox.setSelected(true);

        teethCheckBox = new JCheckBox("Teeth");
        teethCheckBox.setMnemonic('t');
        teethCheckBox.setSelected(true);

        JPanel checkBoxPanel = new JPanel(new GridLayout(0, 1));
        checkBoxPanel.add(chinCheckBox);
        checkBoxPanel.add(glassesCheckBox);
        checkBoxPanel.add(hairCheckBox);
        checkBoxPanel.add(teethCheckBox);

        choices = new StringBuffer("cght");
        pictureLabel = new JLabel();
        updatePicture();

        add(checkBoxPanel, BorderLayout.PAGE_START);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }*/

    public GeekPanel() {
        super(new BorderLayout());
        choices = new StringBuffer("cght");
        pictureLabel = new JLabel();
        JPanel checkBoxPanel = new JPanel(new GridLayout(0, 1));
        chinCheckBox = createCheckBox("Chin", checkBoxPanel);
        glassesCheckBox = createCheckBox("Glasses", checkBoxPanel);
        hairCheckBox = createCheckBox("Hair", checkBoxPanel);
        teethCheckBox = createCheckBox("Teeth", checkBoxPanel);
        updatePicture();
        add(checkBoxPanel, BorderLayout.LINE_START);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }


    private JCheckBox createCheckBox(String label, JPanel panel) {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.addItemListener(new CheckBoxItemListener());
        checkBox.setSelected(true);
        panel.add(checkBox);
        return checkBox;
    }

    private void updatePicture() {
        ImageIconLoader imageIconLoader = new ImageIconLoader(GeekPanel.class);
        String picName = "geek-" + choices + ".gif";
        ImageIcon imageIcon = imageIconLoader.load(picName);
        if (imageIcon != null) {
            pictureLabel.setIcon(imageIcon);
        } else {
            pictureLabel.setText("Nema slike sa imenom:" + picName);
        }
    }

    private class CheckBoxItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            Object source = itemEvent.getSource();
            char c = '-';
            int index = 0;
            if (source == chinCheckBox) {
                c = 'c';
            } else if (source == glassesCheckBox) {
                index = 1;
                c = 'g';
            } else if (source == hairCheckBox) {
                index = 2;
                c = 'h';
            } else if (source == teethCheckBox) {
                index = 3;
                c = 't';
            }
            if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                c = '-';
            }
            choices.setCharAt(index, c);
            updatePicture();
        }
    }
}
