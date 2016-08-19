package cluedoModel;

public class Suggestion {
	private final Player player;
	private final Weapon weapon;
	private final Room room;
	
	public Suggestion(Player player, Weapon weapon, Room room) {
		this.player = player;
		this.weapon = weapon;
		this.room = room;
	}

	@Override
	public String toString() {
		return "Suggestion [player=" + player.toString() + ", weapon=" + weapon.toString() + ", room=" + room.toString() + "]";
	}
	
	public Player getPlayer() {
		return player;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Room getRoom() {
		return room;
	}
	
}
