package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.Relationship;
import hu.modeldriven.astah.matrix.ui.event.*;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Arrays;
import java.util.List;

public class BuildQueryModelUseCase implements EventHandler<Event> {

    private final EventBus eventBus;

    private final QueryInformation queryInformation;

    public BuildQueryModelUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
        this.queryInformation = new QueryInformation();
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof ResetRequestedEvent) {
            this.queryInformation.clear();
        }

        if (event instanceof ColumnPackageSelectedEvent) {
            queryInformation.columnPackage = ((ColumnPackageSelectedEvent) event).selectedPackage();
        }

        if (event instanceof ColumnTypeSelectedEvent) {
            queryInformation.columnType = ((ColumnTypeSelectedEvent) event).type();
        }

        if (event instanceof RowPackageSelectedEvent) {
            queryInformation.rowPackage = ((RowPackageSelectedEvent) event).selectedPackage();
        }

        if (event instanceof RowTypeSelectedEvent) {
            queryInformation.rowType = ((RowTypeSelectedEvent) event).type();
        }

        if (event instanceof RelationshipSelectedEvent) {
            queryInformation.relationship = ((RelationshipSelectedEvent) event).relationship();
        }

        eventBus.publish(queryInformation.asEvent());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(
                ColumnPackageSelectedEvent.class,
                ColumnTypeSelectedEvent.class,
                RowPackageSelectedEvent.class,
                RowTypeSelectedEvent.class,
                RelationshipSelectedEvent.class,
                ResetRequestedEvent.class
        );
    }

    private static class QueryInformation {

        IPackage columnPackage;
        IPackage rowPackage;

        Type rowType;

        Type columnType;

        Relationship relationship;

        public QueryModelChangedEvent asEvent() {
            return new QueryModelChangedEvent(
                    rowPackage,
                    rowType,
                    columnPackage,
                    columnType,
                    relationship);
        }

        public void clear() {
            this.rowPackage = null;
            this.rowType = null;
            this.columnPackage = null;
            this.columnType = null;
            this.relationship = null;
        }
    }

}
