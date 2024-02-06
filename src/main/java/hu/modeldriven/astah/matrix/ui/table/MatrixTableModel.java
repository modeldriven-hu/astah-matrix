package hu.modeldriven.astah.matrix.ui.table;

import com.change_vision.jude.api.inf.model.INamedElement;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class MatrixTableModel extends AbstractTableModel {

    private final transient MatrixData tableData;

    private final Set<Integer> hiddenRows = new TreeSet<>();
    private final Set<Integer> hiddenColumns = new TreeSet<>();

    public MatrixTableModel(MatrixData tableData) {
        this.tableData = tableData;
    }

    public INamedElement getElementByRow(int row) {
        return tableData.rows().get(calculateRowIndexFromVisibleToReal(row));
    }

    public INamedElement getElementByColumn(int column) {
        return tableData.columns().get(calculateColumnIndexFromVisibleToReal(column - 1));
    }

    @Override
    public int getRowCount() {
        return this.tableData.rowCount() - this.hiddenRows.size();
    }

    @Override
    public int getColumnCount() {
        return this.tableData.columnCount() + 1 - this.hiddenColumns.size();
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "";
        } else {
            return this.tableData.columns().get(calculateColumnIndexFromVisibleToReal(column - 1)).getName();
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
            return this.tableData.rows().get(calculateRowIndexFromVisibleToReal(row));
        } else {
            return this.tableData.getRelationship(
                    calculateRowIndexFromVisibleToReal(row),
                    calculateColumnIndexFromVisibleToReal(column - 1)
            );
        }
    }

    private int calculateColumnIndexFromVisibleToReal(int visibleColumns) {
        return calculateIndexFromVisibleToReal(visibleColumns, tableData.columnCount(), hiddenColumns);
    }

    private int calculateRowIndexFromVisibleToReal(int visibleRow) {
        return calculateIndexFromVisibleToReal(visibleRow, tableData.rowCount(), hiddenRows);
    }

    private int calculateIndexFromVisibleToReal(int visibleElementIndex, int maxElements, Set<Integer> hiddenElements) {

        int current = 0;

        for (int i = 0; i < maxElements; i++) {

            if (hiddenElements.contains(i)) {
                continue;
            }

            if (current == visibleElementIndex) {
                return i;
            }

            current++;
        }

        return -1;
    }

    public void hideRows(int[] visibleRowIndexes) {

        // Because we are doing a visible to real index calculation, we need to
        // go in a reverse order

        Arrays.sort(visibleRowIndexes);

        for (int index = visibleRowIndexes.length - 1; index >= 0; index--) {
            int rowToHide = visibleRowIndexes[index];
            hiddenRows.add(calculateRowIndexFromVisibleToReal(rowToHide));
        }

        fireTableStructureChanged();
    }

    public void hideColumns(int[] visibleColumnIndexes) {

        // Because we are doing a visible to real index calculation, we need to
        // go in a reverse order

        Arrays.sort(visibleColumnIndexes);

        for (int index = visibleColumnIndexes.length - 1; index >= 0; index--) {
            int columnToHide = visibleColumnIndexes[index];
            hiddenColumns.add(calculateColumnIndexFromVisibleToReal(columnToHide - 1));
        }

        fireTableStructureChanged();
    }

    public void showAllRows() {
        this.hiddenRows.clear();
        fireTableStructureChanged();
    }

    public void showAllColumns() {
        this.hiddenRows.clear();
        fireTableStructureChanged();
    }

    public void showRows(int[] indexes) {
        
    }

    public void showColumns(int[] indexes) {
    }
}
