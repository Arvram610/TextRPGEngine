package se.liu.arvra591;

import se.liu.arvra591.game.listeners.CombatEventHandler;
import se.liu.arvra591.game.listeners.EngageEventHandler;
import se.liu.arvra591.game.Game;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;
import se.liu.arvra591.game.generators.GameGenerator;

import java.util.Map;

/**
 * The main class for the game
 * This is the file you run to start the game
 * It handles logic on what mode the player is in (ex movement or combat)
 */
public class Main
{
    private Player player;
    private Game game;

    /**
     * Constructor for the main class, initializes what is needed for the game to run
     */
    public Main(){
	CommandHandler commandHandler = new CommandHandler();
	GameGenerator gameGenerator = new GameGenerator(commandHandler);
	gameGenerator.generateGame();
	player = gameGenerator.getPlayer();
	Map<String, Location> locations = gameGenerator.getLocations();
	Map<String, Factory<? extends Item>> items = gameGenerator.getItems();
	Map<String, Factory<? extends Npc>> npcs = gameGenerator.getNpcs();
	EngageEventHandler engageEventHandler = new EngageEventHandler();
	CombatEventHandler combatEventHandler = new CombatEventHandler();
	game = new Game(player, locations, items, npcs, engageEventHandler, combatEventHandler);

	commandHandler.setListener(game.getParser());
    }

    /**
     * This method starts the game
     */
    public void startGame(){

	//player.printObject();

	Location currentLocation = player.getCurrentLocation();
	//currentLocation.printObject();
	game.parseInput("currentmode");
	game.processInput("engage Carl");
	game.parseInput("giveplayerenergy 100");
	game.processInput("attack");
	game.processInput("attack");
	game.processInput("attack");
	game.parseInput("currentmode");
    }


    /**
     * The method used to start the game
     *
     * @param args Ignored
     */
    public static void main(String[] args) {
	Main main = new Main();
	main.startGame();
    }
}
