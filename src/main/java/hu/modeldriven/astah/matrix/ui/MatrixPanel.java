/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.matrix.ui;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.model.AstahModel;
import hu.modeldriven.astah.core.model.DummyNamedElement;
import hu.modeldriven.astah.core.model.Model;
import hu.modeldriven.astah.matrix.ui.event.*;
import hu.modeldriven.astah.matrix.ui.table.MatrixData;
import hu.modeldriven.astah.matrix.ui.table.MatrixDataImpl;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;
import hu.modeldriven.astah.matrix.ui.table.renderer.MatrixTableHeaderRenderer;
import hu.modeldriven.astah.matrix.ui.table.renderer.NamedElementCellRenderer;
import hu.modeldriven.astah.matrix.ui.table.renderer.RelationshipDirectionCellRenderer;
import hu.modeldriven.astah.matrix.ui.usecase.*;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("java:S125")
public class MatrixPanel extends AbstractMatrixPanel {

    private static final boolean DEBUG = false;

    private final Component parentComponent;
    private final transient EventBus eventBus;

    public MatrixPanel(Component parentComponent, EventBus eventBus) {
        super();
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;

        initUIComponents();
        initActionListeners();
        initUseCases();

        if (DEBUG) {
            fillTableWithDemoData();
        }
    }

    private void initUIComponents() {

        matrixTable.getTableHeader().setResizingAllowed(true);
        matrixTable.getTableHeader().setReorderingAllowed(false);

        ListSelectionListener listener = e -> {
            matrixTable.getTableHeader().repaint();
            matrixTable.repaint();
        };

        matrixTable.getSelectionModel().addListSelectionListener(listener);
        matrixTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

        matrixTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        matrixTable.setRowSelectionAllowed(false);
        matrixTable.setColumnSelectionAllowed(false);

        matrixTable.setDefaultRenderer(INamedElement.class, new NamedElementCellRenderer());
        matrixTable.setDefaultRenderer(RelationshipDirection.class, new RelationshipDirectionCellRenderer());
        matrixTable.getTableHeader().setDefaultRenderer(new MatrixTableHeaderRenderer());

        matrixTable.addPropertyChangeListener("model", propertyChangeEvent -> adjustTableColumnsWidth(matrixTable));

        addPopupMenu();
    }

    private void adjustTableColumnsWidth(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        int sumWidth = 0;

        // Iterate through each column
        for (int column = 0; column < table.getColumnCount(); column++) {

            TableColumn tableColumn = columnModel.getColumn(column);

            // Get the header width
            TableCellRenderer defaultHeaderRenderer = table.getTableHeader().getDefaultRenderer();
            Component headerComp = defaultHeaderRenderer.getTableCellRendererComponent(table, tableColumn.getHeaderValue(), false, false, 0, column);

            int calculatedWidth = headerComp.getPreferredSize().width;

            // Get the content width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Object value = table.getValueAt(row, column);
                Component comp = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, column);
                calculatedWidth = Math.max(comp.getPreferredSize().width, calculatedWidth);
            }

            sumWidth += calculatedWidth;

            // Handle last column
            if (column == table.getColumnCount() - 1){

                // FIXME the calculation is sadly not still perfect, needs to be investigated
                // In general the BorderLayout does not change the preferred size of the component
                // Maybe getting rid of BorderLayout in favor of miglayout solves this issue
                
                int panelWidth = panelLayout.preferredLayoutSize(contentPanel).width - UIManager.getInt("ScrollBar.width");

                int lastColumnSize = calculatedWidth;

                // If width is smaller than of the scroll pane, the match it
                if (sumWidth <= panelWidth){
                    // we already added the column width so we need to remove and then calculate
                    int widthBeforeLastColumn = sumWidth - calculatedWidth;
                    lastColumnSize = panelWidth - widthBeforeLastColumn;
                }

                tableColumn.setPreferredWidth(lastColumnSize);
            } else {
                // Set the preferred width for the column
                tableColumn.setPreferredWidth(calculatedWidth);
            }
        }

        table.invalidate();
    }

    private void addPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenu rowMenu = createRowMenu();
        JMenu columnMenu = createColumnMenu();
        JMenu createRelationshipMenu = createRelationshipMenu();

        popupMenu.add(rowMenu);
        popupMenu.add(columnMenu);
        popupMenu.add(createRelationshipMenu);

        matrixTable.setComponentPopupMenu(popupMenu);
    }

    private JMenu createRowMenu() {
        JMenu rowMenu = new JMenu("Row");

        JMenuItem showRowInStructureItem = new JMenuItem("Show in Structure Tree");
        showRowInStructureItem.addActionListener(actionEvent -> fireMatrixEvent(
                new ShowInStructureTreeRequestedEvent(getSelectedRowElement())));
        rowMenu.add(showRowInStructureItem);

        JMenuItem hideRowsItem = new JMenuItem("Hide rows");
        hideRowsItem.addActionListener(actionEvent -> fireMatrixEvent(
                new HideRowsRequestedEvent(matrixTable.getSelectedRows())));
        rowMenu.add(hideRowsItem);

        JMenuItem showAllRowsItem = new JMenuItem("Show all rows");
        showAllRowsItem.addActionListener(actionEvent -> fireMatrixEvent(
                new ShowAllRowsRequestedEvent()));
        rowMenu.add(showAllRowsItem);

        return rowMenu;
    }

    private JMenu createColumnMenu() {
        JMenu columnMenu = new JMenu("Column");

        JMenuItem showColumnInStructureItem = new JMenuItem("Show in Structure Tree");
        showColumnInStructureItem.addActionListener(actionEvent -> fireMatrixEvent(
                new ShowInStructureTreeRequestedEvent(getSelectedColumnElement())));
        columnMenu.add(showColumnInStructureItem);

        JMenuItem hideColumnsItem = new JMenuItem("Hide columns");
        hideColumnsItem.addActionListener(actionEvent -> fireMatrixEvent(
                new HideColumnsRequestedEvent(matrixTable.getSelectedColumns())));
        columnMenu.add(hideColumnsItem);

        JMenuItem showAllColumnsItem = new JMenuItem("Show all columns");
        showAllColumnsItem.addActionListener(actionEvent -> fireMatrixEvent(
                new ShowAllColumnsRequestedEvent()));
        columnMenu.add(showAllColumnsItem);

        return columnMenu;
    }

    private JMenu createRelationshipMenu() {
        JMenu createRelationshipMenu = new JMenu("Create Relationship");

        JMenuItem rowToColumnItem = new JMenuItem("Row to Column");
        rowToColumnItem.addActionListener(actionEvent -> fireMatrixEvent(
                new CreateRelationshipRequestedEvent(getSelectedRowElement(),
                        getSelectedColumnElement(), RelationshipDirection.ROW_TO_COLUMN)));

        createRelationshipMenu.add(rowToColumnItem);

        JMenuItem columnToRowItem = new JMenuItem("Column to Row");
        columnToRowItem.addActionListener(actionEvent -> fireMatrixEvent(
                new CreateRelationshipRequestedEvent(getSelectedRowElement(),
                        getSelectedColumnElement(), RelationshipDirection.COLUMN_TO_ROW)));

        createRelationshipMenu.add(columnToRowItem);

        return createRelationshipMenu;
    }

    private void fireMatrixEvent(Event event) {
        if (isMatrixSelected()) {
            eventBus.publish(event);
        } else {
            eventBus.publish(new MatrixElementNotSelectedEvent());
        }
    }

    private boolean isMatrixSelected() {
        return getSelectedColumnElement() != null && getSelectedRowElement() != null;
    }

    private INamedElement getSelectedRowElement() {

        int selectedRow = matrixTable.getSelectedRow();

        if (selectedRow != -1 && matrixTable.getModel() instanceof MatrixTableModel) {
            MatrixTableModel model = (MatrixTableModel) matrixTable.getModel();
            return model.getElementByRow(selectedRow);
        }

        return null;
    }

    private INamedElement getSelectedColumnElement() {

        int selectedColumn = matrixTable.getSelectedColumn();

        if (selectedColumn > 0 && matrixTable.getModel() instanceof MatrixTableModel) {
            MatrixTableModel model = (MatrixTableModel) matrixTable.getModel();
            return model.getElementByColumn(selectedColumn);
        }

        return null;
    }

    private void initActionListeners() {
        rowTypeSelectButton.addActionListener(actionEvent -> this.eventBus.publish(new RowTypeSelectionRequestedEvent()));
        rowPackageSelectButton.addActionListener(actionEvent -> this.eventBus.publish(new RowPackageSelectionRequestedEvent()));
        columnTypeSelectButton.addActionListener(actionEvent -> this.eventBus.publish(new ColumnTypeSelectionRequestedEvent()));
        columnPackageSelectButton.addActionListener(actionEvent -> this.eventBus.publish(new ColumnPackageSelectionRequestedEvent()));
        dependencySelectButton.addActionListener(actionEvent -> this.eventBus.publish(new DependencySelectionRequestedEvent()));
        queryButton.addActionListener(actionEvent -> this.eventBus.publish(new QueryRequestedEvent()));
    }

    private void initUseCases() {

        Model model = new AstahModel();

        this.eventBus.subscribe(new DisplayRowTypeNameUseCase(rowTypeTextField));
        this.eventBus.subscribe(new DisplayRowTypeSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new DisplayColumnTypeNameUseCase(columnTypeTextField));
        this.eventBus.subscribe(new DisplayColumnTypeSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new DisplayRowPackageNameUseCase(rowPackageTextField));
        this.eventBus.subscribe(new DisplayRowPackageSelectorUseCase(parentComponent, eventBus, model));

        this.eventBus.subscribe(new DisplayColumnPackageNameUseCase(columnPackageTextField));
        this.eventBus.subscribe(new DisplayColumnPackageSelectorUseCase(parentComponent, eventBus, model));

        this.eventBus.subscribe(new DisplayDependencyNameUseCase(dependencyTextField));
        this.eventBus.subscribe(new DisplayDependencyTypeSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new EnableQueryButtonUseCase(queryButton));

        this.eventBus.subscribe(new CalculateMatrixDataUseCase(eventBus));

        this.eventBus.subscribe(new DisplayMatrixDataUseCase(matrixTable));

        this.eventBus.subscribe(new ShowElementInStructureTreeUseCase(eventBus));

        this.eventBus.subscribe(new CreateRelationUseCase(eventBus));

        this.eventBus.subscribe(new DisplayExceptionUseCase());

        this.eventBus.subscribe(new DisplayErrorOnNoMatrixElementSelectionUseCase(parentComponent));

        this.eventBus.subscribe(new HideColumnsUseCase(matrixTable));

        this.eventBus.subscribe(new HideRowsUseCase(matrixTable));

        this.eventBus.subscribe(new ShowAllRowsUseCase(matrixTable));

        this.eventBus.subscribe(new ShowAllColumnsUseCase(matrixTable));
    }

    private void fillTableWithDemoData() {

        List<INamedElement> rows = new ArrayList<>();
        rows.add(new DummyNamedElement("Transfer and some other text as well comes here"));
        rows.add(new DummyNamedElement("Requirement 2"));
        rows.add(new DummyNamedElement("Requirement 3"));
        rows.add(new DummyNamedElement("Requirement 4"));
        rows.add(new DummyNamedElement("Req 5"));

        IntStream.range(6, 30).forEach(i -> rows.add(new DummyNamedElement("Requirement " + i)));

        List<INamedElement> columns = new ArrayList<>();
        columns.add(new DummyNamedElement("Merchant System"));
        columns.add(new DummyNamedElement("Second column"));

        MatrixData data = new MatrixDataImpl(rows, columns);

        /*
        data.addRelationship(0, 1, RelationshipDirection.ROW_TO_COLUMN);
        data.addRelationship(1, 2, RelationshipDirection.ROW_TO_COLUMN);
        data.addRelationship(2, 0, RelationshipDirection.COLUMN_TO_ROW);
        data.addRelationship(2, 1, RelationshipDirection.BOTH);
        */

        MatrixTableModel tableModel = new MatrixTableModel(data);

        this.matrixTable.setModel(tableModel);
    }

}
