package com.caboooom.gameUtil;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

public class BGMPlayer {

    private Player player;
    private Thread playThread;
    private static final Logger logger = LogManager.getLogger(BGMPlayer.class);

    public BGMPlayer(String filePath) {
        playThread = new Thread(() -> {
            while (true) { // 무한 반복 재생
                try (FileInputStream fileInputStream = new FileInputStream(filePath);) {
                    player = new Player(fileInputStream);
                    player.play();
                } catch (JavaLayerException | IOException e) {
                    logger.error("Music play failed - " + filePath);
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void play() {
        playThread.start();
    }

    public void stop() {
        if(player != null) {
            player.close();
        }
    }
}

