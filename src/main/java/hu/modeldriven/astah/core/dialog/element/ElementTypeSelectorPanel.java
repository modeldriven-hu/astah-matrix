/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.core.dialog.element;

import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;

import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.PatternSyntaxException;

/**
 * @author zsolt
 */
public class ElementTypeSelectorPanel extends AbstractElementTypeSelectorPanel {

    private final Consumer<ElementMatcher> elementSelectedCallback;
    private final JDialog parentDialog;

    private final Supplier<List<ElementTypeSelectorTableRow>> data;

    public ElementTypeSelectorPanel(JDialog parentDialog,
                                    Supplier<List<ElementTypeSelectorTableRow>> data,
                                    Consumer<ElementMatcher> elementSelectedCallback) {
        super();
        this.parentDialog = parentDialog;
        this.data = data;
        this.elementSelectedCallback = elementSelectedCallback;
        initComponents();
    }

    private void initComponents() {

        ElementTypeSelectorTableModel tableModel = new ElementTypeSelectorTableModel(
                data.get());
        this.elementTable.setModel(tableModel);

        TableRowSorter<ElementTypeSelectorTableModel> rowSorter = new TableRowSorter<>(tableModel);
        this.elementTable.setRowSorter(rowSorter);

        TableColumnModel columnModel = this.elementTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(0).setMaxWidth(50);

        elementTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.filterTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                handleTextChange();
            }

            void handleTextChange() {
                String text = ElementTypeSelectorPanel.this.filterTextField.getText();

                try {
                    RowFilter filter = RowFilter.regexFilter(text);
                    rowSorter.setRowFilter(filter);
                } catch (PatternSyntaxException e) {
                    // ignore
                }
            }
        });

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

}
