package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.RelationshipSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class DisplayRelationshipNameUseCase implements EventHandler<RelationshipSelectedEvent> {

    private final JTextField textField;

    public DisplayRelationshipNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(RelationshipSelectedEvent event) {
        this.textField.setText(event.name());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(RelationshipSelectedEvent.class);
    }
}
