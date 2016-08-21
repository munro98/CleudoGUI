/**
 * <h1>
 * Displaying the Cluedo board.
 * </h1>
 * <b>Colours:<br></b>
 * <ul>
 * <li>Peach: Player. Active players have their number next to them</li>
 * <li>Blue: Door</li>
 * <li>White: Room</li>
 * <li>Yellow: Corridor</li>
 * <li>Green: Wall</li>
 * </ul>
 * 
 */

package cluedoView;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import cluedoController.Game;
import cluedoModel.Board;
import cluedoModel.RoomEntranceCell;
import cluedoModel.Board.Cell;

class CleudoCanvas extends Canvas implements MouseListener, KeyListener{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private Game game;
	private int scale;
	BufferStrategy bufferStrategy;
			
	public CleudoCanvas(Game game) {
		super();
		
		this.game = game;
		this.setBackground(new Color(255, 255, 255));

		bufferStrategy = null;
		
		addMouseListener(this);
		addKeyListener(this);
	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	@Override
	public void repaint() {
		paint(this.getGraphics());
		if (bufferStrategy == null) {
			createBufferStrategy(3);
			bufferStrategy = getBufferStrategy();
		}
	}

	@Override
	public void paint(Graphics g) {
		
		Graphics graphics = null;
		if (bufferStrategy != null) {
			graphics = bufferStrategy.getDrawGraphics();
			g = graphics;
		}
		
		
		
		Dimension size = this.getSize();
		double minDimension = Math.min(size.getWidth(), size.getHeight());
		scale = (int) (minDimension / Board.SIZE);
		
		Graphics2D g2 = (Graphics2D) g;
		Cell[][] boardCells = game.getBoard().getBoardCells();
		RoomEntranceCell[][] boardSpecialCells = game.getBoard().getBoardEntranceCells();
		
		g2.setColor(new Color(0, 0, 0));
		g2.draw(new Rectangle(0, 0, scale * Board.SIZE, scale * Board.SIZE));

		for (int row = 0; row < Board.SIZE; row++){
			for (int col = 0; col < Board.SIZE; col++){
				
				if (game.getBoard().getPlayerCells()[row][col] != null){ 
					g2.setColor(new Color(255, 235, 200));
					g2.fill(new Rectangle(scale * col, scale * row, scale, scale));
					
					g2.setColor(new Color(0, 0, 0));
					g2.drawString("P"+game.getBoard().getPlayerCells()[row][col].getIndex(), scale * col, scale * row);
				} else {
					switch(boardCells[row][col]) {
						case HALL:
							if (boardSpecialCells[row][col] == null)
								g2.setColor(new Color(255, 235, 100));
							else
								g2.setColor(new Color(80, 80, 255)); // Blue
							break;
						case CENTER:
							g2.setColor(new Color(255, 235, 100));
							break;
						case NONE:
							g2.setColor(new Color(100, 235, 100));
							break;
						case ROOM:
							g2.setColor(new Color(255, 255, 255)); // White
							break;
						case STAIR:
							break;
						case START:
							g2.setColor(new Color(100, 235, 100));
							break;
						default:
							break;
						}
						g2.fill(new Rectangle(scale * col, scale * row, scale, scale));
					}
			}
		}
		
		for (int row = 0; row < Board.SIZE; row++){
			for (int col = 0; col < Board.SIZE; col++){
				switch(boardCells[row][col]) {
				case HALL:
					g2.setColor(new Color(0, 0, 0));
					g2.draw(new Rectangle(scale * col, scale * row, scale, scale));
					break;
				case NONE:
					g2.setColor(new Color(0, 0, 0));
					g2.draw(new Rectangle(scale * col, scale * row, scale, scale));
					break;
				case ROOM:
					break;
				case STAIR:
					break;
				case START:
					break;
				default:
					break;
				
				}
				
			}
		}
		
		g2.drawString("Moves left: " + game.getDice(), 10, 15);
		
		if (graphics != null) {
			graphics.dispose();
			bufferStrategy.show();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = arg0.getY() / scale;
		int col = arg0.getX() / scale;
		//System.out.println(" " + row + " " + col);
		game.handleClick(row, col);
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {

	}


	@Override
	public void mouseExited(MouseEvent arg0) {

	}


	@Override
	public void mousePressed(MouseEvent arg0) {

	}


	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_Q: {
			game.enterExitRoom();
			break;
		}
		case KeyEvent.VK_W: {
			game.moveUp();
			break;
		}
		case KeyEvent.VK_E: {
			game.enterStair();
			break;
		}
		case KeyEvent.VK_A: {
			game.moveLeft();
			break;
		}
		case KeyEvent.VK_S: {
			game.moveDown();
			break;
		}
		case KeyEvent.VK_D: {
			game.moveRight();
			break;
		}
		case KeyEvent.VK_Z: {
			game.makeSuggestion();
			break;
		}
		case KeyEvent.VK_X: {
			game.makeAccusation();
			break;
		}
		case KeyEvent.VK_C: {
			game.skipTurn();
			break;
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}