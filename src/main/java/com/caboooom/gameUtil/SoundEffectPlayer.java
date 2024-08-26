package com.caboooom.gameUtil;

public class SoundEffectPlayer {

    private static final SoundEffect ballHitSound = new SoundEffect("/sounds/ball-bounce.mp3");
    private static final SoundEffect brickBreakSound = new SoundEffect("/sounds/brick-break.mp3");
    private static final SoundEffect gameCompleteSound = new SoundEffect("/sounds/game-complete.mp3");
    private static final SoundEffect gameOverSound = new SoundEffect("/sounds/game-over.mp3");

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
