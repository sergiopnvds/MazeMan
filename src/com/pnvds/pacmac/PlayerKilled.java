package com.pnvds.pacmac;


/**
 * Exception launched when player crashes with a enemy.
 *
 * @author Sergio Penavades
 * @version 09/01/2015
 */
public class PlayerKilled
        extends Exception {
    /**
     * CBuilder.No message.
     */
    public PlayerKilled() {
    }

    /**
     * Builder.
     *
     * @param s explanatory message.
     */
    public PlayerKilled(String s) {
        super(s);
    }
}
