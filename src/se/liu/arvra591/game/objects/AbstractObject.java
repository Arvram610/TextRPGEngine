package se.liu.arvra591.game.objects;


import se.liu.arvra591.game.listeners.CommandHandler;

import java.util.List;

/**
 * All objects in the game inherit from this class, objects includes locations as they are physical All objects have a name and a
 * description
 */
public abstract class AbstractObject
{
    protected String name;
    protected String description;
    private CommandHandler commandHandler;

    protected AbstractObject(String name, String description) {
	this.name = name;
	this.description = description;
	commandHandler = null;
    }

    /**
     * @return Returns the name of the object
     */
    public String getName() {
	return name;
    }


    /**
     * Prints the objects info
     */
    public void printObject() {
	System.out.println("Name: " + name);
	System.out.println("Type: " + getClass().getSimpleName());
	System.out.println("Description: " + description);
    }

    /**
     * Prints the name of the object
     */
    public void printName() {
	System.out.println(name);
    }

    /**
     * Prints the description of the object
     */
    public void printDescription() {
	System.out.println(description);
    }

    /**
     * @param commandHandler The command handler to set
     */
    public void setCommandHandler(final CommandHandler commandHandler) {
	this.commandHandler = commandHandler;
    }

    /**
     * @param command The command to send
     */
    protected void sendCommand(final String command) {
	commandHandler.notifyListener(command);
    }

    /**
     * @param commands The commands to send
     */
    protected void sendCommands(final List<String> commands) {
	commands.forEach(this::sendCommand);
    }
}
