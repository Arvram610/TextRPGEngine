package se.liu.arvra591.game;

public class EventHandler
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
