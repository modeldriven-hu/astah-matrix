package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.core.dialog.type.ElementTypeSelectorData;
import hu.modeldriven.astah.core.dialog.type.TypeSelectorDialog;
import hu.modeldriven.astah.matrix.ui.event.ColumnTypeSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.ColumnTypeSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;

public class DisplayColumnTypeSelectorUseCase implements EventHandler<ColumnTypeSelectionRequestedEvent> {

    private final Component parentComponent;
    private final EventBus eventBus;

    public DisplayColumnTypeSelectorUseCase(Component parentComponent, EventBus eventBus) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(ColumnTypeSelectionRequestedEvent event) {
        TypeSelectorDialog dialog = new TypeSelectorDialog(
                parentComponent,
                new ElementTypeSelectorData(),
                es -> eventBus.publish(
                        new ColumnTypeSelectedEvent(
                                es.name(),
                                es.matcher()
                        ))
        );

        dialog.show();
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ColumnTypeSelectionRequestedEvent.class);
    }
}