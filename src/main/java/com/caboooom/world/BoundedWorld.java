package com.caboooom.world;

import com.caboooom.Bounded;
import com.caboooom.Breakable;
import com.caboooom.Moveable;
import com.caboooom.ball.MoveableBall;
import com.caboooom.bar.Bar;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoundedWorld extends MoveableWorld {

    private static final Logger logger = LogManager.getLogger(BoundedWorld.class);
    private boolean isGameOver = false;
    private boolean isCompleted = false;

    /**
     * BoundedWorld에 속한 Bounded 객체가 BoundedWorld의 범위를 벗어나는지 확인합니다.
     *
     * @param bounded BoundedWorld에 속한 Bounded 객체
     * @return bounded가 BoundedWorld의 범위를 벗어나면 true, 그렇지 않으면 false
     */
    public boolean isOutOfBounds(Bounded bounded) {
        return bounded.getMinX() < getBounds().getMinX() ||
                bounded.getMaxX() > getBounds().getMaxX() ||
                bounded.getMinY() < getBounds().getMinY() ||
                bounded.getMaxY() > getBounds().getMaxY();
    }

    /**
     * Bounded 객체의 이동 방향을 바꿉니다.
     *
     * 이 메서드는 Bounded 객체가 BoundedWorld의 경계를 벗어났을 때, 이동 방향을 반전시킵니다.
     * 객체의 경계가 BoundedWorld의 x축 또는 y축 경계를 벗어날 경우,
     * 각각의 축에 대한 이동 방향이 반전됩니다.
     *
     * @param bounded 이동 방향을 변경할 Bounded 객체.
     *                이 객체는 Moveable 인터페이스를 구현해야 합니다.
     *                그렇지 않으면 메서드가 동작하지 않습니다.
     */
    public void bounce(Bounded bounded) {
        if(!(bounded instanceof Moveable) || bounded instanceof Bar) {
            return;
        }
        MoveableBall moveable = (MoveableBall) bounded;
        if(moveable.getMinX() < getBounds().getMinX() ||
                moveable.getMaxX() > getBounds().getMaxX()) {
            moveable.setDx(-moveable.getDx());
        }
        if(moveable.getMinY() < getBounds().getMinY() ||
                moveable.getMaxY() > getBounds().getMaxY()) {
            moveable.setDy(-moveable.getDy());
        }

        if(moveable.getMaxY() > getBounds().getMaxY()) {
            logger.log(Level.INFO, "The ball has hit the ground. Game over!");
            triggerGameOver();
        }
    }

    /**
     * 객체 모두 정지! 화면에 GAME OVER 출력
     */
    private void triggerGameOver() {
        isGameOver = true;
        stopAllMovement();
        repaint();
    }

    /**
     * 게임 클리어! 화면에 COMPLETE 출력
     */
    private void triggerGameEnd() {
        isCompleted = true;
        stopAllMovement();
        repaint();
    }

    private void stopAllMovement() {
        for (Bounded bounded : boundedList) {
            if (bounded instanceof Moveable) {
                Moveable moveable = (Moveable) bounded;
                moveable.setDx(0);
                moveable.setDy(0);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String message = "GAME OVER";
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(message)) / 2;
            int y = (getHeight() / 2) + fm.getAscent() / 2;
            g.drawString(message, x, y);
        }
        if (isCompleted) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String message = "COMPLETED!";
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(message)) / 2;
            int y = (getHeight() / 2) + fm.getAscent() / 2;
            g.drawString(message, x, y);
        }
    }

    @Override
    public void move() {
        if(isCompleted || isGameOver) {
            return;
        }
        super.move();

        int brickCount = 0;
        List<Bounded> breaks = new ArrayList<>();
        for(int i = 0; i < getCount(); i++) {
            Bounded bounded = boundedList.get(i);

            // world의 bound와 충돌시 튕김
            if(bounded instanceof Moveable && isOutOfBounds(bounded)) {
                bounce(bounded);
            }

            // bound끼리 겹치면 서로 튕김
            for (int j = 0; j < getCount(); j++) {
                if (i != j) {
                    Bounded other = boundedList.get(j);

                    if (bounded.getBounds().intersects(other.getBounds())) {
                        if(other instanceof Breakable) {
                            breaks.add(other);
                        }
                        ((Moveable) bounded).bounce(other);
                    }
                    if (other instanceof Breakable) {
                        brickCount++;
                    }
                }
            }

            if (bounded instanceof Breakable) {
                brickCount++;
            }
            // 벽돌에 공이 닿으면 벽돌이 깨짐
            for(Bounded b : breaks) {
                logger.log(Level.DEBUG, String.format("Break the brick of position (%d, %d)",
                        b.getMinX(), b.getMinY()));
                remove(b);
            }

            // 남은 벽돌이 없으면 게임 종료
            if(brickCount == 0) {
                logger.log(Level.TRACE, "No bricks left. Game Completed!");
                isCompleted = true;
                triggerGameEnd();
            }
        }
    }
}
