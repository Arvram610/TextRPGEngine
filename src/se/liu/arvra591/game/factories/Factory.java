package se.liu.arvra591.game.factories;

import com.google.gson.Gson;
import se.liu.arvra591.game.objects.AbstractObject;

/**
 * A class that generates copies of a given item
 *
 * @param <T> The type of object the factory generates
 */
public class Factory<T extends AbstractObject>
{
    protected T targetObject;
    protected Gson gson;

    /**
     * @param targetObject The targetobject that the factory clones
     */
    public Factory(T targetObject) {
	this.targetObject = targetObject;
	gson = new Gson();
    }

    /**
     * The method used to clone the object Says it does unchecked cast, but all casts are garanteed to be of type T
     *
     * @return Returns a copy of the object
     */
    @SuppressWarnings("unchecked") public T generate() {
	return (T) gson.fromJson(gson.toJson(targetObject), targetObject.getClass());
    }
}
