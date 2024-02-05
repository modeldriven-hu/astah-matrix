package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.HideColumnsRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.Collections;
import java.util.List;

public class HideColumnsUseCase implements EventHandler<HideColumnsRequestedEvent> {

    private final JTable table;

    public HideColumnsUseCase(JTable table) {
        this.table = table;
    }

    @Override
    public void handleEvent(HideColumnsRequestedEvent event) {
        for (int columnToHide : event.selectedColumns()){
            if (columnToHide > 0){
                TableColumn column = table.getColumnModel().getColumn(columnToHide);
                column.setMinWidth(0);
                column.setMaxWidth(0);
                column.setWidth(0);
            }
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(HideColumnsRequestedEvent.class);
    }
}
