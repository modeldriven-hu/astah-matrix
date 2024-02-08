package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.matcher.TypeMatcher;
import hu.modeldriven.astah.matrix.ui.event.QueryModelChangedEvent;
import hu.modeldriven.astah.matrix.ui.event.QueryRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.TableDataCalculatedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixData;
import hu.modeldriven.astah.matrix.ui.table.MatrixDataImpl;
import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculateMatrixDataUseCase implements EventHandler<Event> {

    private final EventBus eventBus;
    private QueryModel queryModel;

    public CalculateMatrixDataUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
        this.queryModel = null;
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof QueryModelChangedEvent) {
            this.queryModel = ((QueryModelChangedEvent) event).queryModel();
        }

        if (event instanceof QueryRequestedEvent && queryModel != null && queryModel.isAllDataProvided()) {
            createMatrix();
        }

    }

    private void createMatrix() {
        List<INamedElement> rows = findElements(queryModel.rowPackage(), queryModel.rowType().matcher());
        List<INamedElement> columns = findElements(queryModel.columnPackage(), queryModel.columnType().matcher());

        MatrixData tableData = new MatrixDataImpl(rows, columns);

        for (int row = 0; row < rows.size(); row++) {

            INamedElement rowElement = rows.get(row);

            for (int column = 0; column < columns.size(); column++) {
                INamedElement columnElement = columns.get(column);
                tableData.addRelationship(row, column, getRelationshipDirection(rowElement, columnElement));
            }
        }

        eventBus.publish(new TableDataCalculatedEvent(tableData));
    }

    private RelationshipDirection getRelationshipDirection(INamedElement row, INamedElement column) {

        TypeMatcher typeMatcher = queryModel.relationship().matcher();

        boolean rowToColumn = Arrays.stream(row.getClientDependencies())
                .anyMatch(dependency -> typeMatcher.matches(dependency) && dependency.getSupplier().equals(column))
                ||
                Arrays.stream(row.getClientRealizations())
                        .anyMatch(realization -> typeMatcher.matches(realization) && realization.getSupplier().equals(column));

        boolean columnToRow = Arrays.stream(row.getSupplierDependencies())
                .anyMatch(dependency -> typeMatcher.matches(dependency) && dependency.getClient().equals(column))
                ||
                Arrays.stream(row.getSupplierRealizations())
                        .anyMatch(realization -> typeMatcher.matches(realization) && realization.getClient().equals(column));

        if (rowToColumn && columnToRow) {
            return RelationshipDirection.BOTH;
        } else if (rowToColumn) {
            return RelationshipDirection.ROW_TO_COLUMN;
        } else if (columnToRow) {
            return RelationshipDirection.COLUMN_TO_ROW;
        } else {
            return RelationshipDirection.NONE;
        }
    }

    private List<INamedElement> findElements(IPackage rowPackage, TypeMatcher rowTypeMatcher) {
        List<INamedElement> elements = new ArrayList<>();

        for (INamedElement element : rowPackage.getOwnedElements()) {
            if (element instanceof IPackage) {
                elements.addAll(findElements((IPackage) element, rowTypeMatcher));
            } else if (rowTypeMatcher.matches(element)) {
                elements.add(element);
            }
        }

        return elements;
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(QueryRequestedEvent.class, QueryModelChangedEvent.class);
    }

}
