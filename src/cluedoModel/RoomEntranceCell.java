/**
 * Wrapper class for a doorway in the Cluedo board
 */

package cluedoModel;

public class RoomEntranceCell{
	private Room room;
	private final int row;
	private final int col;

	public RoomEntranceCell(Room room, int row, int col) {
		super();
		this.room = room;
		this.row = row;
		this.col = col;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}