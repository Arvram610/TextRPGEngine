package se.liu.arvra591;

import se.liu.arvra591.objects.AbstractObject;

import java.util.List;


/**
 * A helper class that helps find abstractobjects in lists based on a provided name
 */
public class ListHelper
{

    /**
     * @param lists A list containing {@link List<AbstractObject>} items that contain {@link AbstractObject}
     * @param name A string with the name of the object you want to find
     *
     * @return It returns either the object if it is found or null
     */
    public static AbstractObject findObjectInLists(List<List<? extends AbstractObject>> lists, String name){
	AbstractObject object;

	for (List<? extends AbstractObject> list : lists) {
	    object = findObjectInList(list, name);
	    if (object != null){
		return object;
	    }
	}
	return null;
    }

    /**
     * @param list A list containing {@link AbstractObject} items
     * @param name A string with the name of the object you want to find
     *
     * @return It returns either the object if it is found or null
     */
    public static AbstractObject findObjectInList(List<? extends AbstractObject> list, String name){
	return list.stream().filter(object -> object.getObjectName().equals(name)).findFirst().orElse(null);
    }
}
