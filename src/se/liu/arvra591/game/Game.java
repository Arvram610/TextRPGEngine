package se.liu.arvra591.game;

import se.liu.arvra591.game.objects.creatures.CreatureStats;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.NpcDialogue;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.creatures.PlayerStats;
import se.liu.arvra591.game.parsers.InputParser;

import java.util.Arrays;

public class Game
{

    private Player player;
    private Npc target;
    private MasterParser parser;

    /**
     * @param player The player that is playing the game
     */
    public Game(Player player){
	this.player = player;
	NpcDialogue npcDialogue = new NpcDialogue(Arrays.asList("Hej!", "Hoppas du mår bra", "Ha en trevlig dag"));
	Npc npc = new Npc("Carl", "A friendly human", 10, 10, CreatureStats.basic, npcDialogue, null);
	this.target = npc;
	this.parser = new MasterParser();
    }

    /**
     * @param input The input from the object
     */
    public void parseInput(String input){
	parser.parseInput(input);
    }

    /**
     * @param input The input from the object
     * gives the user stats, for example "give health 10"
     */
    public void giveStats(String input){ //example "give health (user) 10"
	parseInput(input);// parsees the rest of the string example "health 10"
    }

    /**
     * @param input The input from the object
     * prints a description of the object
     */
    public void say(String input){
	System.out.println(input);
    }

    private class MasterParser extends InputParser
    {
	private MasterParser(){
	    parseInputs.put("say", Game.this::say);

	    parseInputs.put("giveplayerhealth", player::increaseHealth);
	    parseInputs.put("giveplayerenergy", player::addEnergy);
	    parseInputs.put("giveplayerattack", player::increaseAttack);
	    parseInputs.put("giveplayerdefense", player::increaseDefense);

	    parseInputs.put("givenpcenergy", target::addEnergy);
	    parseInputs.put("givenpchealth", target::increaseHealth);
	    parseInputs.put("givenpcattack", target::increaseAttack);
	    parseInputs.put("givenpcdefense", target::increaseDefense);

	    parseInputs.put("moveplayer", player::move);

	    //parseInputs.put("giveitem", player::addItem);

	    //engage
	    //giveItem
	    //useItem
	    //spawnNpc
	    //spawnExit
	    //spawnItem
	    //disEngage
	    //win
	    //lose
	}
    }

    public static void main(String[] args) {
	PlayerStats stats = new PlayerStats(100, 10, 10, 10, 10,
					    10,  101, 10);
	Player player = new Player("player", "player", 10, 10,
				   stats, null, null);
	Game game = new Game(player);
	System.out.println("Health: " + player.getCurrentHealth());
	game.parseInput("giveplayerhealth 10");
	System.out.println("Health: " + player.getCurrentHealth());

	System.out.println();

	System.out.println("Energy: " + player.getCurrentEnergy());
	game.parseInput("giveplayerenergy 10");
	System.out.println("Energy: " + player.getCurrentEnergy());

	System.out.println();

	System.out.println("Attack: " + player.getPlayerStats().getAttack());
	game.parseInput("giveplayerattack 10");
	System.out.println("Attack: " + player.getPlayerStats().getAttack());

	System.out.println();

	System.out.println("Defense: " + player.getPlayerStats().getDefense());
	game.parseInput("giveplayerdefense 10");
	System.out.println("Defense: " + player.getPlayerStats().getDefense());

	System.out.println();

	System.out.println("Npc Health: " + game.target.getCurrentHealth());
	game.parseInput("givenpchealth 10");
	System.out.println("Npc Health: " + game.target.getCurrentHealth());

	System.out.println();

	System.out.println("Npc Energy: " + game.target.getCurrentEnergy());
	game.parseInput("givenpcenergy 10");
	System.out.println("Npc Energy: " + game.target.getCurrentEnergy());

	System.out.println();

	System.out.println("Npc Attack: " + game.target.getStats().getAttack());
	game.parseInput("givenpcattack 10");
	System.out.println("Npc Attack: " + game.target.getStats().getAttack());

	System.out.println();

	System.out.println("Npc Defense: " + game.target.getStats().getDefense());
	game.parseInput("givenpcdefense 10");
	System.out.println("Npc Defense: " + game.target.getStats().getDefense());

	game.parseInput("say Hello");
    }
}
