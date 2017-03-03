package com.pnvds.pacmac;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Blue circle: player.
 *
 * @author Sergio Penavdes
 * @version 09/01/2015
 */
public class Player {
    private final Maze maze;
    private Cell cell;
    private final Cell finishLine;
    private static int movements;

    /**
     * Constructor.
     *
     * @param maze the maze of game.
     * @param start  initial position.
     * @param finishLine target position.
     */
    public Player(Maze maze, Cell start, Cell finishLine) {
        this.maze = maze;
        this.cell = start;
        start.setState(State.BLUE);
        this.finishLine = finishLine;
    }

    /**
     * Getter.
     *
     * @return actual position.
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Getter.
     *
     * @return target position.
     */
    public Cell getFinishLine() {
        return finishLine;
    }

    /**
     * Try to move in the direction passed.
     *
     * @param direction desired.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public void tryToMove(Direction direction) throws FileNotFoundException, IOException {
        try {
            // calculate the cell where is desired to go.
            Cell cell2 = maze.getConexion(cell, direction);
            if (cell2 == null) {
                // there is not a possible cell in this direction
                return;
            }

            // see what is the maze:
            // has to be synchronized
            // to guarantee that the actors are static while we see
            Screen screen = maze.getScreen();
            screen.movePlayer(this, cell2);
            // If we reach the finish Line, the game ends.
            if (cell.equals(finishLine))
                maze.playerWin();
        } catch (PlayerKilled playerKilled) {
            //game over
            maze.playerLose();
        }
    }

    /**
     * Setter.
     * Additionally, empty actual cell and mark player in a new one.
     *
     * @param destination cell.
     */
    public void setCell(Cell newCell) {
        Cell previousCell = this.cell;
        previousCell.setState(State.EMPTY);
        newCell.setState(State.BLUE);
        this.cell = newCell;
        maze.draw();
        movements++;
    }
    
    public static int getMovements(){
    	return movements;
    }
    
    public static void setMovements(int movementsX){
    	movements = movementsX;
    }
}
