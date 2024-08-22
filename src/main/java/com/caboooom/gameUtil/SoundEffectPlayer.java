package com.caboooom.gameUtil;

public class SoundEffectPlayer {

    private static SoundEffect ballHitSound = new SoundEffect("/sounds/ball-bounce.wav");
    private static SoundEffect brickBreakSound = new SoundEffect("/sounds/brick-break.wav");
    private static SoundEffect gameCompleteSound = new SoundEffect("/sounds/game-complete.wav");
    private static SoundEffect gameOverSound = new SoundEffect("/sounds/game-over.wav");

    public static void ballHit() {
        ballHitSound.play();
    }

    public static void brickBreak() {
        brickBreakSound.play();
    }

    public static void gameComplete() {
        gameCompleteSound.play();
    }

    public static void gameOver() {
        gameOverSound.play();
    }

}
