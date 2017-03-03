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

public class RecordsDataBase extends JPanel {
	JList list;
	static DefaultListModel model;
	static JFrame frame;
  int counter = 15;

  public RecordsDataBase() throws IOException {
    setLayout(new BorderLayout());
    model = new DefaultListModel();
    list = new JList(model);
    JScrollPane pane = new JScrollPane(list);
    JButton close = new JButton("Close");
    recordFile.readRecords(InitWindow.getLRecordType());
    close.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  frame.dispose();
      }
    });
    add(pane);
    add(close, BorderLayout.SOUTH);
  }

  /**
	 * Launch the application.
	 */
	public static void initRecords() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new JFrame("Game records");
					 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 frame.setContentPane(new RecordsDataBase());
					 frame.setSize(270, 630);
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  
}
           