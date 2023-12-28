package hu.modeldriven.astah.matrix.ui.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class VerticalTableHeaderCellRenderer extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value != null) {
            this.setText(value.toString());
        } else {
            this.setText("");
        }

        return this;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setClip(0,0,500,500);
        g2.setColor(new Color(60,179,113));
        g2.setFont(new Font("Arial",Font.BOLD,12));
        AffineTransform at = new AffineTransform();
        at.setToTranslation(this.getWidth(), this.getHeight());
        g2.transform(at);
        double radianAngle = ( ((double)-90) / ((double)180) ) * Math.PI;
        at.setToRotation(radianAngle);
        g2.transform(at);
        g2.drawString(this.getText(), 0.0f, 0.0f);
    }

}
