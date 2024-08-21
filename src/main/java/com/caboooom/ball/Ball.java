package com.caboooom.ball;

import com.caboooom.Bounded;

import java.awt.*;

public class Ball implements Bounded {

    protected int x; // 원 중심의 x좌표
    protected int y; // 원 중심의 y좌표
    protected int radius;

    public Ball(int x, int y, int radius) {
        if(radius <= 0) {
            throw new IllegalArgumentException("반지름은 양의 정수여야 합니다.");
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }

    @Override
    public int getMinX() {
        return x - radius;
    }

    @Override
    public int getMaxX() {
        return x + radius;
    }

    @Override
    public int getMinY() {
        return y - radius;
    }

    @Override
    public int getMaxY() {
        return y + radius;
    }

    @Override
    public int getWidth() {
        return 2 * radius;
    }

    @Override
    public int getHeight() {
        return 2 * radius;
    }
}
