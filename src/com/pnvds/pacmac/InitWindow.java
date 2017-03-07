package com.pnvds.pacmac;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


public class InitWindow{

	public JFrame initWindow;
	private JTextField Pname;
	static JComboBox mazeSize;

	String[] sizes = { "10x10","15x15","20x20","25x25" } ;
	String[] types = {"Standard","Random"} ;
	String[] recordsArray = {"10x10","15x15","20x20","25x25"} ;
	
	private static int numSize;
	private static String gameType;
	private static	String lDifficulty;
	private static int nEnemies;
	private static String playerNameStr;
	private static String recordType;
	private static int sizeAux;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitWindow window = new InitWindow();
					window.initWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InitWindow() {
	    	initialize();
	}

	/**
	 * Initialize the contents of the checkIn.
	 */
	public void initialize() {
		initWindow = new JFrame("PacMaze");
		initWindow.setBounds(100, 100, 316, 515);
		initWindow.setResizable(false);
		initWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initWindow.getContentPane().setLayout(null);	
		
		JLabel name = new JLabel("PacMaze ");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		name.setBounds(75, 6, 197, 76);
		initWindow.getContentPane().add(name);
		
		ButtonGroup bg= new ButtonGroup();
	
	JPanel panel = new JPanel();
	panel.setLayout(null);
	panel.setBorder(new LineBorder(Color.BLACK, 1, true));
	panel.setBounds(19, 393, 278, 82);
	initWindow.getContentPane().add(panel);
	
	JLabel recordsLabel = new JLabel("Records type: ");
	recordsLabel.setBounds(6, 16, 102, 20);
	panel.add(recordsLabel);
	
	JComboBox recordsBox = new JComboBox(recordsArray);
	recordsBox.setBounds(133, 17, 139, 20);
	panel.add(recordsBox);
	
	JButton records = new JButton("Open");
	records.setBounds(90, 48, 97, 28);
	panel.add(records);
	records.setForeground(Color.BLACK);
	
	JPanel panel_1 = new JPanel();
	panel_1.setLayout(null);
	panel_1.setBorder(new LineBorder(Color.BLACK, 1, true));
	panel_1.setBounds(19, 104, 278, 251);
	initWindow.getContentPane().add(panel_1);
	
	JPanel panelLevel = new JPanel();
	panelLevel.setBounds(6, 102, 266, 96);
	panel_1.add(panelLevel);
	panelLevel.setLayout(null);
	panelLevel.setBorder(new LineBorder(Color.BLACK, 1, true));
	
	final JRadioButton easy = new JRadioButton( "Easy" ) ;
	//roomKing.setBounds(35, 180, 75, 24);
	easy.setBounds(6, 49, 65, 24);
	panelLevel.add(easy);
		
	final JRadioButton medium = new JRadioButton( "Medium" ) ;
	medium.setBounds(77, 49, 88, 24);
	panelLevel.add(medium);
	medium.setSelected(true);
		
	final JRadioButton hard = new JRadioButton( "Hard" ) ;
	hard.setBounds(172, 49, 65, 24);
	panelLevel.add(hard);
	bg.add(easy);
	bg.add(medium);
	bg.add(hard);
		
	JLabel level = new JLabel("Level of difficulty: ");
	level.setBounds(6, 6, 130, 20);
	panelLevel.add(level);
		
	JLabel PlayerName = new JLabel("Player name: ");
	PlayerName.setBounds(6, 6, 102, 20);
	panel_1.add(PlayerName);
		
	Pname = new JTextField();
	Pname.setBounds(118, 6, 154, 20);
	panel_1.add(Pname);
	Pname.setColumns(10);
		
	JLabel label = new JLabel("Maze size: ");
	label.setBounds(6, 38, 102, 20);
	panel_1.add(label);
		
	mazeSize = new JComboBox(sizes);
	mazeSize.setBounds(118, 38, 154, 20);
	panel_1.add(mazeSize);
		
	final JComboBox mazetype = new JComboBox(types);
	mazetype.setBounds(118, 70, 154, 20);
	panel_1.add(mazetype);
		
	JLabel game = new JLabel("Select Game: ");
	game.setBounds(6, 69, 130, 20);
	panel_1.add(game);
		
	JButton start = new JButton("Start");
	start.setBounds(16, 210, 122, 33);
	panel_1.add(start);
				
				
	JButton cancel = new JButton("Exit");
	cancel.setBounds(139, 210, 118, 33);
	panel_1.add(cancel);
	
	JLabel lblNewGame = new JLabel("New game");
	lblNewGame.setBounds(19, 83, 102, 20);
	initWindow.getContentPane().add(lblNewGame);
	
	JLabel lblScores = new JLabel("Scores");
	lblScores.setBounds(19, 372, 102, 20);
	initWindow.getContentPane().add(lblScores);
	
	cancel.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			initWindow.dispose();
		}	
	});
	
	start.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
 			playerNameStr = Pname.getText();
	   		if(playerNameStr.equals("")||playerNameStr.equals(null)){
	   			playerNameStr = "Player";
	   			Pname.setText("Player");   					
	   		}	
	   		    
	   		
	   		sizeToInt();
//	   		String mSize = (String) mazeSize.getSelectedItem();
//	   			
//	   		switch (mSize){
//	   			case "10x10": 	numSize=10;
//	  							break;
// 				case "15x15": 	numSize=15;
//								break;
//   				case "20x20": 	numSize=20;
//	   							break;		
//	   			case "25x25": 	numSize=25;
//	   							break;
//	   			default:  		numSize=10;
//	   							break;
//	   		}
	   				   			
	   		gameType = (String) mazetype.getSelectedItem();
	   		if (medium.isSelected()) {			
	   			lDifficulty = "medium";
	   			nEnemies = numSize/3+1;
			}
	   		else if (easy.isSelected()) {
	   			lDifficulty = "easy";
	   			nEnemies = numSize/5+1;
			}
   			else if (hard.isSelected()) {
   				lDifficulty = "hard";
	   			nEnemies = numSize/2;
	   		}
	   		else {
	   			lDifficulty = "medium";
	   			nEnemies = numSize/3+1;
	   		}
	   		sizeAux=numSize;
	   		GUI.setSize(numSize);
	   		Pacman.launchGame();
	   	}
	});
	records.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			sizeToInt();
			ScoresWindow.init(numSize);
		}		
	});
	}
	
	private static void sizeToInt(){
		
		String mSize = (String) mazeSize.getSelectedItem();
			
   		switch (mSize){
   			case "10x10": 	numSize=10;
  							break;
			case "15x15": 	numSize=15;
							break;
			case "20x20": 	numSize=20;
   							break;		
   			case "25x25": 	numSize=25;
   							break;
   			default:  		numSize=10;
   							break;
   		}
	}
	
	public static int getNumSize(){
		return numSize;
	}
	
	public static String getGameType(){
		return gameType;
	}
	
	public static int getNEnemies(){
		return nEnemies;
	}
	
	public static String getLDifficulty (){
		return lDifficulty;
	}
	
	public static String getLPlayerName(){
		return playerNameStr;
	}
	
	public static String getLRecordType(){
		return recordType;
	}
}

