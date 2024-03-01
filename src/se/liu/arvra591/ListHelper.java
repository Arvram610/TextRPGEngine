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
	for (List<? extends AbstractObject> list : lists) { // For each list in the list of lists kodgranskning complains on variablename but its good?
	    AbstractObject object = findObjectInList(list, name);
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
    public static AbstractObject findObjectInList(List<? extends AbstractObject> list, String name){//name includes type but that is what it is, dont knmow better name
	return list.stream().filter(object -> object.getName().equals(name)).findFirst().orElse(null); //Kodgranskning complains aboput type check but is wrong
    }

    /**
     * @param list A list containing {@link AbstractObject} items
     * @param tab, a boolean that decides if the list should be tabbed or not
     */
    public static void printList(List<? extends AbstractObject> list, boolean tab) {
	for (AbstractObject item : list) {
	    if (tab)
		System.out.print("  ");
	    System.out.println(item.getName());
	}
    }
}
