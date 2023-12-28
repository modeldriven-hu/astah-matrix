package hu.modeldriven.astah.matrix.ui.table;

import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.List;

public class TableData {

    private final List<INamedElement> rows;
    private final List<INamedElement> columns;

    private final RelationshipDirection[][] data;

    public TableData(List<INamedElement> rows, List<INamedElement> columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new RelationshipDirection[rows.size()][columns.size()];
    }

    public void addRelationship(int rowCount, int columnCount, RelationshipDirection direction) {
        this.data[rowCount][columnCount] = direction;
    }

    public List<INamedElement> rows() {
        return rows;
    }

    public List<INamedElement> columns() {
        return columns;
    }

    public int rowCount() {
        return this.rows.size();
    }

    public int columnCount() {
        return this.columns.size();
    }

    public RelationshipDirection getRelationship(int row, int column) {
        return this.data[row][column];
    }

    public static enum RelationshipDirection {
        ROW_TO_COLUMN,
        COLUMN_TO_ROW,
        BOTH,
        NONE
    }
}
