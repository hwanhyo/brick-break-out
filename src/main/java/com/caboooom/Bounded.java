package com.caboooom;

import java.awt.*;

public interface Bounded {

    Rectangle getBounds();

    int getMinX();

    int getMaxX();

    int getMinY();

    int getMaxY();

    int getWidth();

    int getHeight();

}
