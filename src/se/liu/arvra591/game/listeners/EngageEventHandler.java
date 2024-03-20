package se.liu.arvra591.game.listeners;

/**
 * The EngageEventHandler class is used to notify the EngageListener when the player has engaged or disengaged in combat
 */
public class EngageEventHandler
{
    private EngageListener listener = null;

    /**
     * @param listener The listener that is notified when the player has engaged or disengaged
     */
    public void setListener(EngageListener listener)
    {
	this.listener = listener;
    }

    /**
     * @param input The npc to engage
     */
    public void engage(String input)
    {
	listener.engage(input);
    }

    /**
     * @param ignored Will be empty string
     */
    public void disengage(String ignored)
    {
	listener.disengage(ignored);
    }
}
