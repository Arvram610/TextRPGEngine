package se.liu.arvra591.game.listeners;

import se.liu.arvra591.game.listeners.EngageListener;

public class EngageEventHandler
{
    private EngageListener listener;

    public void setListener(EngageListener listener)
    {
	this.listener = listener;
    }

    public void engage(String input)
    {
	listener.engage(input);
    }

    public void disEngage(String input)
    {
	listener.disEngage(input);
    }
}
