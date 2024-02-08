package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.*;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Collections;
import java.util.List;

public class PresentLoadedQueryModelUseCase implements EventHandler<QueryModelLoadedEvent> {

    private final EventBus eventBus;

    public PresentLoadedQueryModelUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(QueryModelLoadedEvent event) {
        QueryModel model = event.queryModel();
        eventBus.publish(new RowPackageSelectedEvent(model.rowPackage()));
        eventBus.publish(new RowTypeSelectedEvent(model.rowType()));
        eventBus.publish(new ColumnPackageSelectedEvent(model.columnPackage()));
        eventBus.publish(new ColumnTypeSelectedEvent(model.columnType()));
        eventBus.publish(new RelationshipSelectedEvent(model.relationship()));
        eventBus.publish(new QueryRequestedEvent());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(QueryModelLoadedEvent.class);
    }
}
