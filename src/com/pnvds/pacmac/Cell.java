package com.pnvds.pacmac;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Laberinth cell .
 *
 * @author Sergio Penavades
 * @version 9/1/2015
 */
public class Cell {
    private final PointXY point;
    private final Cell[] conexions;
    private final boolean[] walls;
    private State state;

    /**
     * Builder.
     *
     * @param x coordinate X.
     * @param y coordinate Y.
     */
    public Cell(int x, int y) {
        point = new PointXY(x, y);
        conexions = new Cell[Direction.values().length];
        walls = new boolean[Direction.values().length];
        Arrays.fill(walls, true);
        state = State.EMPTY;
    }

    /**
     * Getter.
     *
     * @return point where the cell is.
     */
    public PointXY getPoint() {
        return point;
    }

    /**
     * Getter.
     *
     * @return state of the Cell.
     */
    public State getState() {
        return state;
    }

    /**
     * Setter.
     *
     * @param state state of the Cell.
     */
    public void setState(State state) {
//        System.out.println(point + ": " + state);
        this.state = state;
    }

    /**
     * Puts the Cell in its context: connects to other cell in a certain direction.
     *
     * @param direction in which the other cell is.
     * @param Cell cell to we are going to connect.
     */
    public void connect(Direction direction, Cell Cell) {
        conexions[direction.ordinal()] = Cell;
    }

    /**
     * Getter.
     *
     * @param direction direction of searching.
     * @return Cell in this direction. May be null if there are no more cells, for example in the map edge.
     */
    public Cell getCell(Direction direction) {
        return conexions[direction.ordinal()];
    }

    /**
     * Create a wall between to Cells.
     *
     * @param Cell1 one Cell.
     * @param Cell2 other Cell.
     */
    public static void putWall(Cell Cell1, Cell Cell2) {
        Cell1.putWall(Cell2);
        Cell2.putWall(Cell1);
    }

    /**
     * Remove a wall between to Cells.
     *
     * @param Cell1 one Cell.
     * @param Cell2 other Cell.
     */
    public static void removeWall(Cell Cell1, Cell Cell2) {
        Cell1.removeWall(Cell2);
        Cell2.removeWall(Cell1);
    }

    /**
     * Create a wall between THIS and another Cell.
     *
     * @param Cell2 other Cell.
     */
    private void putWall(Cell Cell2) {
        for (Direction direction : Direction.values()) {
            Cell c = conexions[direction.ordinal()];
            if (c != null && c.equals(Cell2))
                walls[direction.ordinal()] = true;
        }
    }

    /**
     * Remove a wall between THIS and other Cell.
     *
     * @param Cell2 other Cell.
     */
    private void removeWall(Cell Cell2) {
        for (Direction direction : Direction.values()) {
            Cell c = conexions[direction.ordinal()];
            if (c != null && c.equals(Cell2))
                walls[direction.ordinal()] = false;
        }
    }

    /**
     * Check if there is a wall THIS and another Cell.
     *
     * @param Cell2 another Cell.
     * @return TRUE if there is a wall; FALSE if there is not.
     */
    public boolean isWall(Cell Cell2) {
        for (Direction direction : Direction.values()) {
            Cell c = conexions[direction.ordinal()];
            if (c != null && c.equals(Cell2))
                return walls[direction.ordinal()];
        }
        return true;
    }

    /**
     * Check if a cell has a wall in one direction.
     *
     * @param direction direction if which wall may be.
     * @return TRUE if there is a wall; FALSE if there is not.
     */
    public boolean isWall(Direction direction) {
        return walls[direction.ordinal()];
    }

    /**
     * List of walls arround the cell
     *
     * @return list of walls arround the Cell.
     */
    public Collection<Wall> getWalls() {
        List<Wall> list = new ArrayList<Wall>();
        for (Direction direction : Direction.values()) {
            if (isWall(direction)) {
                Cell Cell2 = getCell(direction);
                list.add(new Wall(this, Cell2));
            }
        }
        return list;
    }

    /**
     * Para imprimir.
     *
     * @return mensaje para imprimir.
     */
    public String toString() {
        return point.toString();
    }
}
