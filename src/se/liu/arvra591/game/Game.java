package se.liu.arvra591.game;

import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CombatEventHandler;
import se.liu.arvra591.game.listeners.CombatListener;
import se.liu.arvra591.game.listeners.EngageEventHandler;
import se.liu.arvra591.game.listeners.EngageListener;
import se.liu.arvra591.game.modes.Adventure;
import se.liu.arvra591.game.modes.Combat;
import se.liu.arvra591.game.modes.GameState;
import se.liu.arvra591.game.objects.ListHelper;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.NpcLogic;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;
import se.liu.arvra591.game.parsers.InputParser;

import java.util.List;
import java.util.Map;

/**
 * The main parser of the game takes input from the game and parses it to the correct method also takes the input from main and sends it to
 * the correct method
 */
public class Game implements EngageListener, CombatListener
{

    private Player player;
    private MasterParser parser;
    private GameState gameState;
    private Adventure adventure;
    private Combat combat;
    private NpcLogic npcLogic;
    private Map<String, Location> locations;
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Factory<? extends Npc>> npcs;


    /**
     * @param player The player object
     * @param locations The locations in the game
     * @param items The items in the game
     * @param npcs The npcs in the game
     */
    public Game(Player player, Map<String, Location> locations, Map<String, Factory<? extends Item>> items,
		Map<String, Factory<? extends Npc>> npcs)
    {
	this.player = player;
	this.parser = new MasterParser();
	this.locations = locations;
	this.items = items;
	this.npcs = npcs;
	this.gameState = GameState.ADVENTURE;
	EngageEventHandler engageEventHandler = new EngageEventHandler();
	CombatEventHandler combatEventHandler = new CombatEventHandler();
	this.combat = new Combat(player, null, engageEventHandler, combatEventHandler);
	this.adventure = new Adventure(player, engageEventHandler);
	this.npcLogic = new NpcLogic(null);
	engageEventHandler.setListener(this);
	combatEventHandler.setListener(this);
    }


    /**
     * @return The master parser of the game
     */
    public InputParser getParser() {
	return parser;
    }


    /**
     * @param input The input from the object prints a description of the object
     */
    private void say(String input) {
	System.out.println(input);
    }

    /**
     * @param input The input from the object
     */
    private void giveItem(String input) {
	Item item = items.get(input).generate();
	player.forceAddItem(item);
    }

    /**
     * @param input The NPC to engage combat with
     */
    @Override public void engage(String input) {
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
	if (target == null) {
	    System.out.println("There is no npc with that name");
	    return;
	}
	combat.setCurrentTarget(target);
	gameState = GameState.COMBAT;
	combat.startOfCombat();
    }

    /**
     * @param ignored will be empty string
     */
    @Override public void disengage(String ignored) {
	if (gameState == GameState.ADVENTURE) {
	    System.out.println("You are not in combat");
	    return;
	}
	gameState = GameState.ADVENTURE;
	System.out.println("You are no longer in combat");
    }

    /**
     * Notifies the npc logic that it is the npcs turn
     */
    public void notifyNpcLogic() {
	npcLogic.setNpc(combat.getCurrentTarget());
	npcLogic.startOfTurn();
    }

    /**
     * @return The target
     */
    private Npc getTarget() {
	return combat.getCurrentTarget();
    }

    /**
     * @param input The input from the player
     */
    public void processInput(String input) {
	if (gameState == GameState.COMBAT) {
	    combat.parseInput(input);
	} else if (gameState == GameState.ADVENTURE) {
	    adventure.parseInput(input);
	}
    }

    /**
     * @param input The amount of health to give the npc
     */
    private void giveNpcHealth(String input) {
	Npc target = getTarget();
	target.increaseHealth(input);
    }

    /**
     * @param input The amount of energy to give the npc
     */
    private void giveNpcEnergy(String input) {
	Npc target = getTarget();
	target.addEnergy(input);
    }

    /**
     * @param input The amount of attack to give the npc
     */
    private void giveNpcAttack(String input) {
	Npc target = getTarget();
	target.increaseAttack(input);
    }

    /**
     * @param input The amount of defense to give the npc
     */
    private void giveNpcDefense(String input) {
	Npc target = getTarget();
	target.increaseDefense(input);
    }

    /**
     * @param input The input from the player will be empty
     */
    private void currentMode(String ignored) {
	System.out.println("Current mode is: " + gameState);
    }

    /**
     * @param input Will be empty
     */
    private void win(String ignored) {
	gameState = GameState.WIN;
    }

    /**
     * @param input Will be empty
     */
    private void lose(String ignored) {
	gameState = GameState.GAME_OVER;
    }

    /**
     * @param input The name of the npc to spawn
     */
    private void spawnNpc(String input) {
	Npc npc = npcs.get(input).generate();
	player.getCurrentLocation().addNpc(npc);
    }

    /**
     * @param input The name of the exit to spawn
     */
    private void spawnExit(String input) {
	Location exit = locations.get(input);
	player.getCurrentLocation().addExit(exit);
    }


    /**
     * @param input The name of the item to spawn
     */

    private void movePlayer(String input) {
	Location location = locations.get(input);
	if (location == null) {
	    return;
	}
	player.forceMove(location);
	player.getCurrentLocation().roomEntered();
    }

    private void spawnItem(String input) {
	Item item = items.get(input).generate();
	player.getCurrentLocation().addItem(item);
    }


    /**
     * Starts the game
     */
    public void start() {
	player.getCurrentLocation().roomEntered();
    }

    /**
     * @param input The attack of the npc that is attacking
     */
    private void attackPlayer(String input) {
	int attack = Integer.parseInt(input);
	int damage = Math.max(attack - player.getStats().getDefense(), 0);
	player.takeDamage(damage);
	if (!player.isAlive()) {
	    player.onDeath();
	    return;
	}
	String enemy = combat.getCurrentTarget().getName();

	System.out.println(enemy + " attacked you for " + damage + " damage \n");
	combat.startOfRound();
	System.out.println();
    }

    /**
     * @param input The name of the npc to remove
     */
    private void removeNpc(String input) {
	player.getCurrentLocation().removeNpc(input);
    }

    /**
     * @param input The name of the exit to remove
     */
    private void removeExit(String input) {
	player.getCurrentLocation().removeExit(input);
    }

    /**
     * @param input The name of the item to remove
     */
    private void removeItem(String input) {
	player.getCurrentLocation().removeItem(input);
    }

    /**
     * @return Returns if the game is on
     */
    public boolean isGameOn() {
	switch (gameState) {
	    case WIN:
	    case GAME_OVER:
		return false;
	    default:
		return true;
	}
    }

    /**
     * The master parser of the game, Allows the objects to talk to the game
     */
    private class MasterParser extends InputParser
    {
	private MasterParser() {
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

	    parseInputs.put("moveplayer", Game.this::movePlayer);

	    parseInputs.put("giveitem", Game.this::giveItem);

	    parseInputs.put("engage", Game.this::engage);
	    parseInputs.put("disengage", Game.this::disengage);
	    parseInputs.put("currentmode", Game.this::currentMode);

	    parseInputs.put("win", Game.this::win);
	    parseInputs.put("lose", Game.this::lose);

	    parseInputs.put("spawnnpc", Game.this::spawnNpc);
	    parseInputs.put("spawnexit", Game.this::spawnExit);
	    parseInputs.put("spawnitem", Game.this::spawnItem);
	    parseInputs.put("removenpc", Game.this::removeNpc);
	    parseInputs.put("removeexit", Game.this::removeExit);
	    parseInputs.put("removeitem", Game.this::removeItem);
	}
    }
}
