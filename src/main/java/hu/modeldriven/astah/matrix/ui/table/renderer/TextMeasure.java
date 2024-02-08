package hu.modeldriven.astah.matrix.ui.table.renderer;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class TextMeasure {

    private final String text;
    private final Font font;

    public TextMeasure(String text, Font font) {
        this.text = text;
        this.font = font;
    }

    public Rectangle2D getBounds() {
        Graphics graphics = new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_ARGB).getGraphics();
        graphics.setFont(font);
        FontMetrics metrics = graphics.getFontMetrics();
        return metrics.getStringBounds(text, null);
    }

    public int getWidth() {
        return (int) getBounds().getWidth();
    }

}
