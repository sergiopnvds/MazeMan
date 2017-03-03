package com.pnvds.pacmac;


/**
 * A wall between two cells.
 *
 * @author Sergio Penavades Suarez
 * @version 09/01/2015
 */
public class Wall {
    private final Cell cell1;
    private final Cell cell2;

    /**
     * Builder.
     *
     * @param cell1 una cell.
     * @param cell2 otra cell.
     */
    public Wall(Cell cell1, Cell cell2) {
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    /**
     * Getter.
     *
     * @return cell1.
     */
    public Cell getCell1() {
        return cell1;
    }

    /**
     * Getter.
     *
     * @return cell2.
     */
    public Cell getCell2() {
        return cell2;
    }

    /**
     * Message to print.
     *
     * @return  Message to print.
     */
    public String toString() {
        return String.format("[%s, %s]", cell1, cell2);
    }

    /**
     * Compare cells
     *
     * @param o other cell.
     * @return TRUE if the cells ar equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Wall wall = (Wall) o;

        if (cell2 == null) {
            return cell1.equals(wall.cell1) && wall.cell2 == null;
        }

        if (cell1.equals(wall.cell1) && cell2.equals(wall.cell2))
            return true;
        if (cell1.equals(wall.cell2) && cell2.equals(wall.cell1))
            return true;
        return false;
    }

    /**
     * Unique ID.
     *
     * @return Unique ID.
     */
    @Override
    public int hashCode() {
        int result = cell1.hashCode();
        if (cell2 != null)
            result = 31 * result + cell2.hashCode();
        return result;
    }
}
