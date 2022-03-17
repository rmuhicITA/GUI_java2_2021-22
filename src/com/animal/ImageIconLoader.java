package com.animal;

import javax.swing.*;
import java.net.URL;

public class ImageIconLoader {
    private String imagePath;

    public ImageIconLoader(String imagePath) {
        this.imagePath = imagePath;
    }


    public ImageIcon loadImageIcon() {
        if (!imagePath.endsWith(".gif")) {
            imagePath = imagePath + ".gif";
        }
        URL imageAddress = ImageIconLoader.class.getResource(imagePath);
        System.out.println(imageAddress);
        if (imageAddress != null) {
            ImageIcon imageIcon = new ImageIcon(imageAddress);
            return imageIcon;
        } else {
            System.err.println("Ne mogu učitati sliku...");
            return null;
        }
    }
}
