package se.liu.arvra591.factories;

import com.google.gson.Gson;
import se.liu.arvra591.objects.AbstractObject;

public abstract class Factory<T extends AbstractObject>
{
    protected T targetObject;
    Gson gson;

    protected Factory(T targetObject){
	this.targetObject = targetObject;
	gson = new Gson();
    }

    public T gen() {
	return (T) gson.fromJson(gson.toJson(targetObject), targetObject.getClass());
    }
}
