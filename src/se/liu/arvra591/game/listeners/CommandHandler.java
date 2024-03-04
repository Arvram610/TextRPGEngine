package se.liu.arvra591.game.listeners;

import se.liu.arvra591.game.parsers.InputParser;

/**
 *  The CommandHandler class is used to send commands to the masterparser from Objects
 */
public class CommandHandler
{
    private InputParser listener = null;

    public void setListener(InputParser listener){
	this.listener = listener;
    }

    public void notifyListener(String command){
	listener.parseInput(command);
    }
}
