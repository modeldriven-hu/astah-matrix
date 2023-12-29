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
import hu.modeldriven.astah.matrix.ui.usecase.*;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
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
        initUseCases();
    }

    private void initComponents() {

        this.matrixTable.getTableHeader().setResizingAllowed(true);

        ListSelectionListener listener = e -> matrixTable.repaint();
        matrixTable.getSelectionModel().addListSelectionListener(listener);
        matrixTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

        this.matrixTable.setDefaultRenderer(INamedElement.class, new NamedElementTableCellRenderer());
        this.matrixTable.setDefaultRenderer(RelationshipDirection.class, new RelationshipTableCellRenderer());

        fillTableWithDemoData();
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

        TableData data = new TableData(rows, columns);

        data.addRelationship(0,1, RelationshipDirection.ROW_TO_COLUMN);
        data.addRelationship(1,2, RelationshipDirection.ROW_TO_COLUMN);
        data.addRelationship(2,0, RelationshipDirection.COLUMN_TO_ROW);
        data.addRelationship(2,1, RelationshipDirection.BOTH);

        RelationshipTableModel tableModel = new RelationshipTableModel(data);

        this.matrixTable.setModel(tableModel);
    }

}
