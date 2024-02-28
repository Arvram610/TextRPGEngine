package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.ListHelper;
import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.items.Item;

import java.util.List;

public class ObjectContainer <T extends AbstractObject>
{
    protected List<T> objectList;

    public ObjectContainer(List<T> itemList){
	this.objectList = itemList;
    }

    public List<T> getObjectList() {
	return objectList;
    }

    public void printContainer(){
	for (T object : objectList) {
	    object.printObject();
	}
    }

    public T removeObject(String name){
	AbstractObject object = ListHelper.findObjectInList(objectList, name);
	if (object != null) {
	    objectList.remove(object);
	    return (T) object;
	}
	return null;
    }

    public T removeObject(T object) {
	return removeObject(object.getName());
    }

    public T getObject(String name){
	AbstractObject object = ListHelper.findObjectInList(objectList, name);
	if (object != null) {
	    return (T) object;
	}
	return null;
    }

    public void forceAddObject(T object){
	objectList.add(object);
    }

    public boolean addObject(final T object) {
	if (objectList.contains(object)) {
	    return false;
	}
	forceAddObject(object);
	return true;
    }
}
