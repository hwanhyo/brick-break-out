package com.caboooom.world;

import com.caboooom.Bounded;
import com.caboooom.Moveable;
import com.caboooom.ball.MoveableBall;
import com.caboooom.bar.Bar;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveableWorld extends World {

    private static final Logger logger = LogManager.getLogger(MoveableWorld.class);
    private int moveCount; // 현재 이동 횟수
    private int maxMoveCount; // 최대 이동 횟수 (0이면 제한 없이 계속 이동합니다.)

    public MoveableWorld() {
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();

                for (Bounded bounded : boundedList) {
                    if (bounded instanceof Bar) {
                        ((Bar) bounded).moveTo(mouseX, 0);
                    }
                }
                repaint();
            }
        });
    }

    public MoveableWorld(int maxMoveCount) {
        this.maxMoveCount = maxMoveCount;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int maxMoveCount) {
        this.maxMoveCount = maxMoveCount;
    }

    /**
     * 이동 횟수를 초기화합니다.
     * 최대 이동 횟수는 변하지 않습니다.
     */
    public void reset() {
        moveCount = 0;
    }

    /**
     * World에 속한 객체들 중 Moveable 인터페이스의 구현체들을
     * 단위 시간동안 x축 방향으로 dx만큼, y축 방향으로 dy만큼 1회 이동시키고, moveCount를 1만큼 증가시킵니다.
     * 이동이 끝나면 화면을 다시 그립니다.
     *
     * 만약 moveCount가 maxMoveCount 이상이라면, 실행하지 않고 종료합니다.
     */
    public void move() {
        if(maxMoveCount != 0 && moveCount >= maxMoveCount) {
            return;
        }
        for (int i = 0; i < getCount(); i++) {
            Bounded bounded = boundedList.get(i);
            if (bounded instanceof MoveableBall) {
                ((Moveable) bounded).move();
            }
        }
        moveCount++;
        repaint();
    }

    /**
     * 최대 이동 횟수만큼 Moveable 객체를 이동시킵니다.
     * 최재 이동 횟수가 0이면, 계속 이동시킵니다.
     */
    public void run() {
        logger.log(Level.DEBUG, "invoke run()");
        while(maxMoveCount == 0 || moveCount < maxMoveCount) {
            logger.log(Level.TRACE, String.format("invoke move(): moveCount=%d, maxMoveCount=%d",
                    moveCount, maxMoveCount));
            move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
