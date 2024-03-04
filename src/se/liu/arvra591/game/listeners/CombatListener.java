package se.liu.arvra591.game.listeners;

/**
 * A class that listens for combat events and notifies the game when it is time to run the npc logic
 */
public interface CombatListener
{
    void notifyNpcLogic();
}
