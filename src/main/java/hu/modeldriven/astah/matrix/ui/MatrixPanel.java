/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.matrix.ui;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.representation.AstahModelRepresentation;
import hu.modeldriven.astah.core.representation.DummyNamedElement;
import hu.modeldriven.astah.core.representation.ModelRepresentation;
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
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("java:S125")
public class MatrixPanel extends AbstractMatrixPanel {

    private static final boolean DEBUG = false;

    private static final String BUTTON_FOREGROUND = "Button.foreground";

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

        newButton.setIcon(FontIcon.of(
                MaterialDesign.MDI_FILE,
                16,
                UIManager.getColor(BUTTON_FOREGROUND)));

        openButton.setIcon(FontIcon.of(
                MaterialDesign.MDI_DESKTOP_MAC,
                16,
                UIManager.getColor(BUTTON_FOREGROUND)));

        saveButton.setIcon(FontIcon.of(
                MaterialDesign.MDI_CONTENT_SAVE,
                16,
                UIManager.getColor(BUTTON_FOREGROUND)));

        exportButton.setIcon(FontIcon.of(
                MaterialDesign.MDI_EXPORT,
                16,
                UIManager.getColor(BUTTON_FOREGROUND)));

        queryButton.setIcon(FontIcon.of(
                MaterialDesign.MDI_RUN,
                16,
                UIManager.getColor(BUTTON_FOREGROUND)));

        findInRowButton.setIcon(FontIcon.of(
                MaterialDesign.MDI_DRAG_VERTICAL,
                16,
                UIManager.getColor(BUTTON_FOREGROUND)));

        findInColumnButton.setIcon(FontIcon.of(
                MaterialDesign.MDI_DRAG_VERTICAL,
                16,
                UIManager.getColor(BUTTON_FOREGROUND)));

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

        matrixTable.addPropertyChangeListener("model", propertyChangeEvent -> eventBus.publish(new TableStructureChangedEvent()));

        addPopupMenu();
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
        JMenu relationshipMenu = new JMenu("Relationship");

        JMenuItem rowToColumnItem = new JMenuItem("Create Row to Column");
        rowToColumnItem.addActionListener(actionEvent -> fireMatrixEvent(
                new CreateRelationshipRequestedEvent(getSelectedRowElement(),
                        getSelectedColumnElement(), RelationshipDirection.ROW_TO_COLUMN)));

        relationshipMenu.add(rowToColumnItem);

        JMenuItem columnToRowItem = new JMenuItem("Create Column to Row");
        columnToRowItem.addActionListener(actionEvent -> fireMatrixEvent(
                new CreateRelationshipRequestedEvent(getSelectedRowElement(),
                        getSelectedColumnElement(), RelationshipDirection.COLUMN_TO_ROW)));

        relationshipMenu.add(columnToRowItem);

        return relationshipMenu;
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
        openButton.addActionListener(e -> eventBus.publish(new OpenFileRequestedEvent()));
        saveButton.addActionListener(e -> eventBus.publish(new SaveFileRequestedEvent()));
        exportButton.addActionListener(e -> eventBus.publish(new ExportCSVRequestedEvent()));
        findInRowButton.addActionListener(e -> eventBus.publish(new FindSelectionInRowRequested()));
        findInColumnButton.addActionListener(e -> eventBus.publish(new FindSelectionInColumnRequested()));

        newButton.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(null,
                    "The following operation will clear all fields. Do you want to continue?",
                    "Create a new query",
                    JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION) {
                return;
            }

            eventBus.publish(new ResetRequestedEvent());
        });

    }

    private void initUseCases() {

        ModelRepresentation modelRepresentation = new AstahModelRepresentation();

        this.eventBus.subscribe(new DisplayRowTypeNameUseCase(rowTypeTextField));
        this.eventBus.subscribe(new DisplayRowTypeSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new DisplayColumnTypeNameUseCase(columnTypeTextField));
        this.eventBus.subscribe(new DisplayColumnTypeSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new DisplayRowPackageNameUseCase(rowPackageTextField));
        this.eventBus.subscribe(new DisplayRowPackageSelectorUseCase(parentComponent, eventBus, modelRepresentation));

        this.eventBus.subscribe(new DisplayColumnPackageNameUseCase(columnPackageTextField));
        this.eventBus.subscribe(new DisplayColumnPackageSelectorUseCase(parentComponent, eventBus, modelRepresentation));

        this.eventBus.subscribe(new DisplayRelationshipNameUseCase(dependencyTextField));
        this.eventBus.subscribe(new DisplayRelationshipSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new SetToolbarButtonsStatusUseCase(queryButton, saveButton));
        this.eventBus.subscribe(new SetExportButtonStatusUseCase(exportButton));

        this.eventBus.subscribe(new CalculateMatrixDataUseCase(eventBus));

        this.eventBus.subscribe(new DisplayMatrixDataUseCase(eventBus, matrixTable));

        this.eventBus.subscribe(new ShowElementInStructureTreeUseCase(eventBus));

        this.eventBus.subscribe(new CreateRelationUseCase(eventBus));

        this.eventBus.subscribe(new DisplayExceptionUseCase());

        this.eventBus.subscribe(new DisplayErrorOnNoMatrixElementSelectionUseCase(parentComponent));

        this.eventBus.subscribe(new HideColumnsUseCase(eventBus, matrixTable));

        this.eventBus.subscribe(new HideRowsUseCase(eventBus, matrixTable));

        this.eventBus.subscribe(new ShowAllRowsUseCase(eventBus, matrixTable));

        this.eventBus.subscribe(new ShowAllColumnsUseCase(eventBus, matrixTable));

        this.eventBus.subscribe(new ResizeTableColumnsUseCase(matrixTable, panelLayout, contentPanel));

        this.eventBus.subscribe(new ClearOnResetUseCase(matrixTable, Arrays.asList(
                columnPackageTextField, columnTypeTextField, dependencyTextField, rowPackageTextField, rowTypeTextField
        )));

        this.eventBus.subscribe(new BuildQueryModelUseCase(eventBus));
        this.eventBus.subscribe(new SaveFileUseCase(eventBus, this));
        this.eventBus.subscribe(new OpenFileUseCase(eventBus, this, modelRepresentation));
        this.eventBus.subscribe(new ExportToCSVUseCase(eventBus, this));

        this.eventBus.subscribe(new PresentLoadedQueryModelUseCase(eventBus));
        this.eventBus.subscribe(new FindSelectionInRowUseCase(matrixTable, modelRepresentation));
        this.eventBus.subscribe(new FindSelectionInColumnUseCase(matrixTable, modelRepresentation));
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
        IntStream.range(1, 5).forEach(i -> columns.add(new DummyNamedElement("UseCase " + i)));

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
