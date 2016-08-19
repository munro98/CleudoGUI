package cluedoModel;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board{
	
	public static int SIZE = 25;
	
	public enum Cell {HALL, ROOM, START, STAIR, NONE, ENTRANCE, CENTER};
	private Cell[][] boardCells;
	
	private RoomEntranceCell[][] boardSpecialCells;
	
	private Player[][] playerCells;

	public static final List<Room> rooms = new ArrayList<Room>();
	static {
		rooms.add(new Room("Kitchen"));
		rooms.add(new Room("Ball Room"));
		rooms.add(new Room("Conservatory"));
		rooms.add(new Room("Billard Room"));
		rooms.add(new Room("Library"));
		rooms.add(new Room("Study"));
		rooms.add(new Room("Hall"));
		rooms.add(new Room("Lounge"));
		rooms.add(new Room("Dining Room"));
	}
	
	String[] boardText = { 
			"GGGGGGGGGNGGGGGNGGGGGGGGG",
			"RRRRRRNNNNRRRRNNNNNRRRRRR",
			"RRRRRRNNRRRRRRRRRNNRRRRRR",
			"RRRRRRNNRRRRRRRRRNNRRRRRR",
			"RRRRRRNNRRRRRRRRRNNRRRRRR",
			"RRRRRRNNERRRRRRRENNNRRRRR",
			"RRRRERNNRERRRRRERNNNNNNNN",
			"NNNNNNNNRERRRRRERNNRRRRRR",
			"NNNNNNNNNNNNNNNNNNNERRRRR",
			"RRRRRNNNNNNNNNNNNNNRRRRRR",
			"RRRRRRRRNNCCCCCCNNNRRRRRR",
			"RRRRRRRRNNCCCCCCNNNRRRRER",
			"RRRRRRRENNCCCCCCNNNNNNNNG",
			"RRRRRRRRNNCCCCCCNNNRRERRG",
			"RRRRRRRRNNCCCCCCNNRRRRRRR",
			"RRRRRRERNNCCCCCCNNRRRRRRR",
			"GNNNNNNNNNCCCCCCNNRRRRRRR",
			"GNNNNNNNNNNNNNNNNNNRRRRRG",
			"GNNNNNNNNRRRERRRNNNNNNNNN",
			"RRRRRRENNRRRRRRRNNNNNNNNG",
			"RRRRRRRNNRRRRRRENNRRRRRRR",
			"RRRRRRRNNRRRRRRRNNRRRRRRR",
			"RRRRRRRNNRRRRRRRNNRRRRRRR",
			"RRRRRRRNNRRRRRRRNNRRRRRRR",
			"RRRRRRGGGRRRRRRRGNGRRRRRR"};
	
	
	public Board() {
		boardCells = new Cell[SIZE][SIZE];
		boardSpecialCells = new RoomEntranceCell[SIZE][SIZE];
		playerCells = new Player[SIZE][SIZE];
		
		//try {
			//FileReader reader = new FileReader("src/board.txt");		
			//BufferedReader bufferedReader = new BufferedReader(reader);
			//ArrayList<String> lines = new ArrayList<String>();
			/*
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
				if (line.length() != SIZE) {
					throw new RuntimeException("Incorrect line length: " + line.length());
				}
			}
			if (lines.size() != SIZE) {
				throw new RuntimeException("Incorrect line count: " + lines.size());
			}
			*/
			
			String line;
			ArrayList<String> lines = new ArrayList<String>(Arrays.asList(boardText));
			
			for (int i = 0; i < boardCells.length; i++) {
				line = lines.get(i);
				for (int j = 0; j < boardCells[i].length; j++) {
					char c = line.charAt(j);
					
					switch (c) {
					case 'N':
						boardCells[i][j] = Cell.HALL;
						break;
					case 'R':
						boardCells[i][j] = Cell.ROOM;
						break;
					case 'G':
						boardCells[i][j] = Cell.NONE;
						break;
					case 'E':
						boardCells[i][j] = Cell.ENTRANCE;
						break;
					case 'C':
						boardCells[i][j] = Cell.CENTER;
						break;
					
					}
				}
			}
			
		//} catch (IOException e) {
			
		//}
		
		// I hope I made no mistakes here
		//Kitchen
		boardSpecialCells[7][4] = new RoomEntranceCell(rooms.get(0), 7, 4);
		rooms.get(0).addExit(boardSpecialCells[7][4]);
		
		//Ball Room
		boardSpecialCells[5][7] = new RoomEntranceCell(rooms.get(1), 5, 7);
		rooms.get(1).addExit(boardSpecialCells[5][7]);
		boardSpecialCells[8][9] = new RoomEntranceCell(rooms.get(1), 8, 9);
		rooms.get(1).addExit(boardSpecialCells[8][9]);
		boardSpecialCells[8][15] = new RoomEntranceCell(rooms.get(1), 8, 15);
		rooms.get(1).addExit(boardSpecialCells[8][15]);
		boardSpecialCells[5][17] = new RoomEntranceCell(rooms.get(1), 5, 17);
		rooms.get(1).addExit(boardSpecialCells[5][17]);
		//Conse
		boardSpecialCells[5][19] = new RoomEntranceCell(rooms.get(2), 5, 19);
		rooms.get(2).addExit(boardSpecialCells[5][19]);
		//
		boardSpecialCells[8][18] = new RoomEntranceCell(rooms.get(3), 8, 18);
		rooms.get(3).addExit(boardSpecialCells[8][18]);
		boardSpecialCells[12][23] = new RoomEntranceCell(rooms.get(3), 12, 23);
		rooms.get(3).addExit(boardSpecialCells[12][23]);
		//Library
		boardSpecialCells[16][16] = new RoomEntranceCell(rooms.get(4), 16, 16);
		rooms.get(4).addExit(boardSpecialCells[16][16]);
		boardSpecialCells[12][21] = new RoomEntranceCell(rooms.get(4), 12, 21);
		rooms.get(4).addExit(boardSpecialCells[12][21]);
		//Study
		boardSpecialCells[19][18] = new RoomEntranceCell(rooms.get(5), 19, 18);
		rooms.get(5).addExit(boardSpecialCells[19][18]);
		//Hall
		boardSpecialCells[17][12] = new RoomEntranceCell(rooms.get(6), 17, 12);
		rooms.get(6).addExit(boardSpecialCells[17][12]);
		boardSpecialCells[20][16] = new RoomEntranceCell(rooms.get(6), 20, 16);
		rooms.get(6).addExit(boardSpecialCells[20][16]);
		//Lounge
		boardSpecialCells[18][6] = new RoomEntranceCell(rooms.get(7), 18, 6);
		rooms.get(7).addExit(boardSpecialCells[18][6]);
		
		//Dining
		boardSpecialCells[12][8] = new RoomEntranceCell(rooms.get(8), 12, 8);
		rooms.get(8).addExit(boardSpecialCells[12][8]);
		boardSpecialCells[16][6] = new RoomEntranceCell(rooms.get(8), 16, 6);
		rooms.get(8).addExit(boardSpecialCells[16][6]);
		
		//Setup stair connections
		rooms.get(0).setStairRoom(rooms.get(5));
		rooms.get(5).setStairRoom(rooms.get(0));
		
		rooms.get(2).setStairRoom(rooms.get(7));
		rooms.get(7).setStairRoom(rooms.get(2));
	}
	
	//Print out the board
	public void draw () {
		
		StringBuilder boardString = new StringBuilder();
		for (int row = 0; row < Board.SIZE; row++){
			StringBuilder sb = new StringBuilder();
			for (int col = 0; col < Board.SIZE; col++){
				if (playerCells[row][col] != null) {
					sb.append("P"+playerCells[row][col].getIndex());
				} else {
					switch(boardCells[row][col]) {
					case HALL:
						sb.append(".");
						break;
					case NONE:
						sb.append("X");
						break;
					case ROOM:
						sb.append("R");
						break;
					case STAIR:
						sb.append("S");
						break;
					case START:
						sb.append("S");
						break;
					case ENTRANCE:
						sb.append("E");
						break;
					case CENTER:
						sb.append("C");
						break;
					default:
						break;
					}
					sb.append(" ");
				}
				
			}
			
			sb.append("\n");
			boardString.append(sb.toString());
		}
		System.out.println(boardString);
		
	}
	
	public Cell[][] getBoardCells() {
		return this.boardCells;
	}
	
	public Player[][] getPlayerCells() {
		return this.playerCells;
	}
	

	public RoomEntranceCell[][] getBoardEntranceCells() {
		return boardSpecialCells;
	}

	public static List<Room> getRooms() {
		return rooms;
	}

	public void spawnPlayers(ArrayList<Player> activePlayers) {
		for (Player player : activePlayers) {
			this.playerCells[player.getStartX()][player.getStartY()] = player;
		}
		
	}
	
	public boolean canMoveDown(Player player) {
		if (player.getX() < Board.SIZE - 1 && boardCells[player.getX()+1][player.getY()] == Cell.HALL 
				&& playerCells[player.getX()+1][player.getY()] == null) {
			return true;
		}
		return false;
		
	}

	public void moveDown(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()+1][player.getY()] = player;
		player.setX(player.getX()+1);
		
	}
	
	public boolean canMoveUp(Player player) {
		if (player.getX() > 0 && boardCells[player.getX()-1][player.getY()] == Cell.HALL
				&& playerCells[player.getX()-1][player.getY()] == null) {
			return true;
		}
		return false;
		
	}

	public void moveUp(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()-1][player.getY()] = player;
		player.setX(player.getX()-1);
		
	}
	
	public boolean canMoveLeft(Player player) {
		if (player.getY() > 0 && boardCells[player.getX()][player.getY()-1] == Cell.HALL
				&& playerCells[player.getX()][player.getY()-1] == null) {
			return true;
		}
		return false;
		
	}

	public void moveLeft(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()][player.getY()-1] = player;
		player.setY(player.getY()-1);
		
	}
	
	public boolean canMoveRight(Player player) {
		if (player.getY()  < Board.SIZE - 1 && boardCells[player.getX()][player.getY()+1] == Cell.HALL
				&& playerCells[player.getX()][player.getY()+1] == null) {
			return true;
		}
		return false;
		
	}

	public void moveRight(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()][player.getY()+1] = player;
		player.setY(player.getY()+1);
		
	}
	
	public boolean canEnterRoom(Player player) {
		if (boardSpecialCells[player.getX()][player.getY()] != null) {
			return true;
		}
		return false;
	}
	
	public Room entranceRoom(Player player) {
		return boardSpecialCells[player.getX()][player.getY()].getRoom();
	}
	
	public void enterRoom(Player player) {
		//if (boardSpecialCells[player.getX()][player.getY()] != null) {
		//}
		playerCells[player.getX()][player.getY()] = null;
		player.setRoom(boardSpecialCells[player.getX()][player.getY()].getRoom());
		//player.setY(-1);
		//player.setX(-1);
	}

	public void exitRoom(Player player) {
		RoomEntranceCell cell = player.getRoom().getExit(0);
		playerCells[cell.getRow()][cell.getCol()] = player;
		player.setX(cell.getRow());
		player.setY(cell.getCol());
		player.setRoom(null);
		
	}

	public boolean canEnterStair(Player player) {
		if (player.getRoom().getStairRoom() != null) {
			return true;
		}
		return false;
	}
	
	public void enterStair(Player player) {
		player.setRoom(player.getRoom().getStairRoom());
	}
	
	public Room stairRoom(Player player) {
		return player.getRoom().getStairRoom();
	}
	
	public boolean canMakeAccusation(Player player) {
		if (player.getX() > 8 && player.getX() < 18 && player.getY() > 8 && player.getY() < 17) {
			return true;
		}
		return false;
	}

	public void remove(Player player) {
		playerCells[player.getX()][player.getY()] = null;
	}
	
}
