package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ColumnTypeSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowTypeSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JTextField;
import java.util.Collections;
import java.util.List;

public class DisplayColumnTypeNameUseCase implements EventHandler<ColumnTypeSelectedEvent> {

    private final JTextField textField;

    public DisplayColumnTypeNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(ColumnTypeSelectedEvent e) {
        this.textField.setText(e.name());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ColumnTypeSelectedEvent.class);
    }
}
