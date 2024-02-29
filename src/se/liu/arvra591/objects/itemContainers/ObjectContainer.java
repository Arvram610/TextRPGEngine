package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.ListHelper;
import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.items.Item;

import java.util.List;

/**
 * Class for ObjectContainers. ObjectContainers are containers for objects. They can contain items, npcs, locations, etc
 * ObjectContainers can print the objects in the container, remove and add objects to the container
 */
public class ObjectContainer <T extends AbstractObject>
{
    protected List<T> objectList;

    /**
     * @param itemList is the list of items in the Container
     */
    public ObjectContainer(List<T> itemList){
	this.objectList = itemList;
    }

    /**
     * @return Returns the list of items in the Container
     */
    public List<T> getObjectList() {
	return objectList;
    }

    /**
     * Prints the items in the Container
     */
    public void printContainer(){
	for (T object : objectList) {
	    object.printObject();
	}
    }

    /**
     * @param name is the name of the Object to remove
     *
     * @return Returns the Object removed from the container
     */
    public T removeObject(String name){
	AbstractObject object = ListHelper.findObjectInList(objectList, name);
	if (object != null) {
	    objectList.remove(object);
	    return (T) object;
	}
	return null;
    }

    /**
     * @param object is the object to remove
     *
     * this function is called when the parameter is the object itseflf nstead of the name of the object
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
    public T getObject(String name){
	AbstractObject object = ListHelper.findObjectInList(objectList, name);
	if (object != null) {
	    return (T) object;
	}
	return null;
    }

    /**
     * @param object is the object to add
     * adds the object to the inventory
     */
    public void forceAddObject(T object){
	objectList.add(object);
    }

    /**
     * @param object is the object to add
     *
     * @return true if the object was added, false if the object already exists in the inventory
     */
    public boolean addObject(final T object) {
	if (objectList.contains(object)) {
	    return false;
	}
	forceAddObject(object);
	return true;
    }
}
