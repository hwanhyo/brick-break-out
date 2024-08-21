package com.caboooom;

public interface Moveable {

    /**
     *
     * @return 단위 시간당 x축 방향으로의 이동량
     */
    int getDx();

    void setDx(int dx);

    /**
     *
     * @return 단위 시간당 y축 방향 이동량
     */
    int getDy();

    void setDy(int dy);

    /**
     * 객체를 단위 시간동안 x축 방향으로 dx만큼, y축 방향으로 dy만큼 이동시킵니다.
     */
    void move();

    /**
     * 객체를 특정 좌표 (x,y)로 이동시킵니다.
     * @param x 이동할 위치의 x좌표
     * @param y 이동할 위치의 y좌표
     */
    void moveTo(int x, int y);

    /**
     * Bounded 객체의 이동 방향을 변경합니다.
     * @param bounded 이동 방향을 변경할 Bounded 객체
     */
    void bounce(Bounded bounded);

}
