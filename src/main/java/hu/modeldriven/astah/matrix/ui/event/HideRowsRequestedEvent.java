package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.core.eventbus.Event;

public class HideRowsRequestedEvent implements Event {

    private final int[] rows;

    public HideRowsRequestedEvent(int[] rows) {
        this.rows = rows;
    }

    public int[] selectedRows() {
        return rows;
    }

}
