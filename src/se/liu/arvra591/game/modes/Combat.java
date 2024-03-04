package se.liu.arvra591.game.modes;

import se.liu.arvra591.game.listeners.CombatEventHandler;
import se.liu.arvra591.game.listeners.EngageEventHandler;
import se.liu.arvra591.game.objects.creatures.CreatureStats;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.PlayerStats;
import se.liu.arvra591.game.parsers.InputParser;
import se.liu.arvra591.game.objects.creatures.Player;

/**
 * This class is the combat mode of the game
 * The player can attack, rest, check inventory, check stats, disengage and check enemy info
 * The player can also check the stats of the enemy
 */
public class Combat extends AbstractMode
{
    private CombatParser parser;

    private EngageEventHandler eventHandler;

    private CombatEventHandler combatEventHandler;

    private static final int ENERGY_COST = 5;

    private static final int REST_ENERGY_REGENERATION = 15;

    private Npc currentTarget;

    /**
     * @param player The player that is playing the game
     */
    public Combat(Player player, Npc target, EngageEventHandler eventHandler, CombatEventHandler combatEventHandler){
	super(player);
	this.parser = new CombatParser();
	this.currentTarget = target;
	this.eventHandler = eventHandler;
	this.combatEventHandler = combatEventHandler;
    }

    /**
     * This function is called at the start of a round
     */
    public void startOfRound(){
	player.addEnergy(player.getPlayerStats().getEnergyRegenerationRate());
	int health = player.getCurrentHealth();
	int energy = player.getCurrentEnergy();
	System.out.println("You have " + health + " health and " + energy + " energy");
	System.out.println(currentTarget.getName() + " now has " + currentTarget.getCurrentHealth() + " health left");
    }

    public void startOfCombat(){
	System.out.println("You are in combat with " + currentTarget.getName());
	System.out.println(currentTarget.getName() + " stats are: ");
	CreatureStats stats = currentTarget.getStats();
	stats.printStats();

	System.out.println();

	System.out.println("Your stats are: ");
	PlayerStats playerStats = player.getPlayerStats();
	playerStats.printStats();

	System.out.println();

	System.out.println("Available commands: attack, rest, checkinventory, stats, disengage and enemyinfo");

	System.out.println();
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

	currentTarget.takeDamage(damage);
	boolean hasDied = !currentTarget.isAlive();
	if (hasDied){
	    System.out.println(currentTarget.getName() + " has died");
	    currentTarget.onDeath();
	    return;
	}
	System.out.println("You attacked " + currentTarget.getName() + " for " + damage + " damage");
	System.out.println(currentTarget.getName() + " now has " + currentTarget.getCurrentHealth() + " health left");
	System.out.println();
	combatEventHandler.notifyNpcLogic();
    }

    /**
     * @param name Will be empty string
     */
    public void rest(String name){
	player.addEnergy(REST_ENERGY_REGENERATION);
	System.out.println("You slept and regained " + REST_ENERGY_REGENERATION + " energy");
    }

    /**
     * @return The target
     */
    public Npc getCurrentTarget(){
	return currentTarget;
    }

    /**
     * @param target The target to set
     */
    public void setCurrentTarget(Npc target){
	currentTarget = target;
    }

    /**
     * @param name Will be empty string
     */
    public void disEngage(String name){
	boolean canDisengage = currentTarget.getCanDisengage();
	if (!canDisengage){
	    System.out.println("You cannot disengage from " + currentTarget.getName());
	    return;
	}
	eventHandler.disEngage(name);
    }

    /**
     * @param name Will be empty string
     */
    public void printEnemyInfo(String ignored){
	currentTarget.printObject();
    }

    private class CombatParser extends InputParser
    {
	private CombatParser(){

	    parseInputs.put("attack", Combat.this::attack);
	    parseInputs.put("rest", Combat.this::rest);
	    parseInputs.put("checkinventory", Combat.this::printInventory);
	    parseInputs.put("inventory", Combat.this::printInventory);
	    parseInputs.put("stats", Combat.this::printStats);
	    parseInputs.put("disengage", Combat.this::disEngage);
	    parseInputs.put("enemyinfo", Combat.this::printEnemyInfo);
	}
    }
}
