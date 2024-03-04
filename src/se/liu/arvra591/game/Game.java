package se.liu.arvra591.game;

import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CombatEventHandler;
import se.liu.arvra591.game.listeners.CombatListener;
import se.liu.arvra591.game.listeners.EngageEventHandler;
import se.liu.arvra591.game.listeners.EngageListener;
import se.liu.arvra591.game.modes.Adventure;
import se.liu.arvra591.game.modes.Combat;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;
import se.liu.arvra591.game.parsers.InputParser;

import java.util.List;
import java.util.Map;

/**
 * The main parser of the game
 * takes input from the game and parses it to the correct method
 * also takes the input from main and sends it to the correct method
 */
public class Game implements EngageListener, CombatListener
{

    private Player player;
    //private EngageEventHandler eventHandler;
    //private CombatEventHandler combatEventHandler;
    private MasterParser parser;
    private GameState gameState;
    private Adventure adventure;
    private Combat combat;
    private NpcLogic npcLogic;
    private Map<String, Location> locations;
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Factory<? extends Npc>> npcs;

    /**
     * @param player The player that is playing the game
     */
    public Game(Player player, Map<String, Location> locations, Map<String, Factory<? extends Item>> items,
		Map<String, Factory<? extends Npc>> npcs, EngageEventHandler engageEventHandler, CombatEventHandler combatEventHandler){
	this.player = player;
	this.parser = new MasterParser();
	this.locations = locations;
	this.items = items;
	this.npcs = npcs;
	this.gameState = GameState.ADVENTURE;
	//this.eventHandler = engageEventHandler;
	//this.combatEventHandler = combatEventHandler;
	this.combat = new Combat(player, null, engageEventHandler, combatEventHandler);
	this.adventure = new Adventure(player, engageEventHandler);
	this.npcLogic = new NpcLogic(null, combatEventHandler);
	engageEventHandler.setListener(this);
	combatEventHandler.setListener(this);
    }

    /**
     * @param input The input from the object
     */
    public void parseInput(String input){
	parser.parseInput(input);
    }


    public InputParser getParser(){
	return parser;
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

    /**
     * @param input The input from the object
     */
    public void giveItem(String input){
	Item item = items.get(input).generate();
	player.forceAddItem(item);
    }

    /**
     * @param input The NPC to engage combat with
     */
    @Override
    public void engage(String input){
	if (gameState == GameState.COMBAT) {
	    System.out.println("You are already in combat");
	    return;
	}
	Location location = player.getCurrentLocation();
	if (location.getNpcs().isEmpty()) {
	    System.out.println("There are no npcs to engage with");
	    return;
	}
	List<Npc> npcs = location.getNpcs();
	Npc target = ListHelper.findObjectInList(npcs, input);
	combat.setCurrentTarget(target);
	gameState = GameState.COMBAT;
	combat.startOfCombat();
    }

    /**
     * @param input will be empty string
     */
    public void disEngage(String input){
	if (gameState == GameState.ADVENTURE) {
	    System.out.println("You are not in combat");
	    return;
	}
	gameState = GameState.ADVENTURE;
	player.getCurrentLocation().removeNpc(combat.getCurrentTarget().getName());
    }

    public void notifyNpcLogic(){
	npcLogic.setNpc(combat.getCurrentTarget());
	npcLogic.startOfTurn();
    }

    /**
     * @return The target
     */
    public Npc getTarget(){
	return combat.getCurrentTarget();
    }

    /**
     * @param input The input from the player
     */
    public void processInput(String input){
	if (gameState == GameState.COMBAT) {
	    combat.parseInput(input);
	}
	else if (gameState == GameState.ADVENTURE) {
	    adventure.parseInput(input);
	}
    }

    /**
     * @param input The amount of health to give the npc
     */
    public void giveNpcHealth(String input){
	Npc target = getTarget();
	target.increaseHealth(input);
    }

    /**
     * @param input The amount of energy to give the npc
     */
    public void giveNpcEnergy(String input){
	Npc target = getTarget();
	target.addEnergy(input);
    }

    /**
     * @param input The amount of attack to give the npc
     */
    public void giveNpcAttack(String input){
	Npc target = getTarget();
	target.increaseAttack(input);
    }

    /**
     * @param input The amount of defense to give the npc
     */
    public void giveNpcDefense(String input){
	Npc target = getTarget();
	target.increaseDefense(input);
    }

    /**
     * @param input The input from the player will be empty
     */
    public void currentMode(String input){
	System.out.println("Current mode is: " + gameState);
    }

    /**
     * @param input Will be empty
     */
    public void win(String input){
	gameState = GameState.WIN;
	//TODO: Add win condition
    }

    /**
     * @param input Will be empty
     */
    public void lose(String input){
	gameState = GameState.GAME_OVER;
	//TODO: Add lose condition
    }

    /**
     * @param input The name of the npc to spawn
     */
    public void spawnNpc(String input){
	Npc npc = npcs.get(input).generate();
	player.getCurrentLocation().addNpc(npc);
    }

    /**
     * @param input The name of the exit to spawn
     */
    public void spawnExit(String input){
	Location exit = locations.get(input);
	player.getCurrentLocation().addExit(exit);
    }

    /**
     * @param input The name of the item to spawn
     */
    public void spawnItem(String input){
	Item item = items.get(input).generate();
	player.getCurrentLocation().addItem(item);
    }

    /**
     * @param input The attack of the npc that is attacking
     */
    public void attackPlayer(String input){
	int attack = Integer.parseInt(input);
	int damage = Math.max(attack - player.getStats().getDefense(), 0);
	player.takeDamage(damage);
	String enemy = combat.getCurrentTarget().getName();

	System.out.println(enemy + " attacked you for " + damage + " damage");
	System.out.println("You have " + player.getCurrentHealth() + " health left");
	System.out.println();
    }

    /**
     * @param input The name of the npc to remove
     */
    public void removeNpc(String input){
	player.getCurrentLocation().removeNpc(input);
    }

    /**
     * @param input The name of the exit to remove
     */
    public void removeExit(String input){
	player.getCurrentLocation().removeExit(input);
    }

    public void removeItem(String input){
	player.getCurrentLocation().removeItem(input);
    }

    private class MasterParser extends InputParser
    {
	private MasterParser(){
	    parseInputs.put("say", Game.this::say);

	    parseInputs.put("giveplayerhealth", player::increaseHealth);
	    parseInputs.put("giveplayerenergy", player::addEnergy);
	    parseInputs.put("giveplayerattack", player::increaseAttack);
	    parseInputs.put("giveplayerdefense", player::increaseDefense);
	    parseInputs.put("attackplayer", Game.this::attackPlayer);

	    parseInputs.put("givenpcenergy", Game.this::giveNpcEnergy);
	    parseInputs.put("givenpchealth", Game.this::giveNpcHealth);
	    parseInputs.put("givenpcattack", Game.this::giveNpcAttack);
	    parseInputs.put("givenpcdefense", Game.this::giveNpcDefense);

	    parseInputs.put("moveplayer", player::move);

	    parseInputs.put("giveitem", Game.this::giveItem);

	    parseInputs.put("engage", Game.this::engage);
	    parseInputs.put("disengage", Game.this::disEngage);
	    parseInputs.put("currentmode", Game.this::currentMode);

	    parseInputs.put("win", Game.this::win);
	    parseInputs.put("lose", Game.this::lose);

	    parseInputs.put("spawnnpc", Game.this::spawnNpc);
	    parseInputs.put("spawnexit", Game.this::spawnExit);
	    parseInputs.put("spawnitem", Game.this::spawnItem);
	    parseInputs.put("removenpc", Game.this::removeNpc);
	    parseInputs.put("removeexit", Game.this::removeExit);
	    parseInputs.put("removeitem", Game.this::removeItem);

	    //useItem


	}
    }
}
