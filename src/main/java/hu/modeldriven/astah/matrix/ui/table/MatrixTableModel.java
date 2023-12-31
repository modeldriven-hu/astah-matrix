package hu.modeldriven.astah.matrix.ui.table;

import com.change_vision.jude.api.inf.model.INamedElement;

import javax.swing.table.AbstractTableModel;

public class MatrixTableModel extends AbstractTableModel {

    private final MatrixData tableData;

    public MatrixTableModel(MatrixData tableData) {
        this.tableData = tableData;
    }

    public INamedElement getElementByRow(int row) {
        return tableData.rows().get(row);
    }

    public INamedElement getElementByColumn(int column) {
        return tableData.columns().get(column - 1);
    }

    @Override
    public int getRowCount() {
        return this.tableData.rowCount();
    }

    @Override
    public int getColumnCount() {
        return this.tableData.columnCount() + 1;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "";
        } else {
            return this.tableData.columns().get(column - 1).getName();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 0) {
            return INamedElement.class;
        } else {
            return RelationshipDirection.class;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column == 0) {
            return this.tableData.rows().get(row);
        } else {
            return this.tableData.getRelationship(row, column - 1);
        }
    }
}
