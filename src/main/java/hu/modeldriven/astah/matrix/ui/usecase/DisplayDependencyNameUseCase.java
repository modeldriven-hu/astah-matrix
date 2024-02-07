package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.DependencyTypeSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class DisplayDependencyNameUseCase implements EventHandler<DependencyTypeSelectedEvent> {

    private final JTextField textField;

    public DisplayDependencyNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(DependencyTypeSelectedEvent event) {
        this.textField.setText(event.name());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(DependencyTypeSelectedEvent.class);
    }
}
