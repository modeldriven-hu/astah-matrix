package hu.modeldriven.astah.core.dialog.typeselector;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TypeSelectorTableModel extends AbstractTableModel {

    private final transient List<SelectableType> rows;

    public TypeSelectorTableModel(TypeRepository repository) {
        super();
        this.rows = createRows(repository);
    }

    private List<SelectableType> createRows(TypeRepository repository) {
        return repository.types().stream()
                .map(SelectableTypeImpl::new)
                .collect(Collectors.toList());
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    public Optional<Type> selectedRow() {
        return rows.stream().filter(SelectableType::selected).map(SelectableType::type).findFirst();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            default:
                return Object.class;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "#";
            case 1:
                return "Element type";
            default:
                return "<undefined>";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (columnIndex == 0 && aValue instanceof Boolean) {

            // We set the selection value for each row to false except rowIndex
            // Then notify the table that all rows have been modified
            // This is not very optimal for performance but given the small amount
            // of rows in this table it works well

            for (int i = 0; i < rows.size(); i++) {
                SelectableType currentRow = rows.get(i);

                if (rowIndex == i) {
                    currentRow.setSelected((Boolean) aValue);
                } else {
                    currentRow.setSelected(false);
                }
            }

            fireTableRowsUpdated(0, rows.size() - 1);
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        SelectableType selectedTableRow = rows.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return selectedTableRow.selected();

            case 1:
                return selectedTableRow.type().name();

            default:
                return null;

        }
    }
}
