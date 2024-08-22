package com.caboooom.world;

import com.caboooom.Bounded;
import com.caboooom.Paintable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends JPanel {

    protected List<Bounded> boundedList;

    public World() {
        super();
        boundedList = new ArrayList<>();
    }

    public int getCount() {
        return boundedList.size();
    }

    public void add(Bounded bounded) {
        boundedList.add(bounded);
    }

    public void remove(Bounded bounded) {
        boundedList.remove(bounded);
    }

    public void reset() {
        boundedList.clear();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i < boundedList.size(); i++) {
            Bounded bounded = boundedList.get(i);
            if(bounded instanceof Paintable) {
                ((Paintable) bounded).paint(g);
            }
        }

    }
}
