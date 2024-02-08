package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.HideRowsRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.TableStructureChangedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class HideRowsUseCase implements EventHandler<HideRowsRequestedEvent> {

    private final EventBus eventBus;
    private final JTable table;

    public HideRowsUseCase(EventBus eventBus, JTable table) {
        this.eventBus = eventBus;
        this.table = table;
    }

    @Override
    public void handleEvent(HideRowsRequestedEvent event) {

        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.hideRows(event.selectedRows());
            eventBus.publish(new TableStructureChangedEvent());
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(HideRowsRequestedEvent.class);
    }

}
