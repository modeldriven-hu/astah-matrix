package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.core.eventbus.Event;

public class ExceptionOccuredEvent implements Event {

    private final Exception exception;

    public ExceptionOccuredEvent(Exception exception) {
        this.exception = exception;
    }

    public Exception exception() {
        return exception;
    }

}
