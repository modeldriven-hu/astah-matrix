package hu.modeldriven.astah.matrix.ui.table.renderer;

import com.change_vision.jude.api.inf.model.INamedElement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

public class NamedElementCellRenderer extends DefaultTableCellRenderer {

    private final Color backgroundColor = new Color(230, 230, 230);

    public NamedElementCellRenderer() {
        super();
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setText("");
        setBackground(backgroundColor);
        setForeground(Color.BLACK);

        if (value instanceof INamedElement) {

            INamedElement namedElement = (INamedElement) value;
            setText(namedElement.getName());

            final Font font;

            if (table.getSelectionModel().isSelectedIndex(row)) {
                font = new Font(getFont().getName(), Font.BOLD, getFont().getSize());
            } else {
                font = new Font(getFont().getName(), Font.PLAIN, getFont().getSize());
            }

            setFont(font);

            setPreferredSize(new Dimension(new TextMeasure(getText(), font).getWidth() + 20, getHeight()));
        }
        return this;
    }

}