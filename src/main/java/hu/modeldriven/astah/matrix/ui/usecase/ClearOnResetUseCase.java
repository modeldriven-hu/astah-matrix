package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ResetRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.List;

public class ClearOnResetUseCase implements EventHandler<ResetRequestedEvent> {

    private final JTable table;
    private final List<JTextField> textFields;

    public ClearOnResetUseCase(JTable table, List<JTextField> textFields) {
        this.table = table;
        this.textFields = textFields;
    }

    @Override
    public void handleEvent(ResetRequestedEvent event) {
        for (JTextField textField : this.textFields) {
            textField.setText("");
        }

        this.table.setModel(new DefaultTableModel());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ResetRequestedEvent.class);
    }
}
