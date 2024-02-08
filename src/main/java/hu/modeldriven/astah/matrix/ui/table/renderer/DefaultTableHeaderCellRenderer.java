package hu.modeldriven.astah.matrix.ui.table.renderer;

/**
 * @(#)DefaultTableHeaderCellRenderer.java 1.0 02/24/09
 * <p>
 * from the package darrylbu.renderer;
 * <p>
 * Refactored in order to remove Sonar errors by Zsolt Sándor
 */

import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.Component;
import java.util.List;
import java.util.Objects;

/**
 * A default cell renderer for a JTableHeader.
 * <p>
 * DefaultTableHeaderCellRenderer attempts to provide identical behavior to the
 * renderer which the Swing subsystem uses by default, the Sun proprietary
 * class sun.swing.table.DefaultTableCellHeaderRenderer.
 * <p>
 * To apply any desired customization, DefaultTableHeaderCellRenderer may be
 * suitably extended.
 *
 * @author Darryl
 */
public class DefaultTableHeaderCellRenderer extends DefaultTableCellRenderer {

    /**
     * Constructs a <code>DefaultTableHeaderCellRenderer</code>.
     * <p>
     * The horizontal alignment and text position are set as appropriate to a
     * table header cell, and the opaque property is set to false.
     */
    public DefaultTableHeaderCellRenderer() {
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(LEFT);
        setVerticalAlignment(BOTTOM);
        setOpaque(false);
    }

    /**
     * Returns the default table header cell renderer.
     * <p>
     * If the column is sorted, the approapriate icon is retrieved from the
     * current Look and Feel, and a border appropriate to a table header cell
     * is applied.
     * <p>
     * Subclasses may overide this method to provide custom content or
     * formatting.
     *
     * @param table      the <code>JTable</code>.
     * @param value      the value to assign to the header cell
     * @param isSelected This parameter is ignored.
     * @param hasFocus   This parameter is ignored.
     * @param row        This parameter is ignored.
     * @param column     the column of the header cell to render
     * @return the default table header cell renderer
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        setIcon(getIcon(table, column));
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return this;
    }

    /**
     * Overloaded to return an icon suitable to the primary sorted column, or null if
     * the column is not the primary sort key.
     *
     * @param table  the <code>JTable</code>.
     * @param column the column index.
     * @return the sort icon, or null if the column is unsorted.
     */
    protected Icon getIcon(JTable table, int column) {
        SortKey sortKey = getSortKey(table, column);
        if (sortKey != null && table.convertColumnIndexToView(sortKey.getColumn()) == column) {
            if (Objects.requireNonNull(sortKey.getSortOrder()) == SortOrder.ASCENDING) {
                return UIManager.getIcon("Table.ascendingSortIcon");
            } else if (sortKey.getSortOrder() == SortOrder.DESCENDING) {
                return UIManager.getIcon("Table.descendingSortIcon");
            }
        }
        return null;
    }

    /**
     * Returns the current sort key, or null if the column is unsorted.
     *
     * @param table  the table
     * @param column the column index
     * @return the SortKey, or null if the column is unsorted
     */
    protected SortKey getSortKey(JTable table, int column) {
        RowSorter<? extends TableModel> rowSorter = table.getRowSorter();
        if (rowSorter == null) {
            return null;
        }

        List<? extends RowSorter.SortKey> sortedColumns = rowSorter.getSortKeys();
        if (!sortedColumns.isEmpty()) {
            return sortedColumns.get(0);
        }
        return null;
    }
}
