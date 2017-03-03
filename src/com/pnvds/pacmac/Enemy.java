package com.pnvds.pacmac;


public class Enemy extends Thread {
	
	 private final Maze maze;
	 private Cell cell;
	 private int dt;
	 
	 /**
	 * 
	 * Builder.
	 *
	 * @param maze maze in action.
	 * @param cell initial position.
	 * @param dt delta of time to move.
	 */
	public Enemy(Maze maze, Cell cell, int dt){
		
		this.maze = maze;
        this.cell = cell;
        this.dt=dt;	
	}

	/** Getter.
	*
	* @return where is now.
	*/
	 public Cell getCell() {
		 
	        return cell;
	 }
	 
	 /**
	 * Try to move in the indicated direction.
	 *
	 * @param direction desired for the movement.
	 * @throws InterruptedException if there is an interruption.
	 */
	 public void tryToMove(Direction direction)
			 throws InterruptedException {
			 // use maze.getConexion(direction) to what cell would go
			 // if it is no possible(cell = null), nothing done
			 // call screem.moveEnemy(cell)
			 // if playerKilled, call a maze.playeLose()
			 // if interruption, nothing donde
		 Cell temporary=maze.getConexion(cell,direction);
		 if (temporary==null) return;
		 Screen screen=maze.getScreen();
		 
		 try {
			screen.moveEnemy(this, temporary);
		} catch (PlayerKilled e) {
			maze.playerLose();
		} 
		 
	 }
	 /**
	 * Setter.
	 *
	 * @param new cell to move.
	 */
	 public void setCell(Cell newCell) {
	 // put State.EMPTY in the previous cell
	 // put State.ENEMY in the new cell
	 //  put the new cell in the filed cell 
	 // claa maze.draw() to refresh the window
		 Cell previous = this.cell;
		 previous.setState(com.pnvds.pacmac.State.EMPTY);
	     newCell.setState(com.pnvds.pacmac.State.ENEMY);
	     this.cell = newCell;
	     maze.draw();
	 }
	 
	 /**
	 * Enemy activity.
	 */
	 @Override
	 public void run() {
		 while(true){
			 Direction dNew=Direction.random();
			 try {
				tryToMove(dNew);
				sleep(dt);
			} catch (InterruptedException e) {
				return;
			}
			
		 }
		 
		 
	 // an infinite loop may be interrupted by InterruptedException
	 // use Direction.random() to have the movement direction
	 // call tryToMove(direction)
	 //sleep a little bit (dt)
	 }

	 
	 
}
