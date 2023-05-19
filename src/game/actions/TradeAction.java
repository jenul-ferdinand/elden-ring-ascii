package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;


/**
 * Trading an item or weapon.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class TradeAction extends Action {
    /**
     * The item the actor offers to trade
     */
    private final Item itemToTrade;

    /**
     * The item the actor want's to get from the trade
     */
    private Item itemToReceive;

    /**
     * The weapon the actor want's to get from the trade
     */
    private WeaponItem weaponToReceive;

    /**
     * This will store the toString of the item/weapon we are receiving
     */
    private final String str;


    /**
     * Constructor for Item
     * @param itemToTrade - The Item we are giving
     * @param itemToReceive - The Item we are receiving
     */
    public TradeAction(Item itemToTrade, Item itemToReceive) {
        this.itemToTrade = itemToTrade;
        this.itemToReceive = itemToReceive;

        str = itemToReceive.toString();
    }

    /**
     * Constructor for WeaponItem
     * @param itemToTrade - The Item we are giving
     * @param weaponToReceive - The WeaponItem we are receiving
     */
    public TradeAction(Item itemToTrade, WeaponItem weaponToReceive) {
        this.itemToTrade = itemToTrade;
        this.weaponToReceive = weaponToReceive;

        str = weaponToReceive.toString();
    }



    /**
     * Execute the action to exchange the items
     * @param actor - The actor performing the action.
     * @param map - The map the actor is on.
     * @return String - Confirmation of action string
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // Remove item from actor
        actor.removeItemFromInventory(itemToTrade);

        // Give weapon to actor
        if (weaponToReceive != null) actor.addWeaponToInventory(weaponToReceive);

        // Give item to actor
        if (itemToReceive != null) actor.addItemToInventory(itemToReceive);

        // Return the confirmation string
        return "Traded " + itemToTrade.toString() + " for " + str;
    }



    /**
     * This will be the String of the menu option for the user to choose
     * @param actor - The actor performing the action.
     * @return String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "🤝 Trade " + itemToTrade.toString() + " for " + str;
    }
}
