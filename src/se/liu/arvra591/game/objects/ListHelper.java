package se.liu.arvra591.game.objects;

import java.util.List;


/**
 * A helper class that helps find abstractobjects in lists based on a provided name
 */
public class ListHelper
{

    /**
     * @param objects A list containing {@link List<AbstractObject>} items that contain {@link AbstractObject}
     * @param name    A string with the name of the object you want to find
     *
     * @return It returns either the object if it is found or null
     */
    public static AbstractObject findObjectInLists(List<List<? extends AbstractObject>> objectsCol, String name) {
	for (List<? extends AbstractObject> objects : objectsCol) { // For each object in the object of objects kodgranskning complains on variablename but its good?
	    AbstractObject object = findObjectInList(objects, name);
	    if (object != null) {
		return object;
	    }
	}
	return null;
    }

    /**
     * @param objects A objects containing {@link AbstractObject} items
     * @param name    A string with the name of the object you want to find
     *
     * @return It returns either the object if it is found or null
     */
    @SuppressWarnings("MaybeTypeCheck") public static <T extends AbstractObject> T findObjectInList(List<T> objects,
												    String name)
    {//name includes type but that is what it is, dont knmow better name
	return objects.stream().filter(object -> object.getName().equals(name)).findFirst()
		.orElse(null); //Kodgranskning complains aboput type check but is wrong
    }

    /**
     * @param objects A objects containing {@link AbstractObject} items
     * @param tab,    a boolean that decides if the objects should be tabbed or not
     */
    public static void printList(List<? extends AbstractObject> objects, boolean tab) {
	for (AbstractObject item : objects) {
	    if (tab) {
		System.out.print("  ");
	    }
	    System.out.println(item.getName());
	}
    }
}
