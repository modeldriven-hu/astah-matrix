package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.matrix.ui.table.TableData;
import hu.modeldriven.core.eventbus.Event;

public class TableDataCalculatedEvent implements Event {

    private final TableData tableData;

    public TableDataCalculatedEvent(TableData tableData){
        this.tableData = tableData;
    }

    public TableData getTableData() {
        return tableData;
    }
}
