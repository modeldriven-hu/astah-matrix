package hu.modeldriven.astah.matrix.ui.table.renderer;

import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class RelationshipDirectionCellRenderer extends DefaultTableCellRenderer {

    private final Icon rowToColumnIcon = new ImageIcon(getClass().getResource("/images/row-to-column.png"));
    private final Icon columnToRowIcon = new ImageIcon(getClass().getResource("/images/column-to-row.png"));
    private final Icon bothDirectionIcon = new ImageIcon(getClass().getResource("/images/both-direction.png"));

    private final Color selectionColor = new Color(173, 216, 230);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setText("");
        setIcon(null);

        setBackgroundColor(table, row, column, isSelected);
        setDirectionIcon(value);

        return this;
    }

    private void setBackgroundColor(JTable table, int row, int column, boolean isSelected) {

        this.setBackground(table.getBackground());

        if (isSelected) {
            this.setBackground(selectionColor); // Light blue color
        } else {
            boolean isRowSelected = table.getSelectionModel().isSelectedIndex(row);
            boolean isColumnSelected = table.getColumnModel().getSelectionModel().isSelectedIndex(column);

            if (isRowSelected || isColumnSelected) {
                this.setBackground(selectionColor);
            }
        }
    }

    private void setDirectionIcon(Object value) {
        if (value != null && value instanceof RelationshipDirection) {

            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);

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
