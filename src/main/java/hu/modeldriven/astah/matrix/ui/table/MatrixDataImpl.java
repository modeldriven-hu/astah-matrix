package hu.modeldriven.astah.matrix.ui.table;

import com.change_vision.jude.api.inf.model.INamedElement;

import java.util.List;

public class MatrixDataImpl implements MatrixData {

    private final List<INamedElement> rows;
    private final List<INamedElement> columns;

    private final RelationshipDirection[][] data;

    public MatrixDataImpl(List<INamedElement> rows, List<INamedElement> columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new RelationshipDirection[rows.size()][columns.size()];
    }

    @Override
    public void addRelationship(int rowCount, int columnCount, RelationshipDirection direction) {
        this.data[rowCount][columnCount] = direction;
    }

    @Override
    public List<INamedElement> rows() {
        return rows;
    }

    @Override
    public List<INamedElement> columns() {
        return columns;
    }

    @Override
    public int rowCount() {
        return this.rows.size();
    }

    @Override
    public int columnCount() {
        return this.columns.size();
    }

    @Override
    public RelationshipDirection getRelationship(int row, int column) {
        return this.data[row][column];
    }

}
