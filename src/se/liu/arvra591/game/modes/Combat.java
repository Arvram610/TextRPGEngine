package se.liu.arvra591.game.modes;

import se.liu.arvra591.game.objects.containers.PlayerInventory;
import se.liu.arvra591.game.objects.creatures.CreatureStats;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.PlayerStats;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;
import se.liu.arvra591.game.parsers.InputParser;
import se.liu.arvra591.game.objects.creatures.Player;

import java.util.ArrayList;
import java.util.List;

public class Combat extends AbstractMode
{
    private CombatParser parser;

    private static final int ENERGY_COST = 5;

    private static final int REST_ENERGY_REGEN = 15;

    private Npc currentTarget;

    /**
     * @param player The player that is playing the game
     */
    public Combat(Player player, Npc target){
	super(player);
	this.parser = new CombatParser();
	this.currentTarget = target;
    }

    public void startOfRound(){
	player.addEnergy(player.getPlayerStats().getEnergyRegenRate());
	int health = player.getCurrentHealth();
	int energy = player.getCurrentEnergy();
	System.out.println("You have " + health + " health and " + energy + " energy");
	System.out.println(currentTarget.getName() + " now has " + currentTarget.getCurrentHealth() + " health left");
    }

    /**
     * @param input The input from the player
     */
    public void parseInput(String input){
	parser.parseInput(input);
    }

    /**
     * This function is called when the player attacks a target
     * The plauer will attack the target and the target will take damage based on the players attack and the targets defense
     * @param name Will be empty string
     */
    public void attack(String name){
	PlayerStats stats = player.getPlayerStats();
	int attack = stats.getAttack();
	int targetDefense = currentTarget.getStats().getDefense();
	int damage = Math.max(attack - targetDefense, 0);

	if (!player.reduceEnergy(ENERGY_COST)){
	    System.out.println("You do not have enough energy to attack");
	    return;
	}

	boolean hasDied = currentTarget.takeDamage(damage);
	if (hasDied){
	    System.out.println(currentTarget.getName() + " has died");
	    return;
	}
	System.out.println("You attacked " + currentTarget.getName() + " for " + damage + " damage");
    }

    public void rest(String name){
	player.addEnergy(REST_ENERGY_REGEN);
	System.out.println("You slept and regained " + REST_ENERGY_REGEN + " energy");
    }

    private class CombatParser extends InputParser
    {
	private CombatParser(){

	    parseInputs.put("attack", Combat.this::attack);
	    parseInputs.put("rest", Combat.this::rest);
	    parseInputs.put("checkinventory", Combat.this::printInventory);
	    parseInputs.put("inventory", Combat.this::printInventory);
	    parseInputs.put("stats", Combat.this::printStats);
	}
    }
}
