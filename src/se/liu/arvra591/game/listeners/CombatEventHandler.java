package se.liu.arvra591.game.listeners;

public class CombatEventHandler
{
    private CombatListener listener;

    public void setListener(CombatListener listener)
    {
	this.listener = listener;
    }

    public void notifyNpcLogic()
    {
	listener.notifyNpcLogic();
    }
}
