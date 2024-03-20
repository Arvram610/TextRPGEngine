package se.liu.arvra591.game.listeners;

/**
 * An interface that listens for engage events and notifies the game when it is time to enter combat or adventure mode
 */
public interface EngageListener
{
    /**
     * @param input The npc to engage
     */
    public void engage(String input);

    /**
     * @param ignored Will be empty string
     */
    public void disengage(String ignored);
}
