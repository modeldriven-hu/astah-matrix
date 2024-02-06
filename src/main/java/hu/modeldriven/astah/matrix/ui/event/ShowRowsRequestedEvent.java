package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.core.eventbus.Event;

public class ShowRowsRequestedEvent implements Event {

    private final int[] selectedRows;

    public ShowRowsRequestedEvent(int[] selectedRows) {
        this.selectedRows = selectedRows;
    }

    public int[] selectedRows(){
        return selectedRows;
    }

}
