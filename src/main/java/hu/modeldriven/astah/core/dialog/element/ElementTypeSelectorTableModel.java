package hu.modeldriven.astah.core.dialog.element;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Optional;

public class ElementTypeSelectorTableModel extends AbstractTableModel {

    private final List<ElementTypeSelectorTableRow> rows;

    public ElementTypeSelectorTableModel(List<ElementTypeSelectorTableRow> rows){
        super();
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    public Optional<ElementTypeSelectorTableRow> selectedRow(){
        return rows.stream().filter(row -> row.selected() == true).findFirst();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return Boolean.class;
            case 1: return String.class;
        }

        return Object.class;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0: return "#";
            case 1: return "Element type";
        }

        return "<undefined>";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                if (aValue instanceof Boolean) {

                    // We set the selection value for each row to false except rowIndex
                    // Then notify the table that all rows have been modified
                    // This is not very optimal for performance but given the small amount
                    // of rows in this table it works well

                    for (int i = 0; i < rows.size(); i++) {
                        ElementTypeSelectorTableRow currentRow = rows.get(i);

                        if (rowIndex == i) {
                            currentRow.setSelected((Boolean) aValue);
                        } else {
                            currentRow.setSelected(false);
                        }
                    }

                    fireTableRowsUpdated(0, rows.size()-1);
                }
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ElementTypeSelectorTableRow selectedTableRow = rows.get(rowIndex);

        switch (columnIndex){

            case 0:
                return selectedTableRow.selected();

            case 1:
                return selectedTableRow.name();

        }

        return null;
    }
}
