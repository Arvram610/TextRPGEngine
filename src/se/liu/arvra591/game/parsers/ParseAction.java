package se.liu.arvra591.game.parsers;

/**
 * The ParseAction interface is used to do an action based on the parsed input
 */
public interface ParseAction
{
    /**
     * @param input The input to perform the action on, example move (location) where location is the input
     */
    public void performAction(String input);
}
