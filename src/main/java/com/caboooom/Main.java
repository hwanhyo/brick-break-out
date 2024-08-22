package com.caboooom;

import com.caboooom.ball.MoveableBall;
import com.caboooom.bar.Bar;
import com.caboooom.brick.PaintableBrick;
import com.caboooom.world.BoundedWorld;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.lang.String.format;

public class Main {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 450;
    private static final int DEFAULT_BALL_COUNT = 1;
    private static final int DEFAULT_MOVE_COUNT = 0;
    private static final int DEFAULT_DT = 60;
    private static final Color[] colors = new Color[] {
            Color.BLACK, Color.WHITE, Color.pink, Color.ORANGE
    };
    private static final Random random = new Random();
    private static final JFrame frame = new JFrame();

    public static void main( String[] args ) throws InterruptedException {
        Logger logger = LogManager.getLogger(Main.class);
        logger.log(Level.DEBUG,
                String.format("Create JFrame and JPanel: width=%d, height=%d", FRAME_WIDTH, FRAME_HEIGHT));
        BoundedWorld world = new BoundedWorld(DEFAULT_MOVE_COUNT, DEFAULT_DT);
        frame.add(world);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);

        startGame(frame, world);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void startGame(JFrame frame, BoundedWorld world) throws InterruptedException {
        Logger logger = LogManager.getLogger(Main.class);

        world.reset();
        world.setMoveCount(DEFAULT_MOVE_COUNT);
        world.setDt(DEFAULT_DT);
        initAndAddBalls(FRAME_WIDTH / 2, FRAME_HEIGHT - 100, 0, world);
        initAndAddBricks(FRAME_WIDTH / 10 - 5, 20, world);
        initAndAddBar(FRAME_WIDTH - 50,FRAME_HEIGHT + 30, 200, world);

        logger.log(Level.DEBUG, "Game starts after 2000 millis");
        Thread.sleep(2000);

        logger.log(Level.DEBUG, "invoke world.run()");
        world.run();
        frame.repaint();
    }

    private static void initAndAddBar(int initialX, int initialY, int width, BoundedWorld world) {
        Logger logger = LogManager.getLogger(Main.class);
        world.add(new Bar(initialX,initialY, width));
        logger.log(Level.DEBUG, format("Add bar: width=%d, height=fixedValue", 200));
    }

    private static void initAndAddBricks(int brickWidth, int brickHeight, BoundedWorld world) {
        Logger logger = LogManager.getLogger(Main.class);
        int brickX = brickWidth / 2;
        int brickY = 10;
        int padding = 5;
        int row = 2;
        int col = 10;
        logger.log(Level.DEBUG, format("Add bricks: brickCount=%d, width=%d, height=%d, padding=%d",
                row * col, brickWidth, brickHeight, padding));
        for(int yi = 0; yi < row; yi++) {
            for(int xi = 0; xi < col; xi++) {
                PaintableBrick brick = new PaintableBrick(
                        brickX + xi * brickWidth + xi * padding,
                        brickY + yi * brickHeight + yi * padding,
                        brickWidth, brickHeight);
                world.add(brick);
            }
        }
    }

    private static void initAndAddBalls(int initialX, int initialY, int ballCount, BoundedWorld world) {
        Logger logger = LogManager.getLogger(Main.class);
        logger.log(Level.DEBUG, format("Add balls: ballCount=%d, initial position(x, y)=(%d, %d)",
                        DEFAULT_BALL_COUNT, initialX, initialY));
        while(ballCount < 2) {
            try {
                MoveableBall ball = new MoveableBall(initialX, initialY, 10);
                ball.setDx(10);
                ball.setDy(-10);
                world.add(ball);
                ballCount++;
            } catch (IllegalArgumentException ignore) {
                logger.log(Level.DEBUG, "The initial variable values of the ball are illegal."
                        + "The ball will be recreated.");
            }
        }
    }
}
