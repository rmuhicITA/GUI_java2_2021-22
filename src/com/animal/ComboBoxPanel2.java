package com.animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.stream.Stream;

public class ComboBoxPanel2 extends JPanel {

    private JLabel pictureLabel;
    private JComboBox<String> animalComboBox;

    public ComboBoxPanel2() {
        super(new BorderLayout());
        pictureLabel = new JLabel();
        pictureLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pictureLabel.setHorizontalAlignment(JLabel.CENTER);

        AnimalEnum[] animals = AnimalEnum.values();

        /*String[] animalNames =
                Stream.of(animals)
                .map(AnimalEnum::getAnimalName)
                .collect(Collectors.toList())
                        .toArray(String[]::new);
         */
        String[] animalNames =
                Stream.of(animals)
                        .map(AnimalEnum::getAnimalName).toList()
                        .toArray(String[]::new);

        animalComboBox = new JComboBox<>(animalNames);
        animalComboBox.addActionListener(this::onComboBoxItemSelect);
        animalComboBox.setSelectedItem(0);

        updatePicture();

        add(animalComboBox, BorderLayout.NORTH);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void updatePicture() {
        ImageIconLoader2 imageIconLoader = new ImageIconLoader2(App.class);
        String animalName = (String) animalComboBox.getSelectedItem();
        String pictureName = animalName + ".gif";
        ImageIcon imageIcon = imageIconLoader.load(pictureName);
        pictureLabel.setIcon(imageIcon);
    }

    public void onComboBoxItemSelect(ActionEvent actionEvent) {
        updatePicture();
    }
}
