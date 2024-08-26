package com.caboooom;

import com.caboooom.gameUtil.BGMPlayer;
import com.caboooom.gameUtil.GameInitializer;
import com.caboooom.world.BoundedWorld;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Main {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 450;
    private static final int DEFAULT_MOVE_COUNT = 0;
    private static final int DEFAULT_DT = 60;

    private static final JFrame frame = new JFrame();
    private static final Logger logger = LogManager.getLogger();
    private static final BGMPlayer bgmPlayer = new BGMPlayer("src/main/resources/sounds/background.mp3");

    public static void main( String[] args ) throws InterruptedException {
        bgmPlayer.play();

        logger.log(Level.DEBUG,
                String.format("Create JFrame and JPanel: width=%d, height=%d", FRAME_WIDTH, FRAME_HEIGHT));
        BoundedWorld world = new BoundedWorld(DEFAULT_MOVE_COUNT, DEFAULT_DT);
        frame.add(world);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);

        GameInitializer.startGame(frame, world);
    }

    public static JFrame getFrame() {
        return frame;
    }

}
