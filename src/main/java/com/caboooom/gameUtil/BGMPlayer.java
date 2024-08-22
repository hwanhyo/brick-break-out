package com.caboooom.gameUtil;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BGMPlayer {

    private Clip clip;

    public BGMPlayer(String filePath) {
        try {
            URL soundURL = getClass().getResource(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }
}

