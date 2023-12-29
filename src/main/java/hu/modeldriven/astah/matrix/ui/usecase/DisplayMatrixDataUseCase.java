package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.TableDataCalculatedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JTable;
import java.util.Collections;
import java.util.List;

public class DisplayMatrixDataUseCase implements EventHandler<TableDataCalculatedEvent> {

    private final JTable table;

    public DisplayMatrixDataUseCase(JTable table){
        this.table = table;
    }

    @Override
    public void handleEvent(TableDataCalculatedEvent event) {
        MatrixTableModel tableModel = new MatrixTableModel(event.getTableData());
        this.table.setModel(tableModel);
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(TableDataCalculatedEvent.class);
    }
}
