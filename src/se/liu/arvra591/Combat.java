package se.liu.arvra591;

import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.parsers.InputParser;
import se.liu.arvra591.objects.creatures.Player;

public class Combat
{
    private Player player;

    private Parser parser;

    private static final int ENERGY_COST = 5;

    private Npc currentTarget;

    /**
     * @param player The player that is playing the game
     */
    public Combat(Player player, Npc target){
	this.player = player;
	this.parser = new Parser();
	this.currentTarget = target;
    }

    public void startOfRound(){
	player.addEnergy(player.getPlayerStats().getEnergyRegenRate());
	int health = player.getHealth();
	int energy = player.getCurrentEnergy();
	System.out.println("You have " + health + " health and " + energy + " energy");
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
	System.out.println(currentTarget.getName() + " now has " + currentTarget.getHealth() + " health left");
    }

    public void sleep(String name){
	//player.sleep();
    }

    private class Parser extends InputParser
    {
	private Parser(){

	    parseInputs.put("attack", Combat.this::attack);
	    parseInputs.put("sleep", Combat.this::sleep);
	}
    }

    public static void main(String[] args){
	PlayerStats stats = new PlayerStats(100, 10, 10, 5, 10, 0, 0, 100, 5);
	CreatureStats creatureStats = new CreatureStats(100, 5, 5, 0, 0);

	Player player = new Player("Player", "A player", 100, 100, stats, null, 0, null);
	Npc target = new Npc("Carl", "A friendly human", 100, creatureStats, null, null);

	Combat combat = new Combat(player, target);

	//combat.parseInput("help");
	combat.parseInput("attack");
	combat.startOfRound();
    }

}
