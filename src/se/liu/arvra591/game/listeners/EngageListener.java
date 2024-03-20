package se.liu.arvra591.game.listeners;

/**
 * An interface that listens for engage events and notifies the game when it is time to enter combat or adventure mode
 */
public interface EngageListener
{
    void engage(String input);

    void disengage(String input);
}
