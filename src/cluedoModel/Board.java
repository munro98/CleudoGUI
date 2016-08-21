/**
 * Stores the hardcoded Cluedo board.
 */

package cluedoModel;

import java.awt.Point;
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
		rooms.add(new Room("Kitchen", new Point(1, 2)));
		rooms.add(new Room("Ball Room", new Point(10, 3)));
		rooms.add(new Room("Conservatory", new Point(20, 3)));
		rooms.add(new Room("Billard Room", new Point(20, 8)));
		rooms.add(new Room("Library", new Point(20, 15)));
		rooms.add(new Room("Study", new Point(20, 22)));
		rooms.add(new Room("Hall", new Point(12, 20)));
		rooms.add(new Room("Lounge", new Point(1, 20)));
		rooms.add(new Room("Dining Room", new Point(1, 11)));
	}
	
	String[] boardText = { 
			"GGGGGGGGGNGGGGGNGGGGGGGGG",
			"RRRRRRNNNNRRRRNNNNNRRRRRR",
			"RRRRRRNNRRRRRRRRRNNRRRRRR",
			"RRRRRRNNRRRRRRRRRNNRRRRRR",
			"RRRRRRNNRRRRRRRRRNNRRRRRR",
			"RRRRRRNNRRRRRRRRENNNRRRRR",
			"RRRRERNNRERRRRRERNNNNNNNN",
			"NNNNNNNNRERRRRRERNNRRRRRR",
			"NNNNNNNNNNNNNNNNNNNRRRRRR",
			"RRRRRNNNNNNNNNNNNNNRRRRRR",
			"RRRRRRRRNNCCCCCCNNNRRRRRR",
			"RRRRRRRRNNCCCCCCNNNRRRRER",
			"RRRRRRRENNCCCCCCNNNNNNNNG",
			"RRRRRRRRNNCCCCCCNNNRRERRG",
			"RRRRRRRRNNCCCCCCNNRRRRRRR",
			"RRRRRRERNNCCCCCCNNRRRRRRR",
			"GNNNNNNNNNCCCCCCNNRRRRRRR",
			"NNNNNNNNNNNNNNNNNNNRRRRRG",
			"GNNNNNNNNRRRERRRNNNNNNNNN",
			"RRRRRRENNRRRRRRRNNNNNNNNN",
			"RRRRRRRNNRRRRRRENNNNNNNNN",
			"RRRRRRRNNRRRRRRRNNRRRRRRR",
			"RRRRRRRNNRRRRRRRNNRRRRRRR",
			"RRRRRRRNNRRRRRRRNNRRRRRRR",
			"RRRRRRGNGRRRRRRRGNGRRRRRR"};
	
	
	public Board() {
		boardCells = new Cell[SIZE][SIZE];
		boardSpecialCells = new RoomEntranceCell[SIZE][SIZE];
		playerCells = new Player[SIZE][SIZE];
		
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
		boardSpecialCells[12][21] = new RoomEntranceCell(rooms.get(4), 12, 21);
		rooms.get(4).addExit(boardSpecialCells[12][21]);
		boardSpecialCells[15][17] = new RoomEntranceCell(rooms.get(4), 15, 17);
		rooms.get(4).addExit(boardSpecialCells[15][17]);
		//Study
		boardSpecialCells[20][18] = new RoomEntranceCell(rooms.get(5), 20, 18);
		rooms.get(5).addExit(boardSpecialCells[20][18]);
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
	
	/**
	 * Returns the 2D cell array containing each square on the board.
	 * @return boardCells
	 */
	public Cell[][] getBoardCells() {
		return this.boardCells;
	}
	
	/**
	 * Returns the 2D array of the map, where each player currently is.
	 * @return playerCells
	 */
	public Player[][] getPlayerCells() {
		return this.playerCells;
	}
	
	/**
	 * Returns a 2D array containing each door location on the board.
	 * @return RoomEntranceCell[][]
	 */
	public RoomEntranceCell[][] getBoardEntranceCells() {
		return boardSpecialCells;
	}

	/**
	 * Returns the list of the rooms within the Cluedo board.
	 * @return rooms
	 */
	public static List<Room> getRooms() {
		return rooms;
	}

	/**
	 * Inserts the players into the 2D array for the board.
	 * @param activePlayers
	 */
	public void spawnPlayers(ArrayList<Player> activePlayers) {
		for (Player player : activePlayers) {
			this.playerCells[player.getStartX()][player.getStartY()] = player;
		}
		
	}
	
	/**
	 * Can the player move 1 square down?
	 * @param player
	 * @return boolean
	 */
	public boolean canMoveDown(Player player) {
		if (player.getRoom()!= null)
			return false;
		if (player.getX() < Board.SIZE - 1 && boardCells[player.getX()+1][player.getY()] == Cell.HALL 
				&& playerCells[player.getX()+1][player.getY()] == null) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Moves the player 1 square down
	 * @param player
	 */
	public void moveDown(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()+1][player.getY()] = player;
		player.setX(player.getX()+1);
		
	}
	
	/**
	 * Can the player move 1 square up?
	 * @param player
	 * @return boolean
	 */
	public boolean canMoveUp(Player player) {
		if (player.getRoom()!= null)
			return false;
		if (player.getX() > 0 && boardCells[player.getX()-1][player.getY()] == Cell.HALL
				&& playerCells[player.getX()-1][player.getY()] == null) {
			return true;
		}
		return false;
		
	}

	/**
	 * Moves the player 1 square up.
	 * @param player
	 */
	public void moveUp(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()-1][player.getY()] = player;
		player.setX(player.getX()-1);
		
	}
	
	/**
	 * Can the player move 1 square left?
	 * @param player
	 * @return boolean
	 */
	public boolean canMoveLeft(Player player) {
		if (player.getRoom()!= null)
			return false;
		if (player.getY() > 0 && boardCells[player.getX()][player.getY()-1] == Cell.HALL
				&& playerCells[player.getX()][player.getY()-1] == null) {
			return true;
		}
		return false;
		
	}

	/**
	 * Moves the player 1 square left
	 * @param player
	 */
	public void moveLeft(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()][player.getY()-1] = player;
		player.setY(player.getY()-1);
		
	}
	
	/**
	 * Moves the player 1 square right
	 * @param player
	 * @return boolean
	 */
	public boolean canMoveRight(Player player) {
		if (player.getRoom()!= null)
			return false;
		if (player.getY()  < Board.SIZE - 1 && boardCells[player.getX()][player.getY()+1] == Cell.HALL
				&& playerCells[player.getX()][player.getY()+1] == null) {
			return true;
		}
		return false;
		
	}

	/**
	 * Moves the player 1 square right
	 * @param player
	 */
	public void moveRight(Player player) {
		playerCells[player.getX()][player.getY()] = null;
		playerCells[player.getX()][player.getY()+1] = player;
		player.setY(player.getY()+1);
		
	}
	
	/**
	 * Can the player move into a room?
	 * @param player
	 * @return boolean
	 */
	public boolean canEnterRoom(Player player) {
		if (boardSpecialCells[player.getX()][player.getY()] != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * The room that player is in
	 * @param player
	 * @return Room
	 */
	public Room entranceRoom(Player player) {
		return boardSpecialCells[player.getX()][player.getY()].getRoom();
	}
	
	/**
	 * Moves the player into a room
	 * @param player
	 */
	public void enterRoom(Player player) {
		//if (boardSpecialCells[player.getX()][player.getY()] != null) {
		//}
		playerCells[player.getX()][player.getY()] = null;
		player.setRoom(boardSpecialCells[player.getX()][player.getY()].getRoom());
		//player.setY(-1);
		//player.setX(-1);
	}

	/**
	 * Moves the player out of a room into the corridor
	 * @param player
	 */
	public void exitRoom(Player player) {
		RoomEntranceCell cell = player.getRoom().getExit(0);
		playerCells[cell.getRow()][cell.getCol()] = player;
		player.setX(cell.getRow());
		player.setY(cell.getCol());
		player.setRoom(null);
		
	}

	/**
	 * Can the player move across the board using a secret corridor
	 * @param player
	 * @return
	 */
	public boolean canEnterStair(Player player) {
		if (player.getRoom() == null) {
			return false;
		}
		if (player.getRoom().getStairRoom() != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Moves the player into the opposite room
	 * @param player
	 */
	public void enterStair(Player player) {
		player.setRoom(player.getRoom().getStairRoom());
	}
	
	/**
	 * The current room containing a secret passageway the player is in
	 * @param player
	 * @return Room
	 */
	public Room stairRoom(Player player) {
		return player.getRoom().getStairRoom();
	}
	
	/**
	 * Can the player make a accusation?
	 * @param player
	 * @return boolean
	 */
	public boolean canMakeAccusation(Player player) {
		if (player.getX() > 8 && player.getX() < 18 && player.getY() > 8 && player.getY() < 17) {
			return true;
		}
		return false;
	}

	/**
	 * Removes player from play
	 * @param player
	 */
	public void remove(Player player) {
		playerCells[player.getX()][player.getY()] = null;
	}
	
}
