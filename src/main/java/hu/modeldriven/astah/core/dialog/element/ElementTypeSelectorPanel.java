/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.core.dialog.element;

import com.change_vision.jude.api.inf.model.IModel;
import hu.modeldriven.astah.core.dialog.element.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;

import javax.swing.JDialog;
import java.util.function.Consumer;

/**
 * @author zsolt
 */
public class ElementTypeSelectorPanel extends AbstractElementTypeSelectorPanel {

    private final Consumer<ElementMatcher> elementSelectedCallback;
    private final JDialog parentDialog;

    public ElementTypeSelectorPanel(JDialog parentDialog, Consumer<ElementMatcher> elementSelectedCallback) {
        super();
        this.parentDialog = parentDialog;
        this.elementSelectedCallback = elementSelectedCallback;
        initComponents();
    }

    private void initComponents() {

        this.okButton.addActionListener(actionEvent -> {
            this.elementSelectedCallback.accept(new ClassMatcher(IModel.class));
            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

        this.cancelButton.addActionListener(actionEvent -> {
            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

    }


}
