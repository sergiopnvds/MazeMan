package com.pnvds.pacmac;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Graphic interface.
 *
 * @author Sergio Penavades
 * @version 09.01.2015
 */
public class GUI
        extends JPanel {
    /**
     * Game Name.
     */
    public static final String TITLE = "Maze";

    /**
     * Space between the game zone and the window edge.
     */
    private static final int MARGIN = 10;
    /**
     * Width of the game zone.
     */
    private static final int WIDTH = 500;
    /**
     * Cell size: pixels.
     */
    private int cell;
    
    /**
     * Maze size: cells.
     */
    private static int mazeSize;

    /**
     * Maze.
     */
    private static Maze maze;
    
    /**
     * Timer.
     */
    static Stopwatch timer;

    private void newGame() {
    	timer = new Stopwatch(); 
    	int newSize = InitWindow.getNumSize();
        cell = (WIDTH - 2 * MARGIN) / newSize;
        maze = new Maze(newSize, this);
        for (int i = 0; i < InitWindow.getNEnemies(); i++) {     	 
	            PointXY point = PointXY.random(newSize);
	            Cell cell = maze.getCell(point);
	            Enemy enemy = new Enemy(maze, cell, 300);
	            maze.add(enemy);
	            enemy.start();
        }		
        Player.setMovements(0);
        drawMe();
        
    }
    
    public static void setSize(int size){
    	mazeSize=size;
    }
    
    public static Maze getMaze(){
    	return maze;
    }

    private GUI(Container container) {
    	
        newGame();

        setPreferredSize(new Dimension(WIDTH, WIDTH));
        container.add(this, BorderLayout.CENTER);
        setFocusable(true);
        requestFocusInWindow();

        JToolBar toolBarS = new JToolBar();
        toolBarS.setFloatable(false);
        toolBarS.add(new CreateAction());
        toolBarS.add(new EnemyAction());
        toolBarS.add(new CancelAction());
        toolBarS.add(Box.createHorizontalGlue());
        container.add(toolBarS, BorderLayout.SOUTH);

        addKeyListener(new MyKeyListener());

        repaint();
     
    }

    /**
     * Builder.
     *
     * @param applet Applet.
     */
    public GUI(JApplet applet) {
        this(applet.getContentPane());
    }

    /**
     * Builder.
     *
     * @param frame terminal window.
     * @wbp.parser.constructor
     */
    public GUI(JFrame frame) {
        this(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Tell the thread of Swing that hash to refresh the window
     * Swing will do it when it can 
     */
    public void drawMe() {
        repaint();
    }

    /**
     * Call by Java to draw the window
     *
     * @param g graphic system to draw in @D
     */
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.LIGHT_GRAY);
        int nwx = MARGIN;
        int nwy = MARGIN;
        int side = maze.getSize();

        // draw finishLine cell
        drawFinishLine(g);

        // draw the other Cells
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                drawCell(g, x, y);
            }
        }

        // draw the frame
        g.setColor(Color.BLACK);
        g.drawLine(nwx - 1, nwy - 1, nwx - 1, nwy + side * cell + 1);
        g.drawLine(nwx + side * cell + 1, nwy - 1, nwx + side * cell + 1, nwy + side * cell + 1);
        g.drawLine(nwx - 1, nwy - 1, nwx + side * cell + 1, nwy - 1);
        g.drawLine(nwx - 1, nwy + side * cell + 1, nwx + side * cell + 1, nwy + side * cell + 1);
    }

    private void drawFinishLine(Graphics g) {
        PointXY objetive = maze.getPlayer().getFinishLine().getPoint();
        g.setColor(Color.YELLOW);
        int swx = sw_x(objetive.getX());
        int nwy = sw_y(objetive.getY() + 1);
        g.fillRect(swx, nwy, cell, cell);
    }

    /**
     * Draw a cell.
     *
     * @param g Graphic system to draw in 2D.
     * @param x column.
     * @param y row.
     */
    private void drawCell(Graphics g, int x, int y) {
        Cell cell = maze.getCell(x, y);

        // draw al cell walls
        g.setColor(Color.RED);
        if (cell.isWall(Direction.NORTH))
            g.drawLine(sw_x(x), sw_y(y + 1), sw_x(x + 1), sw_y(y + 1));
        if (cell.isWall(Direction.SOUTH))
            g.drawLine(sw_x(x), sw_y(y), sw_x(x + 1), sw_y(y));
        if (cell.isWall(Direction.EAST))
            g.drawLine(sw_x(x + 1), sw_y(y), sw_x(x + 1), sw_y(y + 1));
        if (cell.isWall(Direction.WEST))
            g.drawLine(sw_x(x), sw_y(y), sw_x(x), sw_y(y + 1));

        State state = cell.getState();
        if (state != null && state != State.EMPTY)
            fillCell(g, x, y, state.getColor());
    }

    /**
     * Draw the content of a Cell.
     *
     * @param g     graphic system to draw in 2D.
     * @param x     column.
     * @param y     row.
     * @param color color used to fill the cell.
     */
    private void fillCell(Graphics g, int x, int y, Color color) {
        int nwx = sw_x(x) + 3;
        int nwy = sw_y(y + 1) + 3;
        int dx = this.cell - 6;
        int dy = this.cell - 6;
        g.setColor(color);
        g.fillOval(nwx, nwy, dx, dy);
    }

    /**
     * Given a column, calculate the vertex down left.
     *
     * @param column column.
     * @return abscissa of the vertex down left.
     */
    private int sw_x(int column) {
        return MARGIN + column * cell;
    }

    /**
    * Given a row, calculate the vertex down left
     *
     * @param row row.
     * @return vertex down left
     */
    private int sw_y(int row) {
        int side = maze.getSize();
        return MARGIN + (side - row) * cell;
    }

    
    /**
     * Ends the game: print a message and generates a new game.
     *
     * @param msg explanation message.
     */
    public void end(String msg) {
        JOptionPane.showMessageDialog(this,
                msg, "Maze",
                JOptionPane.INFORMATION_MESSAGE);
        for (Enemy enemy : maze.getEnemies())
            enemy.interrupt();
        newGame();
    }

    /**
     * Button new interpreter.
     */
    private class CreateAction
            extends AbstractAction {
        /**
         * Builder.
         */
        public CreateAction() {
            super("New");
        }

        /**
         * Push action.
         *
         * @param event event that performs the action.
         */
        public void actionPerformed(ActionEvent event) {
            for (Enemy enemy : maze.getEnemies())
                enemy.interrupt();
//            int size = Integer.parseInt(arenaSide.getText());
            int size = InitWindow.getNumSize();
            newGame();         
            requestFocus();
        }
    }
    

    /**
     * Enemy button interpreter.
     */
    private class EnemyAction
            extends AbstractAction {
        /**
         * Builder.
         */
        public EnemyAction() {
            super("Enemy");
        }

        /**
         * Push action.
         *
         * @param event event that performs the action.
         */
        public void actionPerformed(ActionEvent event) {
            int size = maze.getSize();
            PointXY point = PointXY.random(size);
            Cell cell = maze.getCell(point);
            Enemy enemy = new Enemy(maze, cell, 300);
            maze.add(enemy);
            enemy.start();
            requestFocus();
        }
    }

    /**
     * Button cancel action.
     */
    private class CancelAction
            extends AbstractAction {
        /**
         * Builder.
         */
        public CancelAction() {
            super("Back");
        }

        /**
         * Push action.
         *
         * @param event event that performs the action.
         */
        public void actionPerformed(ActionEvent event) {
        	Pacman.getFrame().dispose();
            requestFocus();
        }
    }

    
    /**
     * Catch tecside.
     */
    private class MyKeyListener
            extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            Direction direction = getDirection(event);
            if (direction != null)
				try {
					maze.getPlayer().tryToMove(direction);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }

        private Direction getDirection(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_UP)
                return Direction.NORTH;
            if (ke.getKeyCode() == KeyEvent.VK_DOWN)
                return Direction.SOUTH;
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
                return Direction.EAST;
            if (ke.getKeyCode() == KeyEvent.VK_LEFT)
                return Direction.WEST;
            return null;
        }
    }
}
