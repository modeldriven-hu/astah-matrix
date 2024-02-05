package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.core.eventbus.Event;

public class HideColumnsRequestedEvent implements Event {

    private final int[] columns;

    public HideColumnsRequestedEvent(int[] columns) {
        this.columns = columns;
    }

    public int[] selectedColumns() {
        return columns;
    }
}
