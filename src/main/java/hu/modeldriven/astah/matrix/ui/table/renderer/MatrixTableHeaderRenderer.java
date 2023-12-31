package hu.modeldriven.astah.matrix.ui.table.renderer;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class MatrixTableHeaderRenderer extends DefaultTableHeaderCellRenderer {

    private final Color backgroundColor = new Color(230, 230, 230);

    public MatrixTableHeaderRenderer() {
        super();
        super.setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setBackground(backgroundColor);

        Font normalFont = new Font(getFont().getName(), Font.PLAIN, getFont().getSize());
        setFont(normalFont);

        if (table.getColumnModel().getSelectionModel().isSelectedIndex(column)) {
            Font boldFont = new Font(getFont().getName(), Font.BOLD, getFont().getSize());
            setFont(boldFont);
        }

        return this;
    }

}
