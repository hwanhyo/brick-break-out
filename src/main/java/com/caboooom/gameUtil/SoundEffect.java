package com.caboooom.gameUtil;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;

public class SoundEffect {

    private final String filePath;
    private static final Logger logger = LogManager.getLogger();

    public SoundEffect(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        try (BufferedInputStream bufferedStream = new BufferedInputStream(getClass().getResourceAsStream(filePath));) {
            Player player = new Player(bufferedStream);
            new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    logger.log(Level.ERROR, "Failed to play sound effect: ", filePath);
                    throw new RuntimeException(e);
                }
            }).start();
        } catch (JavaLayerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
