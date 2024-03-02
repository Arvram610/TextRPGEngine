package se.liu.arvra591.game;

import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.creatures.PlayerStats;
import se.liu.arvra591.game.parsers.InputParser;

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
	this.target = null;
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
	    //engage
	    //movePlayer
	    //giveItem
	    //useItem
	    //spawnNpc
	    //spawnExit
	    //spawnItem
	    //disEngage
	    //win
	    //lose

	    parseInputs.put("giveplayerhealth", player::increaseHealth); //how to do with generic creature? npc can use items too
	    parseInputs.put("giveplayerenergy", player::addEnergy);
	    parseInputs.put("giveplayerattack", player::increaseAttack);
	    parseInputs.put("giveplayerdefense", player::increaseDefense);

	    parseInputs.put("givenpcenergy", target::addEnergy); //how to do with generic creature? npc can use items too")
	    parseInputs.put("givenpchealth", target::increaseHealth);
	    parseInputs.put("givenpcattack", target::increaseAttack);
	    parseInputs.put("givenpcdefense", target::increaseDefense);
	}
    }

    public static void main(String[] args) {
	PlayerStats stats = new PlayerStats(100, 10, 10, 10, 10,
					    10,  101, 10);
	Player player = new Player("player", "player", 10, 10,
				   stats, null, null);
	Game game = new Game(player);
	System.out.println("Health: " + player.getCurrentHealth());
	game.parseInput("give health 10");
	System.out.println("Health: " + player.getCurrentHealth());

	System.out.println();

	System.out.println("Energy: " + player.getCurrentEnergy());
	game.parseInput("give energy 10");
	System.out.println("Energy: " + player.getCurrentEnergy());

	System.out.println();

	System.out.println("Attack: " + player.getPlayerStats().getAttack());
	game.parseInput("give attack 10");
	System.out.println("Attack: " + player.getPlayerStats().getAttack());

	System.out.println();

	System.out.println("Defense: " + player.getPlayerStats().getDefense());
	game.parseInput("give defense 10");
	System.out.println("Defense: " + player.getPlayerStats().getDefense());

	game.parseInput("say Hello");
    }
}
