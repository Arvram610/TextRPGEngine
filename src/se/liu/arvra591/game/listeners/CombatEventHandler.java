package se.liu.arvra591.game.listeners;

/**
 * The CombatEventHandler class is used to notify the CombatListener
 * when the player has finished their turn in combat and calls the npc logic
 */
public class CombatEventHandler
{
    private CombatListener listener = null;

    /**
     * @param listener The listener that is notified when the player has finished their turn
     */
    public void setListener(CombatListener listener)
    {
	this.listener = listener;
    }

    /**
     * Notifies the listener that the player has finished their turn
     */
    public void notifyNpcLogic()
    {
	listener.notifyNpcLogic();
    }
}
