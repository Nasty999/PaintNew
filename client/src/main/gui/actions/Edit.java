package main.gui.actions;

import main.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Edit {
    private final MainFrame mainFrame;

    private final Action changeBrushSize;
    private final Action changeBrushColor;

    public Edit(MainFrame mainFrame) {
        this.mainFrame = mainFrame;


        this.changeBrushSize = new ChangeBrushSize();
        this.changeBrushColor = new ChangeBrushColor();
    }

    public Action getChangeBrushSize() {
        return changeBrushSize;
    }

    public Action getChangeBrushColor() {
        return changeBrushColor;
    }

    private class ChangeBrushSize extends AbstractAction {

        ChangeBrushSize() {
            super("Brush size");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(mainFrame.getIsConnected()) {
                String[] sizes = {"5", "7", "10", "13", "17", "20", "25", "30", "40", "50"};

                String size = (String) JOptionPane.showInputDialog(mainFrame,
                        "Select a brush size",
                        "Select a size", JOptionPane.QUESTION_MESSAGE,
                        null, sizes, sizes[0]);

                mainFrame.getBoard().setBrushSize(Integer.parseInt(size));
            } else {
                JOptionPane.showMessageDialog(mainFrame, "You are not logged in!!!");
            }
        }
    }

    private class ChangeBrushColor extends AbstractAction {
        ChangeBrushColor() {
            super("Brush color");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(mainFrame.getIsConnected()) {
                Color color = JColorChooser.showDialog(mainFrame, "Select a color", null);
                mainFrame.getBoard().setBrushColor(color);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "You are not logged in!!!");
            }
        }
    }

}



