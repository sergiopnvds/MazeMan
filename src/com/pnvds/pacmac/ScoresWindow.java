package com.pnvds.pacmac;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.ListModel;

import java.awt.Component;

public class ScoresWindow {
    	
	static String[] easyStandard = new String[10];
	static String[] easyRandom = new String[10];
	static String[] mediumStandard = new String[10];
	static String[] mediumRandom = new String[10];
	static String[] difficultStandard = new String[10];
	static String[] difficultRandom = new String[10];
	static JFrame frame ;


	/**
	 * @param size 
	 * @wbp.parser.entryPoint
	 */
	public static void init(int size) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {	
				try {
				frame = new JFrame("Scores");
			    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    //frame.setContentPane(new ScoresWindow());
			    frame.setSize(468, 479);
			    frame.setVisible(true);
			    
			    recordFile.addToRecordsList(size);
			    
			    JList list1 = new JList(easyStandard);
			    JList list2 = new JList(easyRandom);
			    JList list4 = new JList(easyRandom);
			    Container c = frame.getContentPane();
			    JScrollPane sp1 = new JScrollPane(list1);
			    JLabel label = new JLabel("Easy");
			    label.setForeground(Color.BLUE);
			    sp1.setColumnHeaderView(label);
			    JScrollPane sp2 = new JScrollPane(list2);
			    JLabel label_1 = new JLabel("Easy");
			    label_1.setForeground(Color.BLUE);
			    sp2.setColumnHeaderView(label_1);
			    Box box = Box.createHorizontalBox();
			    box.setBounds(20, 20, 427, 140);
			    box.add(sp1);
			    box.add(sp2);
			    
			    JScrollPane sp4 = new JScrollPane(list4);
			    JLabel label_3 = new JLabel("Medium");
			    label_3.setForeground(Color.BLUE);
			    sp4.setColumnHeaderView(label_3);
			    Box box2 = Box.createHorizontalBox();
			    box2.setBounds(20, 160, 427, 140);
			    JList list3 = new JList(mediumStandard);
			    JScrollPane sp3 = new JScrollPane(list3);
			    box2.add(sp3);
			    JLabel label_2 = new JLabel("Medium");
			    label_2.setForeground(Color.BLUE);
			    sp3.setColumnHeaderView(label_2);
			    box2.add(sp4);
			    frame.getContentPane().setLayout(null);
			    
			    JLabel lblStandard = new JLabel("Standard");
			    lblStandard.setForeground(new Color(0, 0, 0));
			    lblStandard.setBounds(20, 3, 127, 16);
			    frame.getContentPane().add(lblStandard);
			    JLabel lblMedium = new JLabel("Random");
			    lblMedium.setBounds(234, 3, 143, 16);
			    frame.getContentPane().add(lblMedium);
			    lblMedium.setBackground(Color.LIGHT_GRAY);
			    c.add(box);
			    c.add(box2);
			    
			    Box horizontalBox = Box.createHorizontalBox();
			    horizontalBox.setBounds(20, 300, 427, 140);
			    frame.getContentPane().add(horizontalBox);
			    JList list5 = new JList(difficultStandard);
			    JScrollPane sp5 = new JScrollPane(list5);
			    horizontalBox.add(sp5);
			    JLabel label_4 = new JLabel("Difficult");
			    label_4.setForeground(Color.BLUE);
			    sp5.setColumnHeaderView(label_4);
			    JList list6 = new JList(easyRandom);
			    JScrollPane sp6 = new JScrollPane(list6);
			    horizontalBox.add(sp6);
			    sp6.setColumnHeaderView(new JLabel("Easy"));
			    
			    JLabel lblNewLabel_3 = new JLabel("Difficult");
			    lblNewLabel_3.setForeground(Color.BLUE);
			    sp6.setColumnHeaderView(lblNewLabel_3);
			    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  
	
}
           