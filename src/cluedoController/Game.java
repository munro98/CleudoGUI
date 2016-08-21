package cluedoController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import cluedoModel.Board;
import cluedoModel.Card;
import cluedoModel.Player;
import cluedoModel.Room;
import cluedoModel.Suggestion;
import cluedoModel.Weapon;
import cluedoView.GUI;

public class Game {
	
	/*
	 
	draw cards
	draw players in the rooms
	draw room names
	JavaDoc comment eveywhere
	fill in any TODO areas
	
	� JTextField. These should be used to allow each player to enter their name.

	� Animations. Providing an animation of certain events in the game will make it more fun. For
	example, when a player�s token is being moved to another square, you might animate the motion
	rather than moving it there immediately. Similarly, you could have the tokens themselves be
	animated to perform different actions when different events happen to them (e.g. when going
	through a secret passage).
	 */

	public enum Option {UP, LEFT, DOWN, RIGHT, ENTER, EXIT, STAIR, END, ACCUSATION}

	public static final List<Weapon> weapons = new ArrayList<Weapon>();
	static {
		weapons.add(new Weapon("Candlestick"));
		weapons.add(new Weapon("Dagger"));
		weapons.add(new Weapon("Lead Pipe"));
		weapons.add(new Weapon("Revolver"));
		weapons.add(new Weapon("Rope"));
		weapons.add(new Weapon("Spanner"));
	}

	public static final ArrayList<Player> players = new ArrayList<Player>();
	static {
		players.add(new Player("Miss Scarlett", 0, 9));
		//players.add(new Player("Miss Scarlett", 7, 4));
		players.add(new Player("Colonel Mustard", 0, 15));
		players.add(new Player("Mrs. White", 6, 24));
		players.add(new Player("The Reverend Green", 19, 24));
		players.add(new Player("Mrs. Peacock", 24, 7));
		players.add(new Player("Professor Plum", 17, 0));
	}
	
	private ArrayList<Player> activePlayers;
	private ArrayList<Player> alivePlayers;
	
	
	private Board board;
	private GUI gui;
	private Suggestion solution;
	private Scanner input;
	
	private int selectedPlayerIndex;
	private Player selectedPlayer;
	private Random random; 
	private int dice;
	
	public Game() {
		this.board = new Board();
		
		
		gui = new GUI(this);
	
		//Get playerCount
		int playerCount = gui.askPlayerCount();
		
		activePlayers = new ArrayList<Player>();
		ArrayList<Player> avaliablePlayers = new ArrayList<>(players);
		
		// Let each player enter their name and select a character		
		for (int i = 0; i < playerCount; i++){
			Player p = gui.choosePlayer(avaliablePlayers, i+1);
			p.setPlayerName(gui.getText("Enter your name", p.getName()));
			activePlayers.add(p);
			avaliablePlayers.remove(p);
		}
		
		alivePlayers = new ArrayList<Player>(activePlayers);

		//Generate Solution
		random = new Random();
		Player randomPlayer = players.get(random.nextInt(players.size()));
		Weapon randomWeapon = weapons.get(random.nextInt(weapons.size()));
		int roomSize = board.getRooms().size();
		Room randomRoom = board.getRooms().get(random.nextInt(roomSize));
		
		solution = new Suggestion(randomPlayer, randomWeapon, randomRoom);
		// Debug Print solution
		System.out.println(solution.toString());
		
		{
			List<Card> cardsLeft = new LinkedList<Card>();
			// Get remaining cards
			for (Room room : board.getRooms()) {
				if (room != randomRoom)
					cardsLeft.add(room);
			}
			
			for (Weapon weapon : weapons) {
				if (weapon != randomWeapon)
					cardsLeft.add(weapon);
			}
			
			for (Player player : players) {
				if (player != randomPlayer)
					cardsLeft.add(player);
			}
			// Shuffle cards
			Collections.shuffle(cardsLeft);
			// Deal out the cards
			while(!cardsLeft.isEmpty()) {
				for (Player player : activePlayers) {
					if (cardsLeft.isEmpty())
						break;
					player.addCard(cardsLeft.remove(0));
				}
			}
		}
		
		board.spawnPlayers(activePlayers);
		gui.draw();
		
		selectedPlayerIndex = 0;
		selectedPlayer = activePlayers.get(selectedPlayerIndex);
		dice = random.nextInt(6) + 1;
		
		// Remind starting player it's their turn
		gui.dialog(selectedPlayer.getPlayerName() + "'s turn.\n" + selectedPlayer.getName() + " is now playing");
	}
	
	public void handleClick(int row, int col) {
		//Down
		if (row == selectedPlayer.getX() + 1 && col == selectedPlayer.getY()) {
			moveDown();
		//Up
		} else if (row == selectedPlayer.getX() - 1 && col == selectedPlayer.getY()) {
			moveUp();
		//Right
		} else if (row == selectedPlayer.getX() && col == selectedPlayer.getY() + 1) {
			moveRight();
		//Left
		} else if (row == selectedPlayer.getX() && col == selectedPlayer.getY() - 1) {
			moveLeft();
		}
	}
	
	public void endTurn() {
		if (dice == 0) {
			selectedPlayerIndex++;
			if (selectedPlayerIndex >= alivePlayers.size()) {
				selectedPlayerIndex = 0;
			}
			selectedPlayer = alivePlayers.get(selectedPlayerIndex);
			dice = random.nextInt(6) + 1;
			gui.dialog(selectedPlayer.getPlayerName() + "'s turn.\n" + selectedPlayer.getName() + " is now playing");
			gui.draw();
		} else {
			dice--;
		}
	}
	
	public void moveUp() {
		if (board.canMoveUp(selectedPlayer)) {
			board.moveUp(selectedPlayer);
			gui.draw();
			endTurn();
		}
	}
	
	public void moveDown() {
		if (board.canMoveDown(selectedPlayer)) {
			board.moveDown(selectedPlayer);
			gui.draw();
			endTurn();
		}
	}
	
	public void moveLeft() {
		if (board.canMoveLeft(selectedPlayer)) {
			board.moveLeft(selectedPlayer);
			gui.draw();
			endTurn();
		}
	}
	
	public void moveRight() {
		if (board.canMoveRight(selectedPlayer)) {
			board.moveRight(selectedPlayer);
			gui.draw();
			endTurn();
		}
		
	}
	
	/** if the player is not in a room try put then in a room else exit the room */
	public void enterExitRoom() {
		if (selectedPlayer.getRoom() == null) {
			if (board.canEnterRoom(selectedPlayer)) {
				board.enterRoom(selectedPlayer);
				gui.draw();
				endTurn();
			} else {
				gui.dialog("You must be on an entrance to do that!");
			}
		} else{
			board.exitRoom(selectedPlayer);
			gui.draw();
			endTurn();
		}
	}
	
	/*
	public void enterRoom() {
		if (board.canEnterRoom(selectedPlayer)) {
			board.enterRoom(selectedPlayer);
			gui.draw();
			endTurn();
		}
		
	}
	
	public void exitRoom() {
		if (selectedPlayer.getRoom() != null) {
			board.exitRoom(selectedPlayer);
			gui.draw();
			endTurn();
		}
		
	}
	*/
	
	public void enterStair() {
		if (board.canEnterStair(selectedPlayer)) {
			board.enterStair(selectedPlayer);
			gui.draw();
			endTurn();
		} else {
			gui.dialog("You must be in a corner room to do that!");
		}
		
	}
	
	public void skipTurn() {
		dice = 0;
		endTurn();
		
	}
	
	
	public void makeSuggestion() {
			Room room = selectedPlayer.getRoom();
			if (room == null) {
				gui.dialog("You must be in a room to do that!");
				return;
			}
			Player playerSelection = gui.selectPlayer(players);
			Weapon weaponSelection = gui.selectWeapon(weapons);
			
			System.out.println(playerSelection + " " + weaponSelection);

			for (Player p : activePlayers) {
				if (p.canRefute(room)) {
					System.out.println(p.getName() + " Can refute the murder room");
					gui.dialog(p.getName() + " Can refute the murder room");
					return;
				}
			}
			
			for (Player p : activePlayers) {
				if (p.canRefute(playerSelection)) {
					System.out.println(p.getName() + " Can refute the murderer");
					gui.dialog(p.getName() + " Can refute the murderer");
					return;
				}
			}
			
			for (Player p : activePlayers) {
				if (p.canRefute(weaponSelection)) {
					System.out.println(p.getName() + " Can refute the murder weapon");
					gui.dialog(p.getName() + " Can refute the murder weapon");
					return;
				}
			}
			gui.dialog("Suggestion cannot be refuted.");
			System.out.println("Suggestion cannot be refuted.");
		
	}
	
	public void makeAccusation() {
		
		Player accuser = selectedPlayer;

		Player player = playerSelection();
		Weapon weapon = weaponSelection();
		Room room = roomSelection();

		// see if they got the correct solution
		if (solution.getPlayer() == player
				&& solution.getWeapon() == weapon
				&& solution.getRoom() == room
				) {
			gui.dialog(accuser.getName() + " Has Won!");
			System.exit(0);
			return;
		}
		
		// Player failed
		alivePlayers.remove(accuser);
		board.remove(accuser);
		gui.dialog(accuser.getName() + " made a false accusation and promptly died of a brain aneurysm.");
		
		// See if all the players have lost
		if(alivePlayers.isEmpty()){
			gui.dialog("ALl players failed to find the killer.\nGAME OVER!");
			System.exit(0);
		}
		dice = 0;
		endTurn();
	}
	
	private Player playerSelection() {		
		return gui.selectPlayer(players);
	}
	
	private Weapon weaponSelection() {
		return gui.selectWeapon(weapons);
	}
	
	private Room roomSelection() {
		return gui.selectRoom(Board.getRooms());
	}

	public Board getBoard() {
		return board;
	}

	public int getDice() {
		return dice;
	}
}
