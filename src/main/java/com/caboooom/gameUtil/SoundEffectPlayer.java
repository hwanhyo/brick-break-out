package com.caboooom.gameUtil;

public class SoundEffectPlayer {

    private static SoundEffect ballHitSound = new SoundEffect("/sounds/ball-bounce.mp3");
    private static SoundEffect brickBreakSound = new SoundEffect("/sounds/brick-break.mp3");
    private static SoundEffect gameCompleteSound = new SoundEffect("/sounds/game-complete.mp3");
    private static SoundEffect gameOverSound = new SoundEffect("/sounds/game-over.mp3");

    public static void ballHit() {
        ballHitSound.play();
    }

    public static void brickBreak() {
        brickBreakSound.play();
    }

    public static void gameComplete() {
        gameCompleteSound.play();
    }

    public static void gameOver() { gameOverSound.play();
    }

}
