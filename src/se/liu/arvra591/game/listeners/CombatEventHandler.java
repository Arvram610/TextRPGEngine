package se.liu.arvra591.game.listeners;

/**
 * The CombatEventHandler class is used to notify the CombatListener
 * when the player has finished their turn in combat and calls the npc logic
 */
public class CombatEventHandler
{
    private CombatListener listener = null;

    public void setListener(CombatListener listener)
    {
	this.listener = listener;
    }

    public void notifyNpcLogic()
    {
	listener.notifyNpcLogic();
    }
}
