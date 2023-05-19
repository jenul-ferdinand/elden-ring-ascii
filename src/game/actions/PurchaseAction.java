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
    private Item item;

    /**
     * Weapon to purchase
     */
    private WeaponItem weapon;

    /**
     * The name of the item/weapon
     */
    private final String name;

    /**
     * Cost of the item
     */
    private final int cost;

    /**
     *
     */
    private final RuneManager runeManager;



    /**
     * Constructor for Item
     * @param item - The Item being sold
     * @param cost - The cost of the item
     */
    public PurchaseAction(Item item, int cost) {
        this.item = item;
        this.cost = cost;

        // Get the singleton instance of the RuneManager
        this.runeManager = RuneManager.getRuneManager();

        // Store the name of the item
        this.name = item.toString();
    }

    /**
     * Constructor for WeaponItem
     * @param weapon - The WeaponItem being sold
     * @param cost - The cost of the item
     */
    public PurchaseAction(WeaponItem weapon, int cost) {
        this.weapon = weapon;
        this.cost = cost;

        // Get the singleton instance of the RuneManager
        this.runeManager = RuneManager.getRuneManager();

        // Store the name of the weapon
        this.name = weapon.toString();
    }



    /**
     * This method will add the item to the player's inventory and deduct the runes based on the cost
     * @param actor The receiver of the item being sold
     * @param map The map the actor is on.
     * @return String This String will be printed as confirmation
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if actor can afford the item
        if (runeManager.getRunes(actor) >= cost) {

            // Give the weapon to actor
            if (weapon != null) actor.addWeaponToInventory(weapon);

            // Give the item to actor
            if (item != null) actor.addItemToInventory(item);

            // Subtract runes from actor
            runeManager.subtractRunes(actor, cost);

            // Confirmation message
            return "Purchased " + item + " for " + cost + " runes.";
        }

        // Failure message
        return "Insufficient Balance";
    }



    /**
     * This will be the String of the menu option for the user to choose
     * @param actor The actor performing the action.
     * @return String The menu option String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "🛒 Purchase " + name + " from Trader for " + cost + " runes";
    }
}
