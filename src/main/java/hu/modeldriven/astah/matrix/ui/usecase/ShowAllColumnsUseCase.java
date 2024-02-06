package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ShowAllColumnsRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.ShowAllRowsRequestedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class ShowAllColumnsUseCase implements EventHandler<ShowAllColumnsRequestedEvent> {

    private final JTable table;

    public ShowAllColumnsUseCase(JTable table){
        this.table = table;
    }

    @Override
    public void handleEvent(ShowAllColumnsRequestedEvent event) {
        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.showAllColumns();
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ShowAllColumnsRequestedEvent.class);
    }
}
