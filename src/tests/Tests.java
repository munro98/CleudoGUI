package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cluedoModel.Board;
import cluedoModel.Player;

public class Tests {
	
	private Board board;
	
	public ArrayList<Player> players;

	private void setupTestGame() {
		this.board = new Board();
		
		players = new ArrayList<Player>();
		players.add(new Player("Miss Scarlett", 8, 0));
		players.add(new Player("Colonel Mustard", 0, 15));
		players.add(new Player("Mrs. White", 6, 24));
		
		board.spawnPlayers(players);
	}
	
	private void setupTestGame2() {
		this.board = new Board();
		
		players = new ArrayList<Player>();
		players.add(new Player("Miss Scarlett", 7, 4));
		
		board.spawnPlayers(players);
	}
	
	//Moving across the board
	public @Test void test1() {
		setupTestGame();
		
		while (board.canMoveRight(players.get(0))) {
			board.moveRight(players.get(0));
			board.draw();
		}

		assertTrue(players.get(0).getX() == 8);
		assertTrue(players.get(0).getY() == 18);
		
	}
	
	//Moving across the board 2
	public @Test void test2() {
		setupTestGame();
		
		while (board.canMoveLeft(players.get(2))) {
			board.moveLeft(players.get(2));
			board.draw();
		}
		
		while (board.canMoveDown(players.get(2))) {
			board.moveDown(players.get(2));
			board.draw();
		}

		assertTrue(players.get(2).getX() == 24);
		assertTrue(players.get(2).getY() == 17);
		
	}
	
	//Player collision
	public @Test void test3() {
		setupTestGame();
		
		Player p2 = players.get(2);
		
		board.moveLeft(p2);
		board.draw();
		board.moveLeft(p2);
		board.draw();
		board.moveLeft(p2);
		board.draw();
		board.moveLeft(p2);
		board.draw();
		board.moveLeft(p2);
		board.draw();
		board.moveLeft(p2);
		board.draw();
		board.moveDown(p2);
		board.draw();
		board.moveDown(p2);
		board.draw();


		while (board.canMoveRight(players.get(0))) {
			board.moveRight(players.get(0));
			board.draw();
		}

		assertTrue(players.get(0).getX() == 8);
		assertTrue(players.get(0).getY() == 17);
		
	}
	
	//Entering/Exiting Room
	public @Test void test4() {
		setupTestGame();
		
		while (board.canMoveRight(players.get(0))) {
			board.moveRight(players.get(0));
			board.draw();
		}

		
		board.enterRoom(players.get(0));
		board.draw();
		assertTrue(players.get(0).getRoom() != null);
		board.exitRoom(players.get(0));
		board.draw();
		assertTrue(players.get(0).getRoom() == null);
		
	}
	
	//Using stairs
	public @Test void test5() {
		setupTestGame2();
		
		board.enterRoom(players.get(0));
		board.draw();
		assertTrue(players.get(0).getRoom() != null);
		board.enterStair(players.get(0));
		board.draw();
		board.exitRoom(players.get(0));
		board.draw();
		
		assertTrue(players.get(0).getX() == 20);
		assertTrue(players.get(0).getY() == 18);
		
	}

}

