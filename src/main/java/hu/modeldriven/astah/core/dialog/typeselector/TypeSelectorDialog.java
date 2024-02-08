package hu.modeldriven.astah.core.dialog.typeselector;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.function.Consumer;

public class TypeSelectorDialog {

    private final Component parentComponent;

    private final TypeRepository repository;

    private final Consumer<SelectedType> callback;

    public TypeSelectorDialog(Component parentComponent, TypeRepository repository, Consumer<SelectedType> callback) {
        this.parentComponent = parentComponent;
        this.repository = repository;
        this.callback = callback;
    }

    public void show() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(new BorderLayout());

        TypeSelectorPanel panel = new TypeSelectorPanel(dialog, repository, callback);
        dialog.getContentPane().add(panel);

        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
    }

}
