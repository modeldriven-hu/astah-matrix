package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JTextField;
import java.util.Collections;
import java.util.List;

public class DisplayRowPackageNameUseCase implements EventHandler<RowPackageSelectedEvent> {

    private final JTextField textField;

    public DisplayRowPackageNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(RowPackageSelectedEvent event) {
        this.textField.setText(event.selectedPackage().getName());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(RowPackageSelectedEvent.class);
    }
}
