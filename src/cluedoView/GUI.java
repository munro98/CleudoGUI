package cluedoView;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cluedoController.Game;
import cluedoModel.Board;
import cluedoModel.RoomEntranceCell;
import cluedoModel.Board.Cell;

public class GUI{

	private JFrame frame;
	private Draw draw;
	private Game game;
	
	public GUI(Game game) {
		this.game = game;
	
		frame = new JFrame("Cluedo");
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
	    
	    JButton skip = new JButton("Skip turn");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton suggest = new JButton("Suggest");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton up = new JButton("Up");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton accuse = new JButton("Accuse");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton down = new JButton("Down");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton left = new JButton("Left");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    JButton right = new JButton("Right");
	    skip.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  //TODO
		      }
	    });
	    
	    //frame.add(skip, BorderLayout.SOUTH);
	    JPanel controls = new JPanel();
	    controls.setLayout(new GridLayout(2, 3));
	    controls.add(suggest);
	    controls.add(up);
	    controls.add(accuse);
	    
	    controls.add(left);
	    controls.add(down);
	    controls.add(right);
	    
	    frame.add(controls, BorderLayout.SOUTH);
	    
	    
	    menu.add(newGame);
	    menu.add(quit);
	    frame.add(bar, BorderLayout.NORTH);
	    
	    
	    draw = new Draw(game);
	    /*
	    frame.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				onClick(e);
				redraw();
			}

		});
		*/
	    frame.add(draw, BorderLayout.CENTER);
	    
	    //createGameComponent(frame);
	    //createActionBar(frame);
	    frame.pack();
	    frame.setResizable(true);
	    frame.setVisible(true);
	    //redraw();
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


	public void paint(Graphics g) {
		Dimension size = this.getSize();
		double minDimension = Math.min(size.getWidth(), size.getHeight());
		int scale = (int) (minDimension / Board.SIZE);
		
		Graphics2D g2 = (Graphics2D) g;
		Cell[][] boardCells = game.getBoard().getBoardCells();
		RoomEntranceCell[][] boardSpecialCells = game.getBoard().getBoardEntranceCells();

		for (int row = 0; row < Board.SIZE; row++){
			for (int col = 0; col < Board.SIZE; col++){
				switch(boardCells[row][col]) {
				case HALL:
					if (boardSpecialCells[row][col] == null)
						g2.setColor(new Color(255, 235, 100));
					else
						g2.setColor(new Color(0, 0, 255));
					break;
				case NONE:
					g2.setColor(new Color(100, 235, 100));
					break;
				case ROOM:
					g2.setColor(new Color(255, 255, 255));
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
		
		for (int row = 0; row < Board.SIZE; row++){
			for (int col = 0; col < Board.SIZE; col++){
				switch(boardCells[row][col]) {
				case HALL:
					g2.setColor(new Color(0, 0, 0));
					g2.draw(new Rectangle(scale * col, scale * row, scale, scale));
					break;
				case NONE:
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
		
		//TODO draw players
		
		//TODO draw rooms names
		//g.drawString(arg0, arg1, arg2);
		
		
	}

	public void mouseClicked(MouseEvent arg0) {
		System.out.println(" " + arg0.getX() + " " + arg0.getY());
		
	}


}