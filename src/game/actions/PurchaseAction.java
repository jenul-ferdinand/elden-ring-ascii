package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;
import game.items.RuneManager;

/**
 * Purchasing an item.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
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
     * Cost of the item
     */
    private final int cost;

    /**
     *
     */
    private final RuneManager runeManager;



    /**
     * Constructor
     * @param receiver The recipient of the item
     * @param item The item to be sold
     */
    public PurchaseAction(Actor receiver, Item item, int cost) {
        this.receiver = receiver;
        this.item = item;
        this.cost = cost;

        this.runeManager = RuneManager.getRuneManager();
    }



    /**
     * This method will add the item to the player's inventory and deduct the runes based on the cost
     * @param actor The receiver of the item being sold
     * @param map The map the actor is on.
     * @return String This String will be printed as confirmation
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if the actor has enough runes
        if (runeManager.getRunes(actor) >= cost) {

            // Add the item to the receiver's inventory
            actor.addWeaponToInventory((WeaponItem) item);

            // Deduct the cost from the player's runes balance
            runeManager.subtractRunes(actor, cost);

            // Return confirmation message
            return "Purchased " + item + " for " + cost + " runes.";
        }

        // Return if the player is too broke
        return "Insufficient Balance";
    }



    /**
     * This will be the String of the menu option for the user to choose
     * @param actor The actor performing the action.
     * @return String The menu option String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "🛒 Purchase " + item + " from Trader for " + cost + " runes";
    }
}
