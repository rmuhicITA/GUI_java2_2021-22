package com.memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class GridPanel extends JPanel {

    static final Color BACKGROUND = new JLabel().getBackground();
    private List<JButton> fieldCells;
    private List<Color> colors;

    public GridPanel(){
        colors = Arrays.asList(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.BLACK, Color.WHITE);
        fieldCells = initializeGame(colors);
        bindViewToModel(colors, fieldCells);
        setLayout(new GridLayout(4,3));
        for (JButton fieldCell : fieldCells) {
            fieldCell.setBackground(BACKGROUND);
            fieldCell.setEnabled(true);
            add(fieldCell);
        }
    }

    public List<JButton> getFieldCells() {
        return fieldCells;
    }

    public List<Color> getColors() {
        return colors;
    }

    private static void bindViewToModel(List<Color> colors, List<JButton> fieldCells) {
        Collection<JComponent> clickedButtons = new HashSet<>();
        Collections.shuffle(fieldCells);
        Iterator<JButton> randomCells = fieldCells.iterator();
        for (Color color : colors) {
            AbstractAction buttonAction = createButtonAction(clickedButtons, color);
            bindButton(buttonAction, randomCells.next());
            bindButton(buttonAction, randomCells.next());
        }
        clickedButtons.clear();
    }

    private static void bindButton(AbstractAction buttonAction, JButton jButton) {
        jButton.setAction(buttonAction);
        jButton.setBackground(BACKGROUND);
    }

    private static List<JButton> initializeGame(Collection<Color> colors) {
        List<JButton> fieldCells = new ArrayList<>();
        for (Color color : colors) {
            fieldCells.add(new JButton());
            fieldCells.add(new JButton());
        }
        return fieldCells;
    }

    private static AbstractAction createButtonAction(Collection<JComponent> clickedButtons, Color color) {
        AbstractAction abstractAction = new AbstractAction() {
            Collection<JComponent> clickedPartners = new HashSet<>();

            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent thisButton = (JComponent) e.getSource();
                clickedPartners.add(thisButton);
                clickedButtons.add(thisButton);
                thisButton.setBackground(color);
                thisButton.setEnabled(false);
                if (clickedButtons.size() == 2) {
                    if (clickedPartners.size() != 2) {
                        JOptionPane.showMessageDialog(thisButton, "No match");
                        for (JComponent partner : clickedButtons) {
                            partner.setBackground(BACKGROUND);
                            partner.setEnabled(true);
                        }
                    }
                    clickedButtons.clear();
                    clickedPartners.clear();
                }
            }
        };

        return abstractAction;
    }
}
