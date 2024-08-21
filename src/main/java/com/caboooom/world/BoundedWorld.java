package com.caboooom.world;

import com.caboooom.Bounded;
import com.caboooom.Breakable;
import com.caboooom.Moveable;
import com.caboooom.ball.MoveableBall;

import java.util.ArrayList;
import java.util.List;

public class BoundedWorld extends MoveableWorld {

    /**
     * BoundedWorld에 속한 Bounded 객체가 BoundedWorld의 범위를 벗어나는지 확인합니다.
     *
     * @param bounded BoundedWorld에 속한 Bounded 객체
     * @return bounded가 BoundedWorld의 범위를 벗어나면 true, 그렇지 않으면 false
     */
    public boolean isOutOfBounds(Bounded bounded) {
        return bounded.getMinX() < getBounds().getMinX() ||
                bounded.getMaxX() > getBounds().getMaxX() ||
                bounded.getMinY() < getBounds().getMinY() ||
                bounded.getMaxY() > getBounds().getMaxY();
    }

    /**
     * Bounded 객체의 이동 방향을 바꿉니다.
     *
     * 이 메서드는 Bounded 객체가 BoundedWorld의 경계를 벗어났을 때, 이동 방향을 반전시킵니다.
     * 객체의 경계가 BoundedWorld의 x축 또는 y축 경계를 벗어날 경우,
     * 각각의 축에 대한 이동 방향이 반전됩니다.
     *
     * @param bounded 이동 방향을 변경할 Bounded 객체.
     *                이 객체는 Moveable 인터페이스를 구현해야 합니다.
     *                그렇지 않으면 메서드가 동작하지 않습니다.
     */
    public void bounce(Bounded bounded) {
        if(!(bounded instanceof Moveable)) {
            return;
        }
        MoveableBall moveable = (MoveableBall) bounded;
        if(moveable.getMinX() < getBounds().getMinX() ||
                moveable.getMaxX() > getBounds().getMaxX()) {
            moveable.setDx(-moveable.getDx());
        }
        if(moveable.getMinY() < getBounds().getMinY() ||
                moveable.getMaxY() > getBounds().getMaxY()) {
            moveable.setDy(-moveable.getDy());
        }
    }

    @Override
    public void move() {
        super.move();

        List<Bounded> breaks = new ArrayList<>();
        for(int i = 0; i < getCount(); i++) {
            Bounded bounded = boundedList.get(i);

            // world의 bound와 충돌시 튕김
            if(bounded instanceof Moveable && isOutOfBounds(bounded)) {
                bounce(bounded);
            }

            // bound끼리 겹치면 서로 튕김
            for (int j = 0; j < getCount(); j++) {
                if (i != j) {
                    Bounded other = boundedList.get(j);

                    if (bounded.getBounds().intersects(other.getBounds())) {
                        if(other instanceof Breakable) {
                            breaks.add(other);
                        }
                        ((Moveable) bounded).bounce(other);
                    }
                }
            }

            for(Bounded b : breaks) {
                remove(b);
            }
        }
    }
}
