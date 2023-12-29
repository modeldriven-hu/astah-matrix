package hu.modeldriven.astah.matrix.ui;

import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Container;

public class MatrixScreen {

    private final Container parent;
    private final EventBus eventBus;

    public MatrixScreen(Container parent) {
        this.parent = parent;
        this.eventBus = new EventBus();
    }

    public void show() {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new MatrixPanel(frame, eventBus), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatrixScreen screen = new MatrixScreen(null);
            screen.show();
        });
    }

}
