package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ColumnTypeSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class DisplayColumnTypeNameUseCase implements EventHandler<ColumnTypeSelectedEvent> {

    private final JTextField textField;

    public DisplayColumnTypeNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(ColumnTypeSelectedEvent event) {
        this.textField.setText(event.name());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ColumnTypeSelectedEvent.class);
    }
}
