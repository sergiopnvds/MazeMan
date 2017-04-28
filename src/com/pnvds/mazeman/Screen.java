package com.pnvds.mazeman;


public class Screen {
	/**
	* Try to move a player to cell2.
	* If can not, nothing done.
	* if can move, use setCell().
	*
	* @param player reference to a player.
	* @param cell2 cell target to move.
	* @throws PlayerKilled if crash with an enemy.
	*/
	public synchronized void movePlayer(Player player, Cell cell2)
	throws PlayerKilled {
		
		if(cell2.getState()==State.EMPTY)
			player.setCell(cell2);
		else if(cell2.getState()==State.BLUE)
			return;
		else if(cell2.getState()==State.ENEMY)
			throw new PlayerKilled();
	}
	/**
	* Try to move enemy to cell2.
	* If can not, nothing done.
	* If yes, put cell with setCell().
	*
	* @param enemy reference to enemy.
	* @param cell2 cell where the enemy wants to move.
	* @throws PlayerComido is crash with a player.
	* @throws InterruptedException is interruption.
	*/
	public synchronized void moveEnemy(Enemy enemy, Cell cell2)
	throws PlayerKilled, InterruptedException {
		
		if(cell2.getState()==State.EMPTY)
			enemy.setCell(cell2);
		else if(cell2.getState()==State.BLUE)
			throw  new PlayerKilled();
		else if(cell2.getState()==State.ENEMY)
			return;
		
	}
	}
	
	


