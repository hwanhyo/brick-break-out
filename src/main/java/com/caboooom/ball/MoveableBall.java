package com.caboooom.ball;

import com.caboooom.Bounded;
import com.caboooom.Moveable;

import java.awt.*;

public class MoveableBall extends PaintableBall implements Moveable {

    private int dx;
    private int dy;

    public MoveableBall(int x, int y, int radius) {
        super(x, y, radius);
    }

    public MoveableBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public MoveableBall(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public MoveableBall(int x, int y, int radius, Color color, int dx, int dy) {
        super(x, y, radius, color);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public int getDx() {
        return dx;
    }

    @Override
    public void setDx(int dx) {
        this.dx = dx;
    }

    @Override
    public int getDy() {
        return dy;
    }

    @Override
    public void setDy(int dy) {
        this.dy = dy;
    }

    @Override
    public void move() {
        moveTo(x + dx, y + dy);
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void bounce(Bounded bounded) {

        if (getBounds().intersects(bounded.getBounds())) {
            Rectangle intersection = getBounds().intersection(bounded.getBounds());

            if (intersection.getWidth() != getBounds().getWidth() && intersection.getWidth() != bounded.getWidth()) {
                setDx(-getDx());
            }

            if (intersection.getHeight() != getBounds().getHeight()
                    && intersection.getHeight() != bounded.getHeight()) {
                setDy(-getDy());
            }

        }
    }
}
