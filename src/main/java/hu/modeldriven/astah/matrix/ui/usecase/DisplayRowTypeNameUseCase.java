package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.RowTypeSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class DisplayRowTypeNameUseCase implements EventHandler<RowTypeSelectedEvent> {

    private final JTextField textField;

    public DisplayRowTypeNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(RowTypeSelectedEvent event) {
        this.textField.setText(event.name());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(RowTypeSelectedEvent.class);
    }
}
