//myPlayer
package com.server;

import java.io.Serializable;

public class Player implements Serializable{

	//private static final long serialVersionUID = 156185318404742910L;
	
	private String playerId;
	private String ipAddress;
	private int playerPosX;
	private int playerPosY;
	private int treasuresCollected;
		
	public Player(String id){
		this.playerId = id;
		this.playerPosX = 0;
		this.playerPosY = 0;
		treasureCollected = 0;
	}
	
	public Player(String id, int initPosX, int initPosY){
		this.playerId = id;
		playerPosX = initPosX;
		playerPosY = initPosY;
		treasureCollected = 0;
	}
	
	public String getPlayerid() {
		return playerId;
	}

	public Point getPositionX() {
		return playerPosX;
	}
	
	public Point getPositionY() {
		return playerPosY;
	}
	
	public void setPosition(int posX, int posY) {
		this.playerPosX = posX;
		this.playerPosY = posY;
	}
	
	public int getTreasureCollected() {
		return treasureCollected;
	}
	
	public void setTreasureCollected(int treasureCollected) {
		this.treasureCollected = treasureCollected;
	}

	public String getIpaddress() {
		return ipAddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipAddress = ipaddress;
	}
}