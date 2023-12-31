package hu.modeldriven.astah.matrix.ui.event;

import hu.modeldriven.astah.matrix.ui.table.MatrixData;
import hu.modeldriven.core.eventbus.Event;

public class TableDataCalculatedEvent implements Event {

    private final MatrixData tableData;

    public TableDataCalculatedEvent(MatrixData tableData) {
        this.tableData = tableData;
    }

    public MatrixData getTableData() {
        return tableData;
    }
}
