package se.liu.arvra591.game.factories;

import com.google.gson.Gson;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.AbstractObject;

/**
 * A class that generates copies of a given item It uses gson to convert the object to a json string and then back to an object
 *
 * @param <T> The type of object the factory generates
 */
public class Factory<T extends AbstractObject>
{
    protected T targetObject;
    protected Gson gson;

    protected CommandHandler commandHandler;

    /**
     * @param targetObject The targetobject that the factory clones
     */
    public Factory(T targetObject, CommandHandler commandHandler) {
	this.targetObject = targetObject;
	this.commandHandler = commandHandler;
	gson = new Gson();
    }

    /**
     * The method used to clone the object Says it does unchecked cast, but all casts are guaranteed to be of type T
     *
     * @return Returns a copy of the object
     */
    @SuppressWarnings("unchecked") public T generate() {  // checked with assistant
	T object = (T) gson.fromJson(gson.toJson(targetObject), targetObject.getClass());
	object.setCommandHandler(commandHandler);
	return object;
    }
}
