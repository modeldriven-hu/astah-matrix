package hu.modeldriven.astah.core.dialog.element;

import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.util.function.Consumer;
import java.util.regex.PatternSyntaxException;

/**
 * @author zsolt
 */
public class ElementTypeSelectorPanel extends AbstractElementTypeSelectorPanel {

    private final Consumer<ElementTypeSelector> elementSelectedCallback;
    private final JDialog parentDialog;

    private final ElementTypeSelectorTableData data;

    public ElementTypeSelectorPanel(JDialog parentDialog,
                                    ElementTypeSelectorTableData data,
                                    Consumer<ElementTypeSelector> elementSelectedCallback) {
        super();
        this.parentDialog = parentDialog;
        this.data = data;
        this.elementSelectedCallback = elementSelectedCallback;
        initComponents();
    }

    private void initComponents() {

        ElementTypeSelectorTableModel tableModel = new ElementTypeSelectorTableModel(data);
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
                    rowSorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException e) {
                    // ignore
                }
            }
        });

        this.okButton.addActionListener(actionEvent -> {
            tableModel.selectedRow().ifPresent(this.elementSelectedCallback);

            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

        this.cancelButton.addActionListener(actionEvent -> {
            this.parentDialog.setVisible(false);
            this.parentDialog.dispose();
        });

    }

}
