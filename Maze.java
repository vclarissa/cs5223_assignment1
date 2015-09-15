//my Maze
package com.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.ds.server.MazeGameClass;
import com.ds.server.PlayerClass;
import com.ds.server.mCell;

import model.Player;
import model.Point;

public class Maze implements Serializable{
	
	
	//dont know what this is for
	private static final long serialVersionUID = 356185318404742910L;
	
	private int treasures;
	private int mazeSize;
	private int treasureCollected;
	
	private int[][] treasureCells;
	private String[][] playerCells;
	//private int NO_OF_TREASURES, SIZE_OF_MAZE, NO_OF_PLAYERS;
	/*
	String backupserverIp = null;
	String serverIp = null;
	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	int portno;
	int backupPort;
	int backupId;
	*/
	//private ArrayList<PlayerClass> players;
	
	public void intializeMaze(int m, int n) {
		treasures = m;
		mazeSize = n;
		playersCount = 0;
		treasureCollected = 0;
		//Need to initialize players
		//players = new ArrayList<PlayerClass>();
		//players = new Hashtable<String, PlayerClass>();
		
		//initialize treasure and player cells
		treasureCells = new int[mazeSize][mazeSize];
		playerCells = new String[mazeSize][mazeSize];
		
		for(int i = 0; i < mazeSize; i++){
			for(int j = 0; j < mazeSize; j++){
				treasureCells[i][j] = 0;
				playerCells[i][j] = null;
			}
		}
		
	}
	
	public void fillTreasures() {

		Random randomGenerator = new Random();
		int posX;
		int posY;
		int treasureAllocated = 0;
		int randomTreasure;

		//while number of treasure allocated still less than number of treasure available
		while(treasureAllocated < treasures) {
			//get random location of the map to fill treasure
			do {
			posX = randomGenerator.nextInt(mazeSize);
			posY = randomGenerator.nextInt(mazeSize);
			} while (treasureCells[posX][posY].isTreasureKept());
			//isTreasureKept() is not created
			
			
			//generate random variable to fill in multiple treasures in one cell
			if(treasures - treasureAllocated > 5){
				randomTreasure = randomGenerator.nextInt(5);
			}
			else{
				randomTreasure = randomGenerator.nextInt(treasures - treasureAllocated + 1);
			}
			
			//fill in the treasure
			if(randomTreasure > 0){
				treasureAllocated += randomTreasure;
				treasureCells[posX][posY] += randomTreasure;
			}
		}
		
	}
	
	public void printTreasureStatus() {
		for(int i = 0; i < mazeSize; i++){
			for(int j = 0; j < mazeSize; j++){
				System.out.println("--------Game State-----------");
				System.out.println("Available treasure: " + treasures);
				System.out.println("Collected treasure: " + (treasures-treasureCollected));
			}
		}
		
	}


	//function to put players in cells
	public String addNewPlayer(){
		String pid = null;
		Player newPlayer = null;
		
		//generate unique Id
		id = Integer.toString(players.size());
		
		//generate random position
		Random randomGenerator = new Random();
		int posX = 0;
		int posY = 0;
		boolean validPosition = false;
		
		while(!validPosition){
			x = randomGenerator.nextInt(mazeSize);
			y = randomGenerator.nextInt(mazeSize);
			
			if(playerCells[x][y] == null){
				playerCells[x][y] = id;
				validPosition = true;
			}
		}
		
		//Need to create class Player
		newPlayer = new Player(id,new Point(x,y));
		//newPlayer.setOnline(true);
		players.put(newPlayer.getId(), newPlayer);
		return newPlayer.getId();
		
		/* config if there is treasures inside players map
		players.get(pNo - 1).setTREASURES_COLLECTED(
				mCells[randomIntForCell].getNoOfTreasuresInaCell());
		NO_OF_TREASURES_LEFT = NO_OF_TREASURES_LEFT
				- mCells[randomIntForCell].getNoOfTreasuresInaCell();
		mCells[randomIntForCell].setOccupied(true);
		mCells[randomIntForCell].setNoOfTreasuresInaCell(0);
		mCells[randomIntForCell].setTreasureKept(false);
		mCells[randomIntForCell].setPlayerInCell(pId);
		*/
		
	}
	
	
	public int getRemainingTreasure(){
		return treasures;
	}
	
	/*
	private boolean isPositionValid(Point position){
		if(position.getX() >= horizontalSize || position.getX() < 0 || position.getY() >= verticalSize || position.getY() < 0){
			return false;
		}
		return true;
	}
	
	private boolean isNewPositionValid(Point newPos){
		if(newPos != null && isPositionValid(newPos)){
			if(playerMap[newPos.getX()][newPos.getY()] == null){
				return true;
			} else {
				return false;
			}
		}else{
			return false;
		}
	}
	
	*/
	

	/* function to move
	public MazeGameClass move(int pId, String direction) {

		int pNo = pId_pNo_hashmap.get(pId);
		int oldLocation = players.get(pNo - 1).getPLAYER_CELL_NO();
		int newLocation;
		switch (direction.toUpperCase()) {
		case "A":
			if (oldLocation % SIZE_OF_MAZE != 0) {
				newLocation = oldLocation - 1;
				if (!mCells[newLocation].isOccupied()) {
					NO_OF_TREASURES_LEFT = NO_OF_TREASURES_LEFT
							- mCells[newLocation].getNoOfTreasuresInaCell();
					mCells[oldLocation].setOccupied(false);
					mCells[newLocation].setOccupied(true);
					mCells[oldLocation].setTreasureKept(false);
					mCells[newLocation].setTreasureKept(false);
					mCells[oldLocation].setPlayerInCell(0);
					players.get(pNo - 1).setPLAYER_CELL_NO(newLocation);
					checkLocToGetTreasureAndUpdatePlayer(oldLocation,
							newLocation, pId);
					mCells[newLocation].setPlayerInCell(pId);

				}
			}
			break;
		case "W":
			if (oldLocation > SIZE_OF_MAZE - 1) {
				newLocation = oldLocation - SIZE_OF_MAZE;
				if (!mCells[newLocation].isOccupied()) {
					NO_OF_TREASURES_LEFT = NO_OF_TREASURES_LEFT
							- mCells[newLocation].getNoOfTreasuresInaCell();
					mCells[oldLocation].setOccupied(false);
					mCells[newLocation].setOccupied(true);
					mCells[oldLocation].setTreasureKept(false);
					mCells[newLocation].setTreasureKept(false);
					mCells[oldLocation].setPlayerInCell(0);
					players.get(pNo - 1).setPLAYER_CELL_NO(newLocation);
					checkLocToGetTreasureAndUpdatePlayer(oldLocation,
							newLocation, pId);
					mCells[newLocation].setPlayerInCell(pId);

				}
			}
			break;
		case "S":
			if (oldLocation < (SIZE_OF_MAZE * SIZE_OF_MAZE - SIZE_OF_MAZE)) {
				newLocation = oldLocation + SIZE_OF_MAZE;
				if (!mCells[newLocation].isOccupied()) {
					NO_OF_TREASURES_LEFT = NO_OF_TREASURES_LEFT
							- mCells[newLocation].getNoOfTreasuresInaCell();
					mCells[oldLocation].setOccupied(false);
					mCells[newLocation].setOccupied(true);
					mCells[oldLocation].setTreasureKept(false);
					mCells[newLocation].setTreasureKept(false);
					mCells[oldLocation].setPlayerInCell(0);
					players.get(pNo - 1).setPLAYER_CELL_NO(newLocation);
					checkLocToGetTreasureAndUpdatePlayer(oldLocation,
							newLocation, pId);
					mCells[newLocation].setPlayerInCell(pId);

				}
			}
			break;
		case "D":
			if (oldLocation % SIZE_OF_MAZE != SIZE_OF_MAZE - 1) {
				newLocation = oldLocation + 1;
				if (!mCells[newLocation].isOccupied()) {
					NO_OF_TREASURES_LEFT = NO_OF_TREASURES_LEFT
							- mCells[newLocation].getNoOfTreasuresInaCell();
					mCells[oldLocation].setOccupied(false);
					mCells[newLocation].setOccupied(true);
					mCells[oldLocation].setTreasureKept(false);
					mCells[newLocation].setTreasureKept(false);
					mCells[oldLocation].setPlayerInCell(0);
					players.get(pNo - 1).setPLAYER_CELL_NO(newLocation);
					checkLocToGetTreasureAndUpdatePlayer(oldLocation,
							newLocation, pId);
					mCells[newLocation].setPlayerInCell(pId);
				}
			}
			break;
		case "N":
			break;
		default:
			break;
		}
		return this;
	}
	*/
	
}
