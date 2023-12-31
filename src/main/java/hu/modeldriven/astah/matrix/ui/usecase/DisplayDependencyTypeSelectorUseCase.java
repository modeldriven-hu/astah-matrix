package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.core.dialog.type.DependencyTypeSelectorData;
import hu.modeldriven.astah.core.dialog.type.TypeSelector;
import hu.modeldriven.astah.core.dialog.type.TypeSelectorDialog;
import hu.modeldriven.astah.matrix.ui.event.DependencySelectionRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.DependencyTypeSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayDependencyTypeSelectorUseCase implements EventHandler<DependencySelectionRequestedEvent>,
        Consumer<TypeSelector> {

    private final Component parentComponent;
    private final EventBus eventBus;

    public DisplayDependencyTypeSelectorUseCase(Component parentComponent, EventBus eventBus) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(DependencySelectionRequestedEvent event) {
        TypeSelectorDialog dialog = new TypeSelectorDialog(
                parentComponent,
                new DependencyTypeSelectorData(),
                this
        );

        dialog.show();
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(DependencySelectionRequestedEvent.class);
    }

    @Override
    public void accept(TypeSelector typeSelector) {
        eventBus.publish(new DependencyTypeSelectedEvent(
                typeSelector
        ));
    }
}