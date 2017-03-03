package com.pnvds.pacmac;


/**
 * The four candinal directions
 *
 * @author Sergio Penavades
 * @version 09/01/2015
 */
public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    /**
     * Chose a random direction.
     *
     * @return one of the available directions.
     */
    public static Direction random() {
        Direction[] directions = values();
        int i = (int) (directions.length * Math.random());
        return directions[i];
    }
}
