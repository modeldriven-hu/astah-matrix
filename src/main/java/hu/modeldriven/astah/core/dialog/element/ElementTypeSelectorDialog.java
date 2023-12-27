package hu.modeldriven.astah.core.dialog.element;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.function.Consumer;

public class ElementTypeSelectorDialog {

    private final Component parentComponent;
    private final Consumer<ElementTypeSelector> callback;

    public ElementTypeSelectorDialog(Component parentComponent, Consumer<ElementTypeSelector> callback) {
        this.parentComponent = parentComponent;
        this.callback = callback;
    }

    public void show() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(new BorderLayout());

        ElementTypeSelectorTableData data = new ElementTypeSelectorTableData();
        ElementTypeSelectorPanel panel = new ElementTypeSelectorPanel(dialog, data, callback);
        dialog.getContentPane().add(panel);

        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new BorderLayout());

            JButton testButton = new JButton("Display dialog");
            frame.getContentPane().add(testButton, BorderLayout.CENTER);

            testButton.addActionListener(actionEvent -> {
                ElementTypeSelectorDialog dialog = new ElementTypeSelectorDialog(null, selector -> {
                    System.out.println("Selected selector = " + selector.name());
                });

                dialog.show();
            });

            frame.pack();
            frame.setVisible(true);

        });
    }

}
