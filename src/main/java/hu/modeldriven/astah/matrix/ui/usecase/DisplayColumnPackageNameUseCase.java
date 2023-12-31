package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ColumnPackageSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JTextField;
import java.util.Collections;
import java.util.List;

public class DisplayColumnPackageNameUseCase implements EventHandler<ColumnPackageSelectedEvent> {

    private final JTextField textField;

    public DisplayColumnPackageNameUseCase(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void handleEvent(ColumnPackageSelectedEvent event) {
        this.textField.setText(event.selectedPackage().getName());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ColumnPackageSelectedEvent.class);
    }
}
