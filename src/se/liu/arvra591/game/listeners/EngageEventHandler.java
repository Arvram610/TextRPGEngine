package se.liu.arvra591.game.listeners;

/**
 * The EngageEventHandler class is used to notify the EngageListener when the player has engaged or disengaged in combat
 */
public class EngageEventHandler
{
    private EngageListener listener = null;

    public void setListener(EngageListener listener)
    {
	this.listener = listener;
    }

    public void engage(String input)
    {
	listener.engage(input);
    }

    public void disengage(String input)
    {
	listener.disengage(input);
    }
}
