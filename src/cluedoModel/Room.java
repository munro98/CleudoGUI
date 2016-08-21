/**
 * Wrapper for a Room in the board<br>
 * <p>Contains the weapons, players, and if it has a 
 * Secret doorway inside.</p>
 */

package cluedoModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Room implements Card{
	private final String name;
	private final List<RoomEntranceCell> exits;
	private Room stairRoom;
	private Point location;

	public Room(String name, Point p) {
		this.name = name;
		this.exits = new ArrayList<RoomEntranceCell>();
		this.setStairRoom(null);
		this.location = p;
	}
	
	public void addExit(RoomEntranceCell cell) {
		this.exits.add(cell);
	}
	
	public RoomEntranceCell getExit(int i) {
		return this.exits.get(i);
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public Room getStairRoom() {
		return stairRoom;
	}

	public void setStairRoom(Room stairRoom) {
		this.stairRoom = stairRoom;
	}
	
	public Point getLocation(){
		return this.location;
	}
}
