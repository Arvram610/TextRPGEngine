package se.liu.arvra591.game;

import se.liu.arvra591.game.listeners.CombatEventHandler;
import se.liu.arvra591.game.objects.containers.CreatureInventory;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.items.Item;

import java.util.List;
import java.util.Random;

/**
 * Class for the logic of the npcs
 * The npcs will have a set of actions they can do, and will do one of them at the start of their turn
 */
public class NpcLogic
{
    private Npc npc;
    private static final int ENERGY_COST = 5;
    private final static Random RND = new Random();
    private static final int AMOUNT_OF_ACTIONS = 3;

    private static final int REST_ENERGY_REGENERATION = 15;
    public NpcLogic(Npc npc)
    {
	this.npc = npc;
    }

    public void setNpc(Npc npc){
        this.npc = npc;
    }

    public void startOfTurn(){
        npc.addEnergy(Integer.toString(npc.getStats().getEnergyRegenerationRate()));
        //int random = RND.nextInt(AMOUNT_OF_ACTIONS);
        switch(RND.nextInt(AMOUNT_OF_ACTIONS)){
            case 0:
                CreatureInventory inventory = npc.getInventory();
                List<Item> items = inventory.getObjects();
                int amountOfItems = items.size();
                if (!items.isEmpty())
                    useItem(RND.nextInt(amountOfItems));
            case 1: //Fallthrough intended
            case 2:
                if (npc.getCurrentEnergy() >= ENERGY_COST)
                    attack();
                else
                    rest();
                break;
        }
    }

    /**
     * The npc attacks the player
     */
    public void attack(){
        npc.attack();
    }

    /**
     * @param index Random number to choose which item to use
     */
    public void useItem(int index){
        npc.useItem(index);
    }

    public void rest(){
        npc.rest(REST_ENERGY_REGENERATION);
    }
}
