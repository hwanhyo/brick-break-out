package com.caboooom.brick;

import com.caboooom.Bounded;
import com.caboooom.Breakable;

import java.awt.*;

public class Brick implements Bounded, Breakable {

    protected int x; // 벽돌 중심의 x 좌표
    protected int y; // 벽돌 중심의 y 좌표
    protected int width;
    protected int height;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - width / 2, y - width / 2, width, height);
    }

    @Override
    public int getMinX() {
        return x - width / 2;
    }

    @Override
    public int getMaxX() {
        return x + width / 2;
    }

    @Override
    public int getMinY() {
        return y - height / 2;
    }

    @Override
    public int getMaxY() {
        return y + height / 2;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
