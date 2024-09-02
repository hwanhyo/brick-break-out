package com.caboooom.gameUtil;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;

public class SoundEffect {

    private final String filePath;

    public SoundEffect(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        new Thread(() ->{
            try (BufferedInputStream bufferedStream = new BufferedInputStream(getClass().getResourceAsStream(filePath));) {
                Player player = new Player(bufferedStream);
                player.play();
            } catch (JavaLayerException | IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
