package com.pnvds.pacmac;


import java.awt.*;

/**
 * Marks a cell content
 *
 * @author Sergio Penavades
 * @version 09/01/2015
 */
public enum State {
    EMPTY(Color.WHITE), BLUE(Color.BLUE), ENEMY(Color.RED);
    private final Color color;

    /**
     * Builder.
     *
     * @param color of a cell.
     */
    private State(Color color) {
        this.color = color;
    }

    /**
     * Getter.
     *
     * @return color of a cell.
     */
    public Color getColor() {
        return color;
    }
}
