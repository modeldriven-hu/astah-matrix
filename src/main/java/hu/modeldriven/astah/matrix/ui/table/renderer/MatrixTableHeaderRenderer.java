package hu.modeldriven.astah.matrix.ui.table.renderer;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Rectangle2D;

public class MatrixTableHeaderRenderer extends DefaultTableHeaderCellRenderer {

    private final Color backgroundColor = new Color(230, 230, 230);

    public MatrixTableHeaderRenderer() {
        super();
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setBackground(backgroundColor);

        final Font font;

        if (table.getColumnModel().getSelectionModel().isSelectedIndex(column)) {
            font = new Font(getFont().getName(), Font.BOLD, getFont().getSize());
        } else {
            font = new Font(getFont().getName(), Font.PLAIN, getFont().getSize());
        }

        setFont(font);

        Rectangle2D size = new TextMeasure(getText(), font, true).getBounds();
        setPreferredSize(new Dimension((int) size.getWidth() + 30, (int) size.getHeight()));

        return this;
    }

}
