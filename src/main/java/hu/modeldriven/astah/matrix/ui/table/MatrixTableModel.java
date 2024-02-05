package hu.modeldriven.astah.matrix.ui.table;

import com.change_vision.jude.api.inf.model.INamedElement;

import javax.swing.table.AbstractTableModel;
import java.util.Set;
import java.util.TreeSet;

public class MatrixTableModel extends AbstractTableModel {

    private final transient MatrixData tableData;

    private Set<Integer> hiddenRows = new TreeSet<>();

    public MatrixTableModel(MatrixData tableData) {
        this.tableData = tableData;
    }

    public INamedElement getElementByRow(int row) {
        return tableData.rows().get(calculateRealFromVisibleRow(row));
    }

    public INamedElement getElementByColumn(int column) {
        return tableData.columns().get(column - 1);
    }

    @Override
    public int getRowCount() {
        return this.tableData.rowCount() - this.hiddenRows.size();
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
            return this.tableData.rows().get(calculateRealFromVisibleRow(row));
        } else {
            return this.tableData.getRelationship(calculateRealFromVisibleRow(row), column - 1);
        }
    }

    private int calculateRealFromVisibleRow(int visibleRow){

        int currentRow = 0;

        for (int i = 0; i < this.tableData.rowCount(); i++) {

            if (hiddenRows.contains(i)){
                continue;
            }

            if (currentRow == visibleRow){
                return i;
            }

            currentRow++;
        }

        return -1;
    }

    public void hideRows(int[] rows) {
        for (int rowToHide : rows) {
            hiddenRows.add(rowToHide);
        }

        fireTableStructureChanged();
    }
}
