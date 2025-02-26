package se.liu.arvra591.game.objects.containers;

import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.objects.ListHelper;

import java.util.List;

/**
 * Class for ObjectContainers. ObjectContainers are containers for objects. They can contain items, npcs, locations, etc ObjectContainers
 * can print the objects in the container, remove and add objects to the container
 */
public class ObjectContainer<T extends AbstractObject>
{
    protected List<T> objects;

    /**
     * @param items is the list of items in the Container
     */
    public ObjectContainer(List<T> items) {
	this.objects = items;
    }

    /**
     * @return Returns the list of items in the Container
     */
    public List<T> getObjects() {
	return objects;
    }

    /**
     * Prints the items in the Container
     */
    public void printContainer() {
	for (T object : objects) {
	    object.printObject();
	    System.out.println();
	}
    }

    /**
     * @param name is the name of the Object to remove
     *
     * @return Returns the Object removed from the container
     */
    public T removeObject(String name) {
	T object = ListHelper.findObjectInList(objects, name);
	if (object != null) {
	    objects.remove(object);
	    return object;
	}
	return null;
    }

    /**
     * this function is called when the parameter is the object itseflf nstead of the name of the object
     *
     * @param object is the object to remove
     *
     * @return Returns the object removed from the inventory
     */
    public T removeObject(T object) {
	return removeObject(object.getName());
    }

    /**
     * @param name is the name of the object to get
     *
     * @return Returns the object from the inventory
     */
    public T getObject(String name) {
	T object = ListHelper.findObjectInList(objects, name);
	return object;
    }

    /**
     * @param object is the object to add adds the object to the inventory
     */
    public void forceAddObject(T object) {
	objects.add(object);
    }

    /**
     * @param object is the object to add
     *
     * @return true if the object was added, false if the object already exists in the inventory
     */
    public boolean addObject(final T object) {
	if (objects.contains(object)) {
	    return false;
	}
	forceAddObject(object);
	return true;
    }
}
