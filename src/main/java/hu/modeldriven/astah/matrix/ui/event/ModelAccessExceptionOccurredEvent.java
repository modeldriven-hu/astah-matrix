package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.core.representation.ModelAccessException;
import hu.modeldriven.core.eventbus.Event;

public class ModelAccessExceptionOccurredEvent implements Event {

    private final ModelAccessException exception;

    public ModelAccessExceptionOccurredEvent(ModelAccessException exception) {
        this.exception = exception;
    }

    public ModelAccessException exception() {
        return exception;
    }
}
