package com.animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.stream.Stream;

public class RadioButtonPanel extends JPanel {

    private JLabel pictureLabel = new JLabel();
    private ButtonGroup radioButtonGroup = new ButtonGroup();

    public RadioButtonPanel() {
        setLayout(new BorderLayout());
        JPanel radioButtonPanel = new JPanel(new GridLayout(0, 1));
        Stream.of(AnimalEnum.values())
                .map(this::animalToRadioButton)
                .forEach(radioButtonPanel::add);
        add(radioButtonPanel, BorderLayout.LINE_START);
        pictureLabel.setPreferredSize(new Dimension(180, 125));
        ImageIcon imageIcon = AnimalEnum.RABBIT.getImageIcon();
        pictureLabel.setIcon(imageIcon);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private JRadioButton animalToRadioButton(AnimalEnum animalEnum) {
        JRadioButton radioButton = animalEnum.createRadioButton(radioButtonGroup, this::radioButtonListening);
        return radioButton;
    }

    private void radioButtonListening(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
        String pictureName = actionEvent.getActionCommand() + ".gif";
        ImageIconLoader imageIconLoader = new ImageIconLoader(pictureName);
        ImageIcon imageIcon = imageIconLoader.loadImageIcon();
        pictureLabel.setIcon(imageIcon);
    }

}
