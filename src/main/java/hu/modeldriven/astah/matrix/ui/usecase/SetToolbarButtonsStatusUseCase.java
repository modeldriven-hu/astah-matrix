package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.QueryModelChangedEvent;
import hu.modeldriven.astah.matrix.ui.event.ResetRequestedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class SetToolbarButtonsStatusUseCase implements EventHandler<Event> {

    private final List<JButton> buttons;

    public SetToolbarButtonsStatusUseCase(JButton ... buttons) {
        this.buttons = Arrays.asList(buttons);
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof ResetRequestedEvent) {
            buttons.forEach(button -> button.setEnabled(false));
        }

        if (event instanceof QueryModelChangedEvent) {
            QueryModel model = ((QueryModelChangedEvent) event).queryModel();

            if (model.isAllDataProvided()) {
                buttons.forEach(button -> button.setEnabled(true));
            }
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(QueryModelChangedEvent.class, ResetRequestedEvent.class);
    }

}
