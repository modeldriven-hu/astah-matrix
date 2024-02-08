package hu.modeldriven.astah.matrix.ui;

import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;

public class MatrixScreen {

    private final Container parent;
    private final EventBus eventBus;

    public MatrixScreen(Container parent) {
        this.parent = parent;
        this.eventBus = new EventBus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatrixScreen screen = new MatrixScreen(null);
            screen.show();
        });
    }

    public void show() {
        JFrame frame = new JFrame();

        JPanel frameContentPanel = new JPanel(new BorderLayout());
        frameContentPanel.setName("frameContentPanel");
        frameContentPanel.add(new MatrixPanel(frame, eventBus), BorderLayout.CENTER);
        frame.setContentPane(frameContentPanel);

        frame.pack();
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);
    }

}
