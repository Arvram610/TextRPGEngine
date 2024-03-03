package se.liu.arvra591.game.listeners;

import se.liu.arvra591.game.parsers.InputParser;

public class CommandHandler
{
    private InputParser listener;

    public void setListener(InputParser listener){
	this.listener = listener;
    }

    public void notifyListener(String command){
	listener.parseInput(command);
    }
}
