package com.pnvds.pacmac;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A maze.
 *
 * @author Sergio Penavades
 * @version 09/01/2015
 */
public class Maze {
    private final int side;
    private final GUI gui;

    private final Screen screen;

    private final Cell[][] cells;
    private final List<Enemy> enemies = new ArrayList<Enemy>();
    private final Player player;

    /**
     * Constructor.
     *
     * @param side number of cells in vertical and horizontal.
     * @param gui  to draw in window.
     */
    public Maze(int side, GUI gui) {
        this.side = side;
        this.gui = gui;

        screen = new Screen();

        cells = new Cell[side][side];
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                Cell cell = cells[x][y];
                if (0 < x)
                    cell.connect(Direction.WEST, cells[x - 1][y]);
                if (x + 1 < side)
                    cell.connect(Direction.EAST, cells[x + 1][y]);
                if (0 < y)
                    cell.connect(Direction.SOUTH, cells[x][y - 1]);
                if (y + 1 < side)
                    cell.connect(Direction.NORTH, cells[x][y + 1]);
            }
        }
 
        if (InitWindow.getGameType().equals("Standard")){
        	Cell finishLine = getCell(0, 0);
            Cell start = getCell(side - 1, side - 1);
            player = new Player(this, start, finishLine);
        }
        else{
        	Cell finishLine = getCell(Maze.rndPos()-1, Maze.rndPos()-1);
            Cell start = getCell(Maze.rndPos()-1, Maze.rndPos()-1);
            player = new Player(this, start, finishLine);
        }
        
        

        generateMinimum();

        // more walls
        int nmore = side;
        while (nmore > 0) {
            Cell cell1 = getCell(PointXY.random(side));
            Direction direction = Direction.random();
            Cell cell2 = cell1.getCell(direction);
            if (cell2 != null && cell1.isWall(cell2)) {
//                System.out.printf("removeWall(%s, %s)%n", cell1, cell2);
                Cell.removeWall(cell1, cell2);
                nmore--;
            }
        }
    }

    /**
     * PRIM Algorithm.
     * Remove the minumum number of walls to all cells will be connceted
     */
    private void generateMinimum() {
        Cell origen = getCell(PointXY.random(side));
//        origen.State(Color.CYAN);

        Set<Cell> joined = new HashSet<Cell>();
        joined.add(origen);

        java.util.List<Wall> walls = new ArrayList<Wall>();
        walls.addAll(origen.getWalls());

        while (walls.size() > 0) {
            int random = (int) (walls.size() * Math.random());
            Wall wall = walls.remove(random);
            Cell cell1 = wall.getCell1();
            Cell cell2 = wall.getCell2();
            if (cell2 == null)
                continue;
            if (!joined.contains(cell2)) {
                Cell.removeWall(cell1, cell2);
                joined.add(cell2);
                for (Wall wall1 : cell2.getWalls()) {
                    if (!walls.contains(wall))
                        walls.add(wall1);
                }
            }
        }
    }

    /**
     * Getter.
     *
     * @return number of cells in horizontal or vertical.
     */
    public int getSize() {
        return side;
    }

    /**
     * Getter.
     *
     * @return player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter.
     *
     * @return screen that controls the movements.
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Add an enemy to the list.
     *
     * @param enemy new enemy.
     */
    public void add(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Getter.
     *
     * @return list of enemies alive.
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Getter.
     *
     * @param point a point in the maze (row, column).
     * @return cell in that point.
     */
    public Cell getCell(PointXY point) {
        return cells[point.getX()][point.getY()];
    }

    /**
     * Getter.
     *
     * @param x abscissa (row).
     * @param y ordinate (column).
     * @return cell in that point.
     */
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    /**
     * Look for a connection from cell1 in the indicated direction.
     *
     * @param cell1    cell from movement in going to start.
     * @param direction direction of movement.
     * @return NULL if out of bounds or wall.<br>
     * 			If move, return the destination cell.
     */
    public Cell getConexion(Cell cell1, Direction direction) {
        if (cell1.isWall(direction))
            return null;
        Cell cell2 = cell1.getCell(direction);
        if (cell2 == null)
            return null;
        return cell2;
    }

    /**
     * Tell the user interface the is necessary to redraw.
     */
    public void draw() {
        gui.drawMe();
    }

    /**
     * Game over, player escaped the maze
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public void playerWin() throws FileNotFoundException, IOException {
    	double time = GUI.timer.elapsedTime();
    	int score =(int)time+Player.getMovements();
        gui.end("PLAYER WINS \n Score: " + score);
  	  	recordFile.writeFile(InitWindow.getLPlayerName(),InitWindow.getLDifficulty() ,InitWindow.getNumSize(), score);
    }

    /**
     * game over, payer was killed
     */
    public void playerLose() {
        gui.end("PLAYER ENDS");
    }
    
    /**
     * Generates a random position in the maze
     */
    private static int rndPos(){    	
    	return ((int) (Math.random() * InitWindow.getNumSize()) + 1);
    }

}
