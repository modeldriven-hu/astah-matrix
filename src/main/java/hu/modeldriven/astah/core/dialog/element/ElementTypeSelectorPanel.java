/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.core.dialog.element;

import com.change_vision.jude.api.inf.model.IAction;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.IRequirement;
import com.change_vision.jude.api.inf.model.IUseCase;
import hu.modeldriven.astah.core.dialog.element.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;

import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.List;
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

        ElementTypeSelectorTableModel tableModel = new ElementTypeSelectorTableModel(provideRows());

        this.elementTable.setModel(tableModel);

        TableColumnModel columnModel = this.elementTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(0).setMaxWidth(50);

        elementTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.okButton.addActionListener(actionEvent -> {
            tableModel.selectedRow().ifPresent(row -> {
                this.elementSelectedCallback.accept(row.matcher());
            });

            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

        this.cancelButton.addActionListener(actionEvent -> {
            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

    }

    private List<ElementTypeSelectorTableRow> provideRows(){
        List<ElementTypeSelectorTableRow> rows = new ArrayList<>();

        rows.add(new ElementTypeSelectorTableRow("Activity", new ClassMatcher(IAction.class)));
        rows.add(new ElementTypeSelectorTableRow("Requirement", new ClassMatcher(IRequirement.class)));
        rows.add(new ElementTypeSelectorTableRow("Use Case", new ClassMatcher(IUseCase.class)));

        return rows;
    }


}
