package com.pnvds.pacmac;


/**
 * Lauch the graphic app, applet or application
 *
 * @author Sergio Penavades
 * @version 09/01/2015
 */

import javax.swing.*;

public class Pacman
        extends JApplet {

	static JFrame frame;
    /**
     * Inits applet.
     */
    public void init() {
        new GUI(this);
    }

    /**
     * Function to start from terminal
     *
     * @param args No args.
     */
    public static void launchGame() {
        frame = new JFrame(GUI.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new GUI(frame);
    }
    
    /**
     * Getter
     *
     * @param args No args.
     * @return 
     */
    public static JFrame getFrame() {
       return frame;
    }
}