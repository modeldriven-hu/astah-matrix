package hu.modeldriven.astah.matrix.ui.table.renderer;

import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class RelationshipDirectionCellRenderer extends DefaultTableCellRenderer {

    private final Icon rowToColumnIcon = new ImageIcon(getClass().getResource("/images/row-to-column.png"));
    private final Icon columnToRowIcon = new ImageIcon(getClass().getResource("/images/column-to-row.png"));
    private final Icon bothDirectionIcon = new ImageIcon(getClass().getResource("/images/both-direction.png"));

    private final Color selectionColor = new Color(255, 255, 204);
    private final Color currentSelectionColor = new Color(204, 255, 204);


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setText("");
        setIcon(null);

        setBackgroundColor(table, row, column);
        setDirectionIcon(value);

        return this;
    }

    private void setBackgroundColor(JTable table, int row, int column) {

        this.setBackground(table.getBackground());
        this.setBorder(null);

        boolean isRowSelected = table.getSelectionModel().isSelectedIndex(row);
        boolean isColumnSelected = table.getColumnModel().getSelectionModel().isSelectedIndex(column);

        if (isRowSelected || isColumnSelected) {
            this.setBackground(selectionColor);
        }

        if (isRowSelected && isColumnSelected) {
            this.setBackground(currentSelectionColor);
        }

    }

    private void setDirectionIcon(Object value) {
        if (value instanceof RelationshipDirection) {

            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);

            switch ((RelationshipDirection) value) {
                case ROW_TO_COLUMN:
                    setIcon(rowToColumnIcon);
                    break;
                case COLUMN_TO_ROW:
                    setIcon(columnToRowIcon);
                    break;
                case BOTH:
                    setIcon(bothDirectionIcon);
                    break;
                default:
                    setIcon(null);
                    break;
            }
        }

    }

}
