package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.HideColumnsRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.TableStructureChangedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class HideColumnsUseCase implements EventHandler<HideColumnsRequestedEvent> {

    private final EventBus eventBus;
    private final JTable table;

    public HideColumnsUseCase(EventBus eventBus, JTable table) {
        this.eventBus = eventBus;
        this.table = table;
    }

    @Override
    public void handleEvent(HideColumnsRequestedEvent event) {
        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.hideColumns(event.selectedColumns());
            eventBus.publish(new TableStructureChangedEvent());
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(HideColumnsRequestedEvent.class);
    }
}
