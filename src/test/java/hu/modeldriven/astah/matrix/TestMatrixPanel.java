package hu.modeldriven.astah.matrix;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import hu.modeldriven.astah.matrix.ui.MatrixPanel;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class TestMatrixPanel {

    private JFrame frame;

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try {
                UIManager.put("TitlePane.menuBarEmbedded", false);
                FlatLightFlatIJTheme.setup();
                FlatLaf.updateUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        SwingUtilities.invokeLater(() -> {
            try {
                TestMatrixPanel window = new TestMatrixPanel();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TestMatrixPanel() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    protected void initialize() {
        frame = new JFrame("Matrix test");
        frame.setMinimumSize(new Dimension(763, 300));
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EventBus eventBus = new EventBus();

        JPanel panel = new MatrixPanel(frame, eventBus);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
    }


}
