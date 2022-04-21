package team3.tetris.component;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CustomButton extends JButton {
    private final Font font = this.getFont();

    public CustomButton() {
        // set painted
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);

        // set Inputkey(focus)
        this.getInputMap().getParent().remove(KeyStroke.getKeyStroke("pressed SPACE"));
        this.getInputMap().getParent().remove(KeyStroke.getKeyStroke("released SPACE"));
        this.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("pressed ENTER"), "pressed");
        this.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released ENTER"), "released");

        // set Listners
        this.removeMouseListener(this.getMouseListeners()[0]);
        this.addFocusListener(focusListener);

        CustomButton.this.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 5));
    }

    //focus animation
    private FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            CustomButton.this.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 15));
            CustomButton.this.setForeground(Color.RED);
        }

        @Override
        public void focusLost(FocusEvent e) {
            CustomButton.this.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 5));
            CustomButton.this.setForeground(Color.BLACK);
        }
    };

}