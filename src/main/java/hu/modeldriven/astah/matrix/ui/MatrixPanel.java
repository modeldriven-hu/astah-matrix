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
import hu.modeldriven.astah.matrix.ui.table.*;
import hu.modeldriven.astah.matrix.ui.table.renderer.MatrixTableHeaderRenderer;
import hu.modeldriven.astah.matrix.ui.table.renderer.NamedElementCellRenderer;
import hu.modeldriven.astah.matrix.ui.table.renderer.RelationshipDirectionCellRenderer;
import hu.modeldriven.astah.matrix.ui.usecase.*;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class MatrixPanel extends AbstractMatrixPanel {

    private final Component parentComponent;
    private final EventBus eventBus;

    public MatrixPanel(Component parentComponent, EventBus eventBus) {
        super();
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
        initComponents();
        initActionListeners();
        initUseCases();
        fillTableWithDemoData();
    }

    private void initComponents() {

        matrixTable.getTableHeader().setResizingAllowed(true);
        matrixTable.getTableHeader().setReorderingAllowed(false);

        ListSelectionListener listener = e -> {
            matrixTable.getTableHeader().repaint();
            matrixTable.repaint();
        };

        matrixTable.getSelectionModel().addListSelectionListener(listener);
        matrixTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

        matrixTable.setRowSelectionAllowed(false);
        matrixTable.setColumnSelectionAllowed(false);

        matrixTable.setDefaultRenderer(INamedElement.class, new NamedElementCellRenderer());
        matrixTable.setDefaultRenderer(RelationshipDirection.class, new RelationshipDirectionCellRenderer());
        matrixTable.getTableHeader().setDefaultRenderer(new MatrixTableHeaderRenderer());

        addPopupMenu();
    }

    private void addPopupMenu(){
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
        showRowInStructureItem.addActionListener(actionEvent -> eventBus.publish(
                new ShowInStructureTreeRequestedEvent(getSelectedRowElement())));
        rowMenu.add(showRowInStructureItem);

        return rowMenu;
    }

    private JMenu createColumnMenu() {
        JMenu columnMenu = new JMenu("Column");

        JMenuItem showColumnInStructureItem = new JMenuItem("Show in Structure Tree");
        showColumnInStructureItem.addActionListener(actionEvent -> eventBus.publish(
                new ShowInStructureTreeRequestedEvent(getSelectedColumnElement())));
        columnMenu.add(showColumnInStructureItem);

        return columnMenu;
    }

    private JMenu createRelationshipMenu() {
        JMenu createRelationshipMenu = new JMenu("Create Relationship");

        JMenuItem rowToColumnItem = new JMenuItem("Row to Column");
        rowToColumnItem.addActionListener(actionEvent -> eventBus.publish(
                new CreateRelationshipRequestedEvent(getSelectedRowElement(),
                        getSelectedColumnElement(), RelationshipDirection.ROW_TO_COLUMN)));

        createRelationshipMenu.add(rowToColumnItem);

        JMenuItem columnToRowItem = new JMenuItem("Column to Row");
        columnToRowItem.addActionListener(actionEvent -> eventBus.publish(
                new CreateRelationshipRequestedEvent(getSelectedRowElement(),
                        getSelectedColumnElement(), RelationshipDirection.COLUMN_TO_ROW)));

        createRelationshipMenu.add(columnToRowItem);

        return createRelationshipMenu;
    }

    INamedElement getSelectedRowElement(){

        int selectedRow = matrixTable.getSelectedRow();

        if (selectedRow != -1 && matrixTable.getModel() instanceof  MatrixTableModel){
            MatrixTableModel model = (MatrixTableModel) matrixTable.getModel();
            return model.getElementByRow(selectedRow);
        }

        return null;
    }

    INamedElement getSelectedColumnElement(){

        int selectedColumn = matrixTable.getSelectedColumn();

        if (selectedColumn != -1 && matrixTable.getModel() instanceof MatrixTableModel){
            MatrixTableModel model = (MatrixTableModel) matrixTable.getModel();
            return model.getElementByColumn(selectedColumn);
        }

        return null;
    }

    private void initActionListeners(){

        rowTypeSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new RowTypeSelectionRequestedEvent());
        });

        rowPackageSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new RowPackageSelectionRequestedEvent());
        });

        columnTypeSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new ColumnTypeSelectionRequestedEvent());
        });

        columnPackageSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new ColumnPackageSelectionRequestedEvent());
        });

        dependencySelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new DependencySelectionRequestedEvent());
        });

        queryButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new QueryRequestedEvent());
        });
    }

    private void initUseCases(){

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

        this.eventBus.subscribe(new EnableQueryButtonUseCase(eventBus, queryButton));

        this.eventBus.subscribe(new CalculateMatrixDataUseCase(eventBus));

        this.eventBus.subscribe(new DisplayMatrixDataUseCase(matrixTable));

        this.eventBus.subscribe(new ShowElementInStructureTreeUseCase(eventBus));

        this.eventBus.subscribe(new CreateRelationUseCase(eventBus));

        this.eventBus.subscribe(new DisplayExceptionUseCase());
    }

    private void fillTableWithDemoData() {

        List<INamedElement> rows = new ArrayList<>();
        rows.add(new DummyNamedElement("Requirement 1"));
        rows.add(new DummyNamedElement("Requirement 2"));
        rows.add(new DummyNamedElement("Requirement 3"));

        List<INamedElement> columns = new ArrayList<>();
        columns.add(new DummyNamedElement("UseCase 1"));
        columns.add(new DummyNamedElement("UseCase 2"));
        columns.add(new DummyNamedElement("UseCase 3"));
        columns.add(new DummyNamedElement("UseCase 4"));
        columns.add(new DummyNamedElement("UseCase 5"));
        columns.add(new DummyNamedElement("UseCase 6"));

        MatrixData data = new MatrixData(rows, columns);

        data.addRelationship(0,1, RelationshipDirection.ROW_TO_COLUMN);
        data.addRelationship(1,2, RelationshipDirection.ROW_TO_COLUMN);
        data.addRelationship(2,0, RelationshipDirection.COLUMN_TO_ROW);
        data.addRelationship(2,1, RelationshipDirection.BOTH);

        MatrixTableModel tableModel = new MatrixTableModel(data);

        this.matrixTable.setModel(tableModel);
    }

}
