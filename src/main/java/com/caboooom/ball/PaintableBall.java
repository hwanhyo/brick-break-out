package com.caboooom.ball;

import com.caboooom.Paintable;

import java.awt.*;

public class PaintableBall extends Ball implements Paintable {

    public static final Color DEFAULT_COLOR = Color.BLACK;
    private Color color;

    public PaintableBall(int x, int y, int radius) {
        this(x, y, radius, DEFAULT_COLOR);
    }

    public PaintableBall(int x, int y, int radius, Color color) {
        super(x, y, radius);
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(getMinX(), getMinY(), getWidth(), getHeight());
    }
}
