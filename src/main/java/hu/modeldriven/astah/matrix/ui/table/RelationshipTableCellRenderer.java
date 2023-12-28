package hu.modeldriven.astah.matrix.ui.table;

import hu.modeldriven.astah.matrix.ui.table.TableData.RelationshipDirection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class RelationshipTableCellRenderer extends DefaultTableCellRenderer {

    private final Icon rowToColumnIcon = new ImageIcon(getClass().getResource("/images/row-to-column.png"));
    private final Icon columnToRowIcon = new ImageIcon(getClass().getResource("/images/column-to-row.png"));
    private final Icon bothDirectionIcon = new ImageIcon(getClass().getResource("/images/both-direction.png"));

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setText("");
        setIcon(null);

        if (value != null && value instanceof RelationshipDirection) {

            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);

            switch ((RelationshipDirection)value){
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

        return this;
    }
}
