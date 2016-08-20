package cluedoView;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
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
	private CleudoCanvas draw;
	private Game game;
	
	public GUI(Game game) {
		this.game = game;
	
		frame = new JFrame("Cluedo");
		draw = new CleudoCanvas(game);
		
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
	
	public Weapon selectWeapon(List<Weapon> weapons) {
		
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
	
	public Room selectRoom(List<Room> list) {
		
		ButtonGroup group = new ButtonGroup();
	    List<JRadioButton> buttons = new ArrayList<JRadioButton>();
	    for (Room room : list) {
	    	
	      JRadioButton button = new JRadioButton(room.getName());
	      button.setActionCommand(room.getName());
	      buttons.add(button);
	      group.add(button);
	    }
	    
	    buttons.get(0).setSelected(true);
	    JComponent[] inputs = new JComponent[buttons.size()];
	    buttons.toArray(inputs);
	    JOptionPane.showMessageDialog(null, inputs, "Select Player", JOptionPane.PLAIN_MESSAGE);
		
		return list.get(group.getSelection().getMnemonic());
	}
}

