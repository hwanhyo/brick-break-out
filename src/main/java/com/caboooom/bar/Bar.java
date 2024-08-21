package com.caboooom.bar;

import com.caboooom.Bounded;
import com.caboooom.Moveable;
import com.caboooom.Paintable;

import java.awt.*;

/**
 * 벽돌깨기 게임에서 사용자가 조작할 수 있는 바(Bar)입니다.
 * x축 방향으로만 이동 가능하고, y축 방향으로는 이동 불가능합니다.
 */
public class Bar implements Bounded, Paintable, Moveable {

    private static final int DEFAULT_WIDTH = 100;
    private int x;
    private int y;
    private int width;
    private final int height = 30; // 높이 고정
    private int dx;

    public Bar(int x, int y) {
        this(x, y, DEFAULT_WIDTH);
    }

    public Bar(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getMinX(), getMinY(), width, height);
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
        return y - width / 2;
    }

    @Override
    public int getMaxY() {
        return y + width / 2;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
        return 0;
    }

    @Override
    public void setDy(int dy) {
        // y축 이동은 불가능하므로 구현하지 않음
    }

    @Override
    public void move() {
        x += dx;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
    }

    @Override
    public void bounce(Bounded bounded) {
        // Bar 객체는 튕기지 않으므로 구현하지 않음
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(getMinX(), getMinY(), width, height);
    }
}