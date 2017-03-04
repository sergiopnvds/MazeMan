package com.pnvds.pacmac;


// RecordsDataBase.java
//An example of JList with a DefaultListModel that we build up at runtime.
//

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import java.awt.Point;
import java.awt.Dimension;

public class RecordsDataBase extends JPanel {
	JList list1;
	JList list2;
	JList list3;
	JList list4;
	JList list5;
	JList list6;
	
	static DefaultListModel model;
	static JFrame frame;
	int counter = 15;

	public RecordsDataBase() throws IOException {
    setLayout(new BorderLayout());
    model = new DefaultListModel();
    list1 = new JList(model);
    list2 = new JList(model);
    list3 = new JList(model);
    list4 = new JList(model);
    list5 = new JList(model);
    list6 = new JList(model);

    JScrollPane pane1 = new JScrollPane(list1);
    pane1.setPreferredSize(new Dimension(260, 130));
    JScrollPane pane2= new JScrollPane(list2);
    pane2.setPreferredSize(new Dimension(260, 130));
    pane2.setLocation(new Point(131, 0));
    JScrollPane pane3 = new JScrollPane(list3);
    JScrollPane pane4 = new JScrollPane(list4);
    JScrollPane pane5 = new JScrollPane(list5);
    JScrollPane pane6 = new JScrollPane(list6);
    JButton close = new JButton("Close");
    recordFile.readRecords(InitWindow.getLRecordType());
    close.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  frame.dispose();
      }
    });
    add(pane1);
    add(pane2);
    add(pane3);
    add(pane4);
    add(pane5);
    add(pane6);
    //add(list, BorderLayout.NORTH);
    add(close, BorderLayout.SOUTH);
  }

  /**
	 * Launch the application.
	 */
	public static void initRecords() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new JFrame("Game scores");
					 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 frame.setContentPane(new RecordsDataBase());
					 frame.setSize(273, 630);
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  
}
           