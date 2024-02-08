package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.QueryModelChangedEvent;
import hu.modeldriven.astah.matrix.ui.event.ResetRequestedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class SetQueryButtonStatusUseCase implements EventHandler<Event> {

    private final JButton queryButton;

    public SetQueryButtonStatusUseCase(JButton queryButton) {
        this.queryButton = queryButton;
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof ResetRequestedEvent) {
            this.queryButton.setEnabled(false);
        }

        if (event instanceof QueryModelChangedEvent) {
            QueryModel model = ((QueryModelChangedEvent) event).queryModel();

            if (model.isAllDataProvided()) {
                this.queryButton.setEnabled(true);
            }
        }
    }


    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(QueryModelChangedEvent.class, ResetRequestedEvent.class);
    }

}
