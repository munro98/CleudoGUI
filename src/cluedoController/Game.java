package cluedoController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import cluedoModel.Board;
import cluedoModel.Card;
import cluedoModel.Player;
import cluedoModel.Room;
import cluedoModel.Suggestion;
import cluedoModel.Weapon;
import cluedoView.GUI;

public class Game {
	
	/*
	 
	JavaDoc comment eveywhere
	add MouseListener on canvas
	draw cards
	
	• JTextField. These should be used to allow each player to enter their name.
	
	• Short-Cut Keys. Modern GUIs provide short-cut keys enabling experienced users to operate
	the program more efficient. For example, a short-cut key could be used to start the next turn,
	and to access menu items. A KeyListener is needed to implement this.
	
	• Animations. Providing an animation of certain events in the game will make it more fun. For
	example, when a player’s token is being moved to another square, you might animate the motion
	rather than moving it there immediately. Similarly, you could have the tokens themselves be
	animated to perform different actions when different events happen to them (e.g. when going
	through a secret passage).
	
	draw players in the rooms
	draw room names
	clean up board A E S T H I C S
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
		//int playerCount = gui.askPlayerCount();
		int playerCount = 2;
		
		System.out.println(playerCount);
		System.out.println("new Game!");
		
		activePlayers = new ArrayList<Player>();
		for (int i = 0; i < playerCount; i++)
			activePlayers.add(players.get(i));
		
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
		
		/*

		//while (this.isRunning) {
		while (this.isRunning && alivePlayers.size() > 1) {
			for (int j = 0; j < alivePlayers.size(); j++) {
				Player player = alivePlayers.get(j);
				// Start player turn
				int diceRoll = random.nextInt(6) + 1;
				System.out.println(player.getName() + "(P" + player.getIndex() + ")" + " is now playing");
				System.out.println("The dice has rolled " + diceRoll + "!");
				
				Room roomToEnter = null;
				
				for (;diceRoll > 0; diceRoll--) {
					board.draw();
					
					
					//System.out.println( "" + player.getX() + " " + player.getY());
					
					ArrayList<Option> options = new ArrayList<Option>();
					// Generate choices
					//if (player.getX() > 0) { // can move up
					if (player.getRoom() == null) {
						if (board.canMoveUp(player)) {
							//board.g
							options.add(Option.UP);
						}
						
						if (board.canMoveDown(player)) {
							options.add(Option.DOWN);
						}
						
						if (board.canMoveLeft(player)) {
							options.add(Option.LEFT);
						}
						
						if (board.canMoveRight(player)) {
							options.add(Option.RIGHT);
						}
						
						if (board.canEnterRoom(player)) {
							options.add(Option.ENTER);
							roomToEnter = board.entranceRoom(player);
						}
						if (board.canMakeAccusation(player)) {
							options.add(Option.ACCUSATION);
						}
					} else {
							options.add(Option.EXIT);
							if (board.canEnterStair(player)) {
								options.add(Option.STAIR);
								roomToEnter = board.stairRoom(player);
							}
					}
					
					options.add(Option.END);
					
					
					System.out.println("Pick a move(" + diceRoll + " left):");
					for (int i = 0; i < options.size(); i++) {
						switch(options.get(i)) {
							case UP: {
								System.out.println("Up("+ i + ")");
								break;
							}
							case DOWN: {
								System.out.println("Down("+ i + ")");
								break;
							}
							case LEFT: {
								System.out.println("Left("+ i + ")");
								break;
							}
							case RIGHT: {
								System.out.println("Right(" + i + ")");
								break;
							}
							case ENTER: {
								System.out.println("Enter " + roomToEnter.getName()+ "(" + i + ")");
								break;
							}
							case STAIR: {
								System.out.println(roomToEnter);
								System.out.println("Stair to " + roomToEnter.getName()+ "(" + i + ")");
								break;
							}
							case EXIT: {
								System.out.println("Exit " + player.getRoom().getName() + "(" + i + ")");
								break;
							}
							case END: {
								System.out.println("End turn(" + i + ")");
								break;
							}
							case ACCUSATION: {
								System.out.println("Make accusation(" + i + ")");
								break;
							}
							default:
								break;
							}
					}
					
					// Get choice from user
					int playerOption = -1;
					while (!(playerOption >= 0 && playerOption < options.size())) {
						String inputString;
						try {
							inputString = input.next();
							playerOption = Integer.parseInt(inputString);
						} catch (InputMismatchException e) {
						} catch (NumberFormatException e) {
						} finally {
							if (!(playerOption >= 0 && playerOption < options.size()))
								System.out.println("Enter a valid choice:");
						}
					}
					
					// Execute move
					switch(options.get(playerOption)) {
						case UP: {
							board.moveUp(player);
							break;
						}
						case DOWN: {
							board.moveDown(player);
							break;
						}
						case LEFT: {
							board.moveLeft(player);
							break;
						}
						case RIGHT: {
							board.moveRight(player);
							break;
						}
						case ENTER: {
							board.enterRoom(player);
							makeSuggestion(player);
							break;
						}
						case EXIT: {
							board.exitRoom(player);
							break;
						}
						case STAIR: {
							board.enterStair(player);
							makeSuggestion(player);
							break;
						}
						case END: {
							diceRoll = 0;
							break;
						}
						case ACCUSATION: {
							makeAccusation(player);
							diceRoll = 0;
							break;
						}
						default:
							break;
						}
					}
				
			}
			
			
		}
		System.out.println(alivePlayers.get(0).getName() + " is last alive and has Won!");
		*/
		
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
	
	public void enterStair() {
		if (board.canEnterStair(selectedPlayer)) {
			board.enterStair(selectedPlayer);
			gui.draw();
			endTurn();
		}
		
	}
	
	public void skipTurn() {
		dice = 0;
		endTurn();
		
	}
	
	public void makeSuggestion(Player player) {
		System.out.println("Would you like to make a suggestion?(y/n):");
		int playerOption = -1;
		while (playerOption != 1 && playerOption != 2) {
			String inputString;
			try {
				inputString = input.next();
				//System.out.println(inputString);
				if (inputString.toLowerCase().charAt(0) == 'y') {
					playerOption = 1;
				} else if (inputString.toLowerCase().charAt(0) == 'n') {
					playerOption = 2;
				}
			} catch (InputMismatchException e) {
			} catch (NumberFormatException e) {
			} finally {
				if (playerOption != 1 && playerOption != 2)
					System.out.println("Enter a valid choice:");
			}
		}
		
		if (playerOption == 1) {
			
			//make suggestion
			Room room = player.getRoom();
			
			//TODO
			int playerSelection = 1;//playerSelection();
			int weaponSelection = 1;//weaponSelection();

			//System.out.println(players.get(playerSelection));
			//System.out.println(weapons.get(weaponSelection));
			
			//Suggestion suggestion = new Suggestion(players.get(playerSelection), weapons.get(weaponSelection), room);
			
			for (Player p : activePlayers) {
				if (p.canRefute(room)) {
					System.out.println(p.getName() + " Can refute the murder room");
					return;
				}
			}
			
			for (Player p : activePlayers) {
				if (p.canRefute(players.get(playerSelection))) {
					System.out.println(p.getName() + " Can refute the murderer");
					return;
				}
			}
			
			for (Player p : activePlayers) {
				if (p.canRefute(weapons.get(weaponSelection))) {
					System.out.println(p.getName() + " Can refute the murder weapon");
					return;
				}
			}
			
			System.out.println("Suggestion cannot be refuted.");
			
		}
		
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
		return gui.selectWeapon((ArrayList)weapons);
	}
	
	private Room roomSelection() {
		return gui.selectRoom((ArrayList)board.getRooms());
	}

	public Board getBoard() {
		return board;
	}

	public int getDice() {
		return dice;
	}
}
