package com.caboooom.brick;

import com.caboooom.Paintable;

import java.awt.*;

public class PaintableBrick extends Brick implements Paintable {

    private static final Color DEFAULT_COLOR = Color.BLACK;
    private Color color;

    public PaintableBrick(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = DEFAULT_COLOR;
    }

    public PaintableBrick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(getMinX(), getMinY(), width, height);

        g.setColor(Color.BLACK);
        g.drawRect(getMinX(), getMinY(), width, height);
    }
}
