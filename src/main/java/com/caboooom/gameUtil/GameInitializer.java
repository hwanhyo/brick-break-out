package com.caboooom.gameUtil;

import com.caboooom.Main;
import com.caboooom.ball.MoveableBall;
import com.caboooom.bar.Bar;
import com.caboooom.brick.PaintableBrick;
import com.caboooom.world.BoundedWorld;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import java.awt.*;

public class GameInitializer {

    public enum GameLevel {
        EASY, HARD, EXTREME
    }

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 450;
    private static final int DEFAULT_BALL_COUNT = 1;
    private static final int DEFAULT_MOVE_COUNT = 0;
    private static final int DEFAULT_DT = 60;

    private static int BALL_COUNT;
    private static int BAR_WIDTH;
    private static int BAR_INIT_Y;

    /**
     * 사용자가 선택한 MAP 정보를 리턴합니다.
     * @return MAP의 벽돌 배치 정보
     */
    public static int[][] chooseMap() {
        int selectedMapIndex = MapSelectionDialog.showMapSelectionDialog(Main.getFrame());

        switch (selectedMapIndex) {
            case 0 :
                return BrickMap.MAP_1;
            case 1 :
                return BrickMap.MAP_2;
            case 2 :
                return BrickMap.MAP_3;
            case 3 :
                return BrickMap.MAP_4;
            case 4 :
                return BrickMap.MAP_5;
            default :
                return null;
        }
    }

    public static void startGame(JFrame frame, BoundedWorld world) throws InterruptedException {
        Logger logger = LogManager.getLogger(Main.class);

        world.reset();
        world.setMoveCount(DEFAULT_MOVE_COUNT);
        world.setDt(DEFAULT_DT);
        setGameLevel(frame, world);
        initAndAddBalls(FRAME_WIDTH / 2, FRAME_HEIGHT - 100, BALL_COUNT, world);
        initAndAddBricks(chooseMap(), FRAME_WIDTH / 10 - 5, 20, world);
        initAndAddBar(FRAME_WIDTH - 50, BAR_INIT_Y, BAR_WIDTH, world);

        logger.log(Level.DEBUG, "Game starts after 2000 millis");
        Thread.sleep(2000);

        logger.log(Level.DEBUG, "invoke world.run()");
        world.run();
        frame.repaint();
    }

    private static void setGameLevel(Frame frame, BoundedWorld world) {
        String[] options = {"EASY", "HARD", "EXTREME"};
        int choice = JOptionPane.showOptionDialog(
                frame,
                "난이도를 선택하세요.",
                "난이도 선택",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0: // EASY
                world.setSpeedIncrementRatio(1.0);
                BALL_COUNT = 2;
                BAR_WIDTH = 200;
                BAR_INIT_Y = FRAME_HEIGHT + 30;
                break;
            case 1: // HARD
                world.setSpeedIncrementRatio(0.9);
                BALL_COUNT = 4;
                BAR_WIDTH = 100;
                BAR_INIT_Y = FRAME_HEIGHT - 10;
                break;
            case 2: // EXTREME
                world.setSpeedIncrementRatio(0.7);
                BALL_COUNT = 8;
                BAR_WIDTH = 50;
                BAR_INIT_Y = FRAME_HEIGHT - 30;
                break;
            default:
                world.setSpeedIncrementRatio(1.0);
                break;
        }
    }

    private static void initAndAddBricks(int[][] map, int brickWidth, int brickHeight, BoundedWorld world) {
        if(map == null) {
            throw new RuntimeException();
        }
        Logger logger = LogManager.getLogger(Main.class);
        int padding = 5;
        logger.log(Level.DEBUG, String.format("Add bricks: brickCount=%d, width=%d, height=%d, padding=%d",
                map.length * map[0].length, brickWidth, brickHeight, padding));

        for(int yi = 0; yi < map.length; yi++) {
            for(int xi = 0; xi < map[yi].length; xi++) {
                if (map[yi][xi] == 1) {
                    PaintableBrick brick = new PaintableBrick(
                            xi * (brickWidth + padding) + (brickWidth / 2),
                            yi * (brickHeight + padding) + 10,
                            brickWidth, brickHeight);
                    world.add(brick);
                }
            }
        }
    }

    private static void initAndAddBar(int initialX, int initialY, int width, BoundedWorld world) {
        Logger logger = LogManager.getLogger(Main.class);
        world.add(new Bar(initialX,initialY, width));
        logger.log(Level.DEBUG, String.format("Add bar: width=%d, height=fixedValue", 200));
    }

    private static void initAndAddBalls(int initialX, int initialY, int ballCount, BoundedWorld world) {
        Logger logger = LogManager.getLogger(Main.class);
        logger.log(Level.DEBUG, String.format("Add balls: ballCount=%d, initial position(x, y)=(%d, %d)",
                DEFAULT_BALL_COUNT, initialX, initialY));
        int count = 0;
        while(count < ballCount) {
            try {
                MoveableBall ball = new MoveableBall(initialX, initialY, 10);
                ball.setDx(10);
                ball.setDy(-10);
                world.add(ball);
                count++;
            } catch (IllegalArgumentException ignore) {
                logger.log(Level.DEBUG, "The initial variable values of the ball are illegal."
                        + "The ball will be recreated.");
            }
        }
    }
}
