package se.liu.arvra591.factories;

import com.google.gson.Gson;
import se.liu.arvra591.objects.AbstractObject;

public class Factory<T extends AbstractObject>
{
    protected T targetObject;
    protected Gson gson;

    public Factory(T targetObject){
	this.targetObject = targetObject;
	gson = new Gson();
    }

    @SuppressWarnings("unchecked")
    public T gen() {
	return (T) gson.fromJson(gson.toJson(targetObject), targetObject.getClass());
    }
}
