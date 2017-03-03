package com.pnvds.pacmac;


/**
 * Point in two dimensions.
 *
 * @author Sergio Penavades
 * @version 09/01/2015
 */
public class PointXY {
    private final int x;
    private final int y;

    /**
     * Builder.
     *
     * @param x abscissa (column).
     * @param y ordinate (row).
     */
    public PointXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter.
     *
     * @return abscissa (column).
     */
    public int getX() {
        return x;
    }

    /**
     * Setter.
     *
     * @return ordinate (row).
     */
    public int getY() {
        return y;
    }

    /**
     * Message to print.
     *
     * @return Message to print.
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    /**
     * Generates a random point inside a square
     *
     * @param side square side.
     * @return random point.
     */
    public static PointXY random(int side) {
        int x = (int) (side * Math.random());
        int y = (int) (side * Math.random());
        return new PointXY(x, y);
    }

    /**
     * Compare THIS with other object.
     *
     * @param o other object.
     * @return TRUE if they are equivalents.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointXY q = (PointXY) o;

        return x == q.x && y == q.y;

    }

    /**
     * Unique Id.
     *
     * @return Unique Id.
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
