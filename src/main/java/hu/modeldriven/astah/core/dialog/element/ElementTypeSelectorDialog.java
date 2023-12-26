package hu.modeldriven.astah.core.dialog.element;

import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.function.Consumer;

public class ElementTypeSelectorDialog {

    private final Consumer<ElementMatcher> callback;

    public ElementTypeSelectorDialog(Consumer<ElementMatcher> callback) {
        this.callback = callback;
    }

    public void show() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(new BorderLayout());

        ElementTypeSelectorTableRowData data = new ElementTypeSelectorTableRowData();
        ElementTypeSelectorPanel panel = new ElementTypeSelectorPanel(dialog, data, callback);
        dialog.getContentPane().add(panel);

        dialog.pack();
        dialog.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new BorderLayout());

            JButton testButton = new JButton("Display dialog");
            frame.getContentPane().add(testButton, BorderLayout.CENTER);

            testButton.addActionListener(actionEvent -> {
                ElementTypeSelectorDialog dialog = new ElementTypeSelectorDialog(matcher -> {
                    System.out.println("Selected matcher = " + matcher);
                });

                dialog.show();
            });

            frame.pack();
            frame.setVisible(true);

        });
    }

}
