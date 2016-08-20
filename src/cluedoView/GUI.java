package cluedoView;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import cluedoController.Game;
import cluedoModel.Board;
import cluedoModel.RoomEntranceCell;
import cluedoModel.Weapon;
import cluedoModel.Board.Cell;
import cluedoModel.Player;
import cluedoModel.Room;

public class GUI{

	private JFrame frame;
	private Draw draw;
	private Game game;
	
	public GUI(Game game) {
		this.game = game;
	
		frame = new JFrame("Cluedo");
		draw = new Draw(game);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());
	    
	    JMenuBar bar = new JMenuBar();
	    JMenu menu = new JMenu("File");
	    bar.add(menu);
	    
	    JMenuItem newGame = new JMenuItem("New Game");
	    newGame.addActionListener(new ActionListener() {  
	    	public void actionPerformed(ActionEvent arg0) {
	    		//TODO
	    }});
	    
	    JMenuItem quit = new JMenuItem("Quit");
	    quit.addActionListener(new ActionListener() {  
	    	public void actionPerformed(ActionEvent arg0) {
	      System.exit(0);
	    }});
	    
	    JButton enterRoom = new JButton("Enter/Exit Room(Q)");
	    enterRoom.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton up = new JButton("Up(W)");
	    up.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  game.moveUp();
		      }
	    });
	    
	    JButton enterStair = new JButton("Enter Stair(E)");
	    enterStair.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton down = new JButton("Down(S)");
	    down.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  game.moveDown();
		      }
	    });
	    
	    JButton left = new JButton("Left(A)");
	    left.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  game.moveLeft();
		      }
	    });
	    
	    JButton right = new JButton("Right(D)");
	    right.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  game.moveRight();
		      }
	    });
	    
	    JButton suggest = new JButton("Suggest(Z)");
	    suggest.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton accuse = new JButton("Accuse(X)");
	    accuse.addActionListener(new ActionListener() {    	
		      public void actionPerformed(ActionEvent e) {
		    	  game.makeAccusation();
		      }
	    });
	    
	    JButton skip = new JButton("Skip turn(C)");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  game.skipTurn();
		      }
	    });
	    
	    //frame.add(skip, BorderLayout.SOUTH);
	    JPanel controls = new JPanel();
	    controls.setLayout(new GridLayout(3, 3));
	    
	    controls.add(enterRoom);
	    controls.add(up);
	    controls.add(enterStair);

	    controls.add(left);
	    controls.add(down);
	    controls.add(right);
	    
	    controls.add(suggest);
	    controls.add(accuse);
	    controls.add(skip);
	    
	    frame.add(controls, BorderLayout.SOUTH);
	    //frame.add(skip, BorderLayout.EAST);
	    
	    menu.add(newGame);
	    menu.add(quit);
	    frame.add(bar, BorderLayout.NORTH);

	    frame.add(draw, BorderLayout.CENTER);
	    

	    frame.pack();
	    frame.setResizable(true);
	    frame.setVisible(true);
	}
	
	public void draw() {
		draw.repaint();
	}
	
	public void dialog(String message){
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public int askPlayerCount() {
		int playerCount = -1;
		while (!(playerCount > 2 && playerCount < 7)) {
			String inputString;
			try {
				inputString = JOptionPane.showInputDialog(null, "How many players? (3-6):", "How many players? (3-6):", 1);
				playerCount = Integer.parseInt(inputString);
			} catch (NumberFormatException e) {
			}
		}
		return playerCount;
	}
	
	public Player selectPlayer(ArrayList<Player> players) {
		
		ButtonGroup group = new ButtonGroup();
	    List<JRadioButton> buttons = new ArrayList<JRadioButton>();
	    for (Player player : players) {
	    	
	      JRadioButton button = new JRadioButton(player.getName());
	      button.setActionCommand(player.getName());
	      buttons.add(button);
	      group.add(button);
	    }
	    
	    buttons.get(0).setSelected(true);
	    JComponent[] inputs = new JComponent[buttons.size()];
	    buttons.toArray(inputs);
	    JOptionPane.showMessageDialog(null, inputs, "Select Player", JOptionPane.PLAIN_MESSAGE);
		
		return players.get(group.getSelection().getMnemonic());
	}
	
	public Weapon selectWeapon(ArrayList<Weapon> weapons) {
		
		ButtonGroup group = new ButtonGroup();
	    List<JRadioButton> buttons = new ArrayList<JRadioButton>();
	    for (Weapon weapon : weapons) {
	    	
	      JRadioButton button = new JRadioButton(weapon.getName());
	      button.setActionCommand(weapon.getName());
	      buttons.add(button);
	      group.add(button);
	    }
	    
	    buttons.get(0).setSelected(true);
	    JComponent[] inputs = new JComponent[buttons.size()];
	    buttons.toArray(inputs);
	    JOptionPane.showMessageDialog(null, inputs, "Select Player", JOptionPane.PLAIN_MESSAGE);
		
		return weapons.get(group.getSelection().getMnemonic());
	}
	
	public Room selectRoom(ArrayList<Room> rooms) {
		
		ButtonGroup group = new ButtonGroup();
	    List<JRadioButton> buttons = new ArrayList<JRadioButton>();
	    for (Room room : rooms) {
	    	
	      JRadioButton button = new JRadioButton(room.getName());
	      button.setActionCommand(room.getName());
	      buttons.add(button);
	      group.add(button);
	    }
	    
	    buttons.get(0).setSelected(true);
	    JComponent[] inputs = new JComponent[buttons.size()];
	    buttons.toArray(inputs);
	    JOptionPane.showMessageDialog(null, inputs, "Select Player", JOptionPane.PLAIN_MESSAGE);
		
		return rooms.get(group.getSelection().getMnemonic());
	}
}

class Draw extends Canvas{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private Game game;

	public Draw(Game game) {
		this.game = game;
		this.setBackground(new Color(255, 255, 255));
	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	@Override
	public void repaint() {
		paint(this.getGraphics());
	}

	public void paint(Graphics g) {
		Dimension size = this.getSize();
		double minDimension = Math.min(size.getWidth(), size.getHeight());
		int scale = (int) (minDimension / Board.SIZE);
		
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
		//TODO draw rooms names
		//g.drawString(arg0, arg1, arg2);
		
		
	}

	public void mouseClicked(MouseEvent arg0) {
		System.out.println(" " + arg0.getX() + " " + arg0.getY());
		
	}
}