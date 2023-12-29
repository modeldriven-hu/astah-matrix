package hu.modeldriven.astah.matrix.ui.table;

import com.change_vision.jude.api.inf.model.INamedElement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Font;

public class NamedElementTableCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setText("");
        setBackground(table.getTableHeader().getBackground());

        if (value instanceof INamedElement) {

            INamedElement namedElement = (INamedElement) value;
            setText(namedElement.getName());

            if (table.getSelectionModel().isSelectedIndex(row)) {
                Font boldFont = new Font(getFont().getName(), Font.BOLD, getFont().getSize());
                setFont(boldFont);
            }
        }
        return this;
    }

}