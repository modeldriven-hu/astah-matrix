package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.model.IRealization;
import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;
import hu.modeldriven.astah.matrix.ui.event.*;
import hu.modeldriven.astah.matrix.ui.table.TableData;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculateMatrixDataUseCase implements EventHandler {

    private final EventBus eventBus;
    private final QueryInfo queryInfo;

    public CalculateMatrixDataUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
        this.queryInfo = new QueryInfo();
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof ColumnPackageSelectedEvent) {
            queryInfo.columnPackage = ((ColumnPackageSelectedEvent) event).selectedPackage();
        }

        if (event instanceof ColumnTypeSelectedEvent) {
            queryInfo.columnTypeMatcher = ((ColumnTypeSelectedEvent) event).elementMatcher();
        }

        if (event instanceof RowPackageSelectedEvent) {
            queryInfo.rowPackage = ((RowPackageSelectedEvent) event).selectedPackage();
        }

        if (event instanceof RowTypeSelectedEvent) {
            queryInfo.rowTypeMatcher = ((RowTypeSelectedEvent) event).elementMatcher();
        }

        if (event instanceof DependencyTypeSelectedEvent) {
            queryInfo.dependencyTypeMatcher = ((DependencyTypeSelectedEvent) event).elementMatcher();
        }

        if (event instanceof QueryRequestedEvent) {
            createMatrix();
        }

    }

    private void createMatrix() {
        List<INamedElement> rows = findElements(queryInfo.rowPackage, queryInfo.rowTypeMatcher);
        List<INamedElement> columns = findElements(queryInfo.columnPackage, queryInfo.columnTypeMatcher);

        TableData tableData = new TableData(rows, columns);

        for (int row = 0; row < rows.size(); row++) {

            INamedElement rowElement = rows.get(row);

            for (int column = 0; column < columns.size(); column++) {
                INamedElement columnElement = columns.get(column);
                tableData.addRelationship(row, column, getRelationshipDirection(rowElement, columnElement));
            }
        }

        eventBus.publish(new TableDataCalculatedEvent(tableData));
    }

    private TableData.RelationshipDirection getRelationshipDirection(INamedElement row, INamedElement column) {

        // Client -> Supplier

        boolean rowToColumn = false;

        for (IDependency dependency : row.getClientDependencies()) {
            if (queryInfo.columnTypeMatcher.matches(dependency)) {
                if (dependency.getSupplier().equals(column)) {
                    rowToColumn = true;
                }
            }
        }

        for (IRealization realization : row.getClientRealizations()) {
            if (queryInfo.columnTypeMatcher.matches(realization)) {
                if (realization.getSupplier().equals(column)) {
                    rowToColumn = true;
                }
            }
        }

        boolean columnToRow = false;

        for (IDependency dependency : row.getSupplierDependencies()) {
            if (queryInfo.columnTypeMatcher.matches(dependency)) {
                if (dependency.getClient().equals(column)) {
                    columnToRow = true;
                }
            }
        }

        for (IRealization realization : row.getSupplierRealizations()) {
            if (queryInfo.columnTypeMatcher.matches(realization)) {
                if (realization.getClient().equals(column)) {
                    columnToRow = true;
                }
            }
        }

        if (rowToColumn && columnToRow) {
            return TableData.RelationshipDirection.BOTH;
        } else if (rowToColumn) {
            return TableData.RelationshipDirection.ROW_TO_COLUMN;
        } else if (columnToRow) {
            return TableData.RelationshipDirection.COLUMN_TO_ROW;
        } else {
            return TableData.RelationshipDirection.NONE;
        }
    }

    private List<INamedElement> findElements(IPackage rowPackage, TypeMatcher rowTypeMatcher) {
        List<INamedElement> elements = new ArrayList<>();

        for (INamedElement element : rowPackage.getOwnedElements()) {
            if (element instanceof IPackage) {
                elements.addAll(findElements((IPackage) element, rowTypeMatcher));
            } else {
                if (rowTypeMatcher.matches(element)) {
                    elements.add(element);
                }
            }
        }

        return elements;
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(
                QueryRequestedEvent.class,
                ColumnPackageSelectedEvent.class,
                ColumnTypeSelectedEvent.class,
                RowPackageSelectedEvent.class,
                RowTypeSelectedEvent.class,
                DependencyTypeSelectedEvent.class);
    }


    class QueryInfo {
        IPackage columnPackage;
        TypeMatcher columnTypeMatcher;

        IPackage rowPackage;
        TypeMatcher rowTypeMatcher;

        TypeMatcher dependencyTypeMatcher;
    }

}
