package com.caboooom;

import com.caboooom.ball.MoveableBall;
import com.caboooom.bar.Bar;
import com.caboooom.brick.PaintableBrick;
import com.caboooom.world.BoundedWorld;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 450;
    private static final int DEFAULT_BALL_COUNT = 1;
    private static final int DEFAULT_MOVE_COUNT = 0;
    private static final Color[] colors = new Color[] {
            Color.BLACK, Color.WHITE, Color.pink, Color.ORANGE
    };
    private static final Random random = new Random();


    public static void main( String[] args ) {
        JFrame frame = new JFrame();
        BoundedWorld world = new BoundedWorld();
        frame.add(world);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);

        // add balls
        int ballCount = 0;
        int initialX = FRAME_WIDTH / 2;
        int initialY = FRAME_HEIGHT - 100;
        while(ballCount < 2) {
            try {
                MoveableBall ball = new MoveableBall(initialX, initialY, 10);
                ball.setDx(10);
                ball.setDy(10);
                world.add(ball);
                ballCount++;
            } catch (IllegalArgumentException ignore) {
                // create another ball
            }
        }

        // add bricks
        int brickWidth = FRAME_WIDTH / 10 - 5;
        int brickHeight = 20;
        int brickX = brickWidth / 2;
        int brickY = 10;
        int padding = 5;
        for(int yi = 0; yi < 2; yi++) {
            for(int xi = 0; xi < 10; xi++) {
                PaintableBrick brick = new PaintableBrick(
                        brickX + xi * brickWidth + xi * padding,
                        brickY + yi * brickHeight + yi * padding,
                        brickWidth, brickHeight);
                world.add(brick);
            }
        }

        // add bar
        world.add(new Bar(FRAME_WIDTH - 50,FRAME_HEIGHT + 30, 200));

        // 2초 후 게임 시작
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        world.run();
        frame.repaint();
    }
}