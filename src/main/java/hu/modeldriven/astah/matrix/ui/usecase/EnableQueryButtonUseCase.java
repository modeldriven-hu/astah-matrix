package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.*;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnableQueryButtonUseCase implements EventHandler<Event> {

    private final JButton button;
    private final Map<Class<?>, Boolean> selectedMap;

    public EnableQueryButtonUseCase(JButton button) {
        this.button = button;
        this.selectedMap = new HashMap<>();
    }

    @Override
    public void handleEvent(Event event) {

        for (Class<? extends Event> clazz : subscribedEvents()) {
            if (clazz.isInstance(event)) {
                this.selectedMap.put(clazz, true);
            }
        }

        int eventCount = this.selectedMap.keySet().size();

        if (eventCount == subscribedEvents().size()) {
            this.button.setEnabled(true);
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(
                ColumnPackageSelectedEvent.class,
                ColumnTypeSelectedEvent.class,
                RowPackageSelectedEvent.class,
                RowTypeSelectedEvent.class,
                DependencyTypeSelectedEvent.class);
    }
}
