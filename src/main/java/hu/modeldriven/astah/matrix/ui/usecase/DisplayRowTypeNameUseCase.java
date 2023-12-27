package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.RowElementTypeSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JTextField;
import java.util.Collections;
import java.util.List;

public class DisplayRowTypeNameUseCase implements EventHandler<RowElementTypeSelectedEvent> {

    private final JTextField textField;

    public DisplayRowTypeNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(RowElementTypeSelectedEvent e) {
        this.textField.setText(e.name());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(RowElementTypeSelectedEvent.class);
    }
}
