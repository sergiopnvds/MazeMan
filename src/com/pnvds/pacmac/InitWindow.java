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

	String[] sizes = { "10x10","15x15","20x20","25x25" } ;
	String[] types = {"Standard","Random"} ;
	String[] recordsArray = {"10x10","15x15","20x20","25x25"} ;
	
	private static int numSize;
	private static String gameType;
	private static	String lDifficulty;
	private static int nEnemies;
	private static String playerNameStr;
	private static String recordType;
	
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
		initWindow.setBounds(100, 100, 284, 429);
		initWindow.setResizable(false);
		initWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initWindow.getContentPane().setLayout(null);	
		
		JLabel name = new JLabel("PacMaze ");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		name.setBounds(47, 6, 197, 76);
		initWindow.getContentPane().add(name);
		
		JLabel PlayerName = new JLabel("Player name: ");
		PlayerName.setBounds(26, 89, 102, 20);
		initWindow.getContentPane().add(PlayerName);
		
		Pname = new JTextField();
		Pname.setColumns(10);
		Pname.setBounds(108, 89, 154, 20);
		initWindow.getContentPane().add(Pname);
		
		JLabel recordsLabel = new JLabel("Records type: ");
		recordsLabel.setBounds(36, 304, 102, 20);
		initWindow.getContentPane().add(recordsLabel);
		
		final JComboBox mazeSize = new JComboBox(sizes);
		mazeSize.setBounds(108, 121, 154, 20);
		initWindow.getContentPane().add(mazeSize);
		
		JPanel panelLevel = new JPanel();
		panelLevel.setBounds(19, 184, 243, 96);
		initWindow.getContentPane().add(panelLevel);
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
		
		ButtonGroup bg= new ButtonGroup();
		bg.add(easy);
		bg.add(medium);
		bg.add(hard);
		
		JLabel level = new JLabel("Level of difficulty: ");
		level.setBounds(6, 6, 130, 20);
		panelLevel.add(level);
		
		final JComboBox mazetype = new JComboBox(types);
		mazetype.setBounds(108, 152, 154, 20);
		initWindow.getContentPane().add(mazetype);
		
		JLabel game = new JLabel("Select Game: ");
		game.setBounds(26, 152, 130, 20);
		initWindow.getContentPane().add(game);

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
	   		public void actionPerformed(ActionEvent ae) {
	   			playerNameStr = Pname.getText();
	   			if(playerNameStr.equals("")||playerNameStr.equals(null)){
	   				playerNameStr = "User";
	   				Pname.setText("User");   					
	   				}
	   			
	   		    
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
	   			GUI.setSize(numSize);		
	   	   		Pacman.launchGame();
   			}
		});
			
		start.setBounds(26, 368, 102, 33);
		initWindow.getContentPane().add(start);
		
		JComboBox recordsBox = new JComboBox(recordsArray);
		recordsBox.setBounds(136, 305, 118, 20);
		initWindow.getContentPane().add(recordsBox);
		
		JButton records = new JButton("Records");
		records.setForeground(Color.BLACK);
		records.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				recordType=(String) recordsBox.getSelectedItem();
				RecordsDataBase.initRecords();
			}		
		});
		records.setBounds(66, 336, 154, 20);
		initWindow.getContentPane().add(records);
		
		
	JButton cancel = new JButton("Exit");
	cancel.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			initWindow.dispose();
		}	
	});
		
	cancel.setBounds(160, 368, 97, 33);
	initWindow.getContentPane().add(cancel);
	
	JLabel label = new JLabel("Maze size: ");
	label.setBounds(26, 121, 102, 20);
	initWindow.getContentPane().add(label);
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

