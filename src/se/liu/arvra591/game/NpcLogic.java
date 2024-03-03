package se.liu.arvra591.game;

import se.liu.arvra591.game.listeners.CombatEventHandler;
import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.objects.containers.CreatureInventory;
import se.liu.arvra591.game.objects.creatures.CreatureStats;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.items.Item;

import java.util.List;
import java.util.Random;

public class NpcLogic
{
    private Npc npc;
    private static final int ENERGY_COST = 5;
    private static final int REST_ENERGY_REGEN = 15;
    private CombatEventHandler combatEventHandler;
    public NpcLogic(Npc npc, CombatEventHandler combatEventHandler)
    {
	this.npc = npc;
        this.combatEventHandler = combatEventHandler;
    }

    public void setNpc(Npc npc){
        this.npc = npc;
    }

    public void startOfTurn(){
        npc.addEnergy(Integer.toString(npc.getStats().getEnergyRegenRate()));
        Random rnd = new Random();
        int random = rnd.nextInt(3);
        switch(random){
            case 0:
                CreatureInventory inventory = npc.getInventory();
                List<Item> items = inventory.getObjects();
                if (!items.isEmpty())
                    useItem();
            case 1:
            case 2:

                attack();
                rest();
                break;
        }
    }
    public void attack(){
        npc.attack();
    }

    public void useItem(){

    }

    public void rest(){
        npc.rest(REST_ENERGY_REGEN);
    }
}
