package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;

public class SellAction extends Action {
    /**
     * The item being sold
     */
    private final Item item;

    /**
     * The profit of the sale
     */
    private final int profit;

    /**
     * Constructor
     * @param item The item being sold
     * @param profit The profit of the sale
     */
    public SellAction(Item item, int profit) {
        this.item = item;
        this.profit = profit;
    }

    /**
     * The player will recieve the amount of profit to their balance
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String Confirmation message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Define the player
        Player player = (Player) actor;

        // Add the profit of the sale to the player's balance
        player.addRunes(profit);

        // Remove the weapon item from the inventory of the player
        player.removeWeaponFromInventory((WeaponItem) item);

        // Return the confirmation message
        return "The player sold " + item + " for " + profit + " Runes";
    }

    /**
     * The menu option description
     * @param actor The actor performing the action.
     * @return String Returns the menu option text String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "💲 Sell " + item + " to MerchantKale for " + profit + " Runes";
    }
}
