package se.liu.arvra591.controllers.items;
/**
 * Class to control items
 */
import se.liu.arvra591.controllers.AbstractObjectController;
import se.liu.arvra591.models.items.Item;
import se.liu.arvra591.view.items.ItemView;

public class ItemController extends AbstractObjectController
{
    public ItemController(final Item model, final ItemView view) {
	super(model, view);
    }
}
