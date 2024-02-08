package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.core.dialog.typeselector.SelectedType;
import hu.modeldriven.astah.core.dialog.typeselector.TypeSelectorDialog;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.Relationship;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.RelationshipRepository;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.StereotypedRelationship;
import hu.modeldriven.astah.matrix.ui.event.DependencySelectionRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.RelationshipSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayRelationshipSelectorUseCase implements EventHandler<DependencySelectionRequestedEvent>,
        Consumer<SelectedType> {

    private final Component parentComponent;
    private final EventBus eventBus;

    public DisplayRelationshipSelectorUseCase(Component parentComponent, EventBus eventBus) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(DependencySelectionRequestedEvent event) {
        TypeSelectorDialog dialog = new TypeSelectorDialog(
                parentComponent,
                new RelationshipRepository(),
                this
        );

        dialog.show();
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(DependencySelectionRequestedEvent.class);
    }

    @Override
    public void accept(SelectedType selectedType) {

        Relationship relationship = (Relationship) selectedType.type();

        final RelationshipSelectedEvent event;

        if (selectedType.hasStereotype()) {
            event = new RelationshipSelectedEvent(new StereotypedRelationship(relationship, selectedType.stereotype()));
        } else {
            event = new RelationshipSelectedEvent(relationship);
        }

        eventBus.publish(event);
    }
}