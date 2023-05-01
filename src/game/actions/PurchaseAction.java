package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class PurchaseAction extends Action {
    /**
     * The Actor to give the item to
     */
    private Actor receiver;

    /**
     * Item to purchase
     */
    private final Item item;

    /**
     * Constructor
     * @param receiver The recipient of the item
     * @param item The item to be sold
     */
    public PurchaseAction(Actor receiver, Item item) {
        this.receiver = receiver;
        this.item = item;
    }

    /**
     * The method will add the item to the receiver's inventory
     * @param receiver The receiver of the item being sold
     * @param map The map the actor is on.
     * @return String This String will be printed as confirmation
     */
    @Override
    public String execute(Actor receiver, GameMap map) {
        // TODO: Check for player Runes balance

        // Add the item to the receiver's inventory
        receiver.addItemToInventory(item);

        // Return a message once executed
        return "Purchased " + item + " for 600 runes.";
    }

    /**
     * A String to be printed for the menu option
     * @param actor The actor performing the action.
     * @return String The menu option String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Purchase " + item + " from Trader for 600 runes";
    }
}
