package com.memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.*;

public class App {
    private static final Color BACKGROUND = new JLabel().getBackground();

    public static void main(String[] args) {
        Runnable runnable = App::createAndShowGui;
        SwingUtilities.invokeLater(runnable);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame();
        frame.setTitle("Memory GUI");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // main panel
        JPanel panel = new JPanel(new BorderLayout());

        // grid panel
        List<Color> colors = Arrays.asList(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.BLACK, Color.WHITE);
        List<JButton> fieldCells = initializeGame(colors);
        JPanel gridLayout = initializeView(fieldCells);
        bindViewToModel(colors, fieldCells);
        panel.add(gridLayout, BorderLayout.CENTER);

        // three button panel
        JPanel boxLayout = new JPanel();
        boxLayout.setLayout(new BoxLayout(boxLayout, BoxLayout.X_AXIS));
        boxLayout.add(new JButton(new AbstractAction("Prekid") {
            @Override
            public void actionPerformed(ActionEvent e) {
                bindViewToModel(colors, fieldCells);
            }
        }));
        boxLayout.add(new JButton(new AbstractAction("Restart") {
            @Override
            public void actionPerformed(ActionEvent e) {
                bindViewToModel(colors, fieldCells);
            }
        }));
        boxLayout.add(new JButton(new AbstractAction("Izlazak") {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        }));
        panel.add(boxLayout, BorderLayout.PAGE_START);

        frame.setContentPane(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
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

    private static JPanel initializeView(List<JButton> fieldCells) {
        JPanel gameField = new JPanel(new GridLayout(4, 3));
        for (JButton fieldCell : fieldCells) {
            fieldCell.setBackground(BACKGROUND);
            fieldCell.setEnabled(true);
            gameField.add(fieldCell);
        }
        return gameField;
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
