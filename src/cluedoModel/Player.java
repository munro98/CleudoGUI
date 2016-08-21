package cluedoModel;

import java.util.ArrayList;
import java.util.List;

public class Player implements Card{
	
	static int indexCounter = 0;
	
	private final String name;
	private String playerName;
	private List<Card> cards;
	private int X;
	private int Y;
	final private int startX;
	final private int startY;
	final private int index;
	
	private boolean inRoom;
	private Room room;
	
	

	public Player(String name, int startX, int startY) {
		this.name = name;
		this.cards = new ArrayList<Card>();
		this.startX = startX;
		this.startY = startY;
		this.X = startX;
		this.Y = startY;
		
		this.inRoom = false;
		this.setRoom(null);
		
		
		this.index = indexCounter;
		indexCounter++;
		
	}
	
	public void setPlayerName(String s){
		this.playerName = s;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public boolean canRefute(Card c) {
		return cards.contains(c);
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getIndex() {
		return index;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
