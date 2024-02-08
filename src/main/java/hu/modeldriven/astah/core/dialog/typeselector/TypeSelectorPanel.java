package hu.modeldriven.astah.core.dialog.typeselector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.util.function.Consumer;
import java.util.regex.PatternSyntaxException;

/**
 * @author zsolt
 */
public class TypeSelectorPanel extends AbstractTypeSelectorPanel {

    private final transient Consumer<SelectedType> elementSelectedCallback;
    private final JDialog parentDialog;

    private final transient TypeRepository repository;

    public TypeSelectorPanel(JDialog parentDialog,
                             TypeRepository repository,
                             Consumer<SelectedType> elementSelectedCallback) {
        super();
        this.parentDialog = parentDialog;
        this.repository = repository;
        this.elementSelectedCallback = elementSelectedCallback;
        initUIComponents();
    }

    private void initUIComponents() {

        TypeSelectorTableModel tableModel = new TypeSelectorTableModel(repository);
        this.elementTable.setModel(tableModel);

        TableRowSorter<TypeSelectorTableModel> rowSorter = new TableRowSorter<>(tableModel);
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
                String text = TypeSelectorPanel.this.filterTextField.getText();

                try {
                    rowSorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException e) {
                    // ignore
                }
            }
        });

        this.okButton.addActionListener(actionEvent -> {
            tableModel.selectedRow().ifPresent(selectedType -> {
                String stereotypeText = this.stereotypeTextField.getText();

                if (stereotypeText != null && !stereotypeText.trim().isEmpty()) {
                    this.elementSelectedCallback.accept(new SelectedType(selectedType, stereotypeText));
                } else {
                    this.elementSelectedCallback.accept(new SelectedType(selectedType));
                }
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
