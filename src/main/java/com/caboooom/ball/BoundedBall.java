package com.caboooom.ball;

import java.awt.*;

public class BoundedBall extends MoveableBall {

    private Rectangle bounds; // BoundedBall 객체가 존재할 수 있는 범위

    public BoundedBall(int x, int y, int radius) {
        super(x, y, radius);
        bounds = new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }

    public BoundedBall(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius, dx, dy);
        bounds = new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     *
     * @return 객체의 현재 위치가 bounds 범위를 벗어나면 true를, 그렇지 않으면 false를 리턴합니다.
     */
    public boolean isOutOfBounds() {
        return getMinX() < bounds.getMinX() || getMaxX() > bounds.getMaxX() ||
                getMinY() < bounds.getMinY() || getMaxY() > bounds.getMaxY();
    }

    @Override
    public void move() {
        super.move();
        if(isOutOfBounds()) {
            bounce();
        }
    }

    public void bounce() {
        if ((getMinX() < bounds.getMinX()) || (bounds.getMaxX() < getMaxX())) {
            setDx(-getDx());
        }

        if ((getMinY() < bounds.getMinY()) || (bounds.getMaxY() < getMaxY())) {
            setDy(-getDy());
        }
    }
}
