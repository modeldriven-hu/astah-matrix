package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.MatrixDataDisplayedEvent;
import hu.modeldriven.astah.matrix.ui.event.TableDataCalculatedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class DisplayMatrixDataUseCase implements EventHandler<TableDataCalculatedEvent> {

    private final EventBus eventBus;
    private final JTable table;

    public DisplayMatrixDataUseCase(EventBus eventBus, JTable table) {
        this.eventBus = eventBus;
        this.table = table;
    }

    @Override
    public void handleEvent(TableDataCalculatedEvent event) {
        MatrixTableModel tableModel = new MatrixTableModel(event.tableData());
        this.table.setModel(tableModel);

        eventBus.publish(new MatrixDataDisplayedEvent());
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(TableDataCalculatedEvent.class);
    }
}
