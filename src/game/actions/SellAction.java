package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.RuneManager;
import game.traders.Trader;

/**
 * Selling an Item.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class SellAction extends Action {
    /**
     * The item being sold
     */
    private Item item;

    /**
     * The weapon being sold
     */
    private WeaponItem weapon;

    /**
     * The name of the weapon or item
     */
    private String name;

    /**
     * The profit of the sale
     */
    private final int profit;

    /**
     * The instance of the Trader
     */
    private final Trader trader;

    /**
     * The singleton instance of the RuneManager
     */
    private final RuneManager runeManager;



    /**
     * Constructor
     * @param item The item being sold
     * @param profit The profit of the sale
     */
    public SellAction(Item item, int profit, Trader trader) {
        this.item = item;
        this.profit = profit;
        this.trader = trader;
        this.runeManager = RuneManager.getRuneManager();

        this.name = item.toString();
    }

    public SellAction(WeaponItem weapon, int profit, Trader trader) {
        this.weapon = weapon;
        this.profit = profit;
        this.trader = trader;
        this.runeManager = RuneManager.getRuneManager();

        this.name = weapon.toString();
    }



    /**
     * The player will receive the amount of profit to their balance
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String Confirmation message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Add the profit
        runeManager.addRunes(actor, profit);

        // Remove the weapon
        if (weapon != null) actor.removeWeaponFromInventory(weapon);

        // Remove the item
        if (item != null) actor.removeItemFromInventory(item);

        // Confirmation message
        return "The player sold " + name + " for " + profit + " Runes";
    }



    /**
     * The menu option description
     * @param actor The actor performing the action.
     * @return String Returns the menu option text String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "💲 Sell " + name + " to " + trader.toString() + " for " + profit + " Runes";
    }
}
