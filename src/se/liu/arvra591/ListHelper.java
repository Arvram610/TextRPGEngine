package se.liu.arvra591;

import se.liu.arvra591.objects.AbstractObject;

import java.util.List;

public class ListHelper
{
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

    public static AbstractObject findObjectInList(List<? extends AbstractObject> list, String name){
	return list.stream().filter(object -> object.getName().equals(name)).findFirst().orElse(null);
    }

    public static void printList(List<? extends AbstractObject> list, boolean tab) {
	for (AbstractObject item : list) {
	    if (tab)
		System.out.print("\t");
	    System.out.println(item.getName());
	}
    }
}
