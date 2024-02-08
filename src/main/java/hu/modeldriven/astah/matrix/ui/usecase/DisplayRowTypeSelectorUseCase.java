package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.core.dialog.typeselector.SelectedType;
import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.dialog.typeselector.TypeSelectorDialog;
import hu.modeldriven.astah.core.dialog.typeselector.element.ElementRepository;
import hu.modeldriven.astah.core.dialog.typeselector.element.StereotypedType;
import hu.modeldriven.astah.matrix.ui.event.RowTypeSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowTypeSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayRowTypeSelectorUseCase implements EventHandler<RowTypeSelectionRequestedEvent>, Consumer<SelectedType> {

    private final Component parentComponent;
    private final EventBus eventBus;

    public DisplayRowTypeSelectorUseCase(Component parentComponent, EventBus eventBus) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(RowTypeSelectionRequestedEvent event) {
        TypeSelectorDialog dialog = new TypeSelectorDialog(
                parentComponent,
                new ElementRepository(),
                this);

        dialog.show();
    }


    @Override
    public void accept(SelectedType selectedType) {

        Type type = selectedType.type();

        if (selectedType.hasStereotype()) {
            type = new StereotypedType(type, selectedType.stereotype());
        }

        eventBus.publish(new RowTypeSelectedEvent(type));
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(RowTypeSelectionRequestedEvent.class);
    }

}
