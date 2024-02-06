package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ShowAllRowsRequestedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class ShowAllRowsUseCase implements EventHandler<ShowAllRowsRequestedEvent> {

    private final JTable table;

    public ShowAllRowsUseCase(JTable table){
        this.table = table;
    }

    @Override
    public void handleEvent(ShowAllRowsRequestedEvent event) {
        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.showAllRows();
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ShowAllRowsRequestedEvent.class);
    }
}
