package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.MatrixDataDisplayedEvent;
import hu.modeldriven.astah.matrix.ui.event.QueryModelChangedEvent;
import hu.modeldriven.astah.matrix.ui.event.ResetRequestedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class SetExportButtonStatusUseCase implements EventHandler<Event> {

    private final JButton exportButton;

    public SetExportButtonStatusUseCase(JButton exportButton) {
        this.exportButton = exportButton;
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof ResetRequestedEvent) {
            exportButton.setEnabled(false);
        }

        if (event instanceof MatrixDataDisplayedEvent) {
            exportButton.setEnabled(true);
        }

    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(ResetRequestedEvent.class, MatrixDataDisplayedEvent.class);
    }

}
