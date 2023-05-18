package game.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.utils.Status;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parent class Trader.
 * Manages the storing of items and their specific values
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public abstract class Trader extends Actor {



    /**
     * This enumerator will be used for each item in the map, to specify whether that item can only be sold, bought, or both
     * SELLING = We are only selling this item to the other actor
     * BUYING = We are only buying this actor from the other actor
     * BOTH = We are both buying and selling this item
     */
    enum DealType {
        SELLING,
        BUYING,
        BOTH,
        TRADING,
    }



    /**
     * This will store the inventory of items for the Trader, and it will also store specific pricing details
     */
    protected final HashMap<Item, ArrayList<Integer>> items;

    /**
     * This will store the inventory of weapons for the Trader, and it will also store specific pricing details
     */
    protected final HashMap<WeaponItem, ArrayList<Integer>> weapons;



    /**
     * Constructor.
     * @param name - the name of the Actor
     * @param displayChar - the character that will represent the Actor in the display
     * @param hitPoints - the Actor's starting hit points
     */
    public Trader(String name, char displayChar, int hitPoints) {
        // Superclass attributes
        super(name, displayChar, hitPoints);

        // Init items map
        items = new HashMap<>();

        weapons = new HashMap<>();
    }



    /**
     * Functionality to sell to actor and create a SellAction
     * @param otherActor - The actor to sell to
     * @param actions - The ActionList to add to
     */
    public void sellingToActor(Actor otherActor, ActionList actions) {
        // Check if the other actor has the capability to trade with us
        if (otherActor.hasCapability(Status.TRADE_CAPABLE)) {

            // ==== SELLING TO ACTOR ====
            // Loop through the items of the items map
            for (Item item : items.keySet()) {

                // Check if the current item is up for sale
                if (getItemDealType(item) == DealType.SELLING.ordinal() || getItemDealType(item) == DealType.BOTH.ordinal()) {

                    // Add the purchase action for that item
                    actions.add(new PurchaseAction(item, getItemSellingPrice(item)));
                }
            }

            // Loop through the items of the items map
            for (WeaponItem weapon : weapons.keySet()) {

                // Check if the current item is up for sale
                if (getWeaponDealType(weapon) == DealType.SELLING.ordinal() || getWeaponDealType(weapon) == DealType.BOTH.ordinal()) {

                    // Add the purchase action for that item
                    actions.add(new PurchaseAction(weapon, getWeaponSellingPrice(weapon)));
                }
            }
        }
    }



    /**
     * Functionality to buy from actor and create a PurchaseAction
     * @param otherActor - The actor to buy from
     * @param actions - The ActionList to add to
     */
    public void buyingFromActor(Actor otherActor, ActionList actions) {
        // Check if the other actor has the capability to trade with us
        if(otherActor.hasCapability(Status.TRADE_CAPABLE)) {
            // ==== BUYING FROM ACTOR ====
            for (WeaponItem otherWeapon : otherActor.getWeaponInventory()) {
                for (WeaponItem weapon : weapons.keySet()) {
                    if (weapon.toString() == otherWeapon.toString()) {
                        actions.add(new SellAction(otherWeapon, getWeaponBuyingPrice(weapon), this));
                    }
                }
            }

            for (Item otherItem : otherActor.getWeaponInventory()) {
                for (Item item : items.keySet()) {
                    if (item.toString() == otherItem.toString()) {
                        actions.add(new SellAction(otherItem, getItemBuyingPrice(item), this));
                    }
                }
            }
        }
    }



    /**
     * Merchant actions
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList Returns the list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        // Create a new list of actions
        ActionList actions = new ActionList();

        sellingToActor(otherActor, actions);

        buyingFromActor(otherActor, actions);

        // Return the action list
        return actions;
    }



    /**
     * Initializes an item in the items map, with all the required values
     * @param item - The item to store
     * @param sellPrice - The selling price
     * @param buyPrice - The buying price
     * @param dealType - The dealing type
     */
    public void initItem(Item item, int sellPrice, int buyPrice, DealType dealType) {
        items.put(item, new ArrayList<>());
        items.get(item).add(sellPrice);
        items.get(item).add(buyPrice);
        items.get(item).add(dealType.ordinal());
    }



    /**
     * Gets the deal type of the item
     * @param item - Item to check
     * @return Integer - Returns the third index of the ArrayList which is where the DealType value is stored
     */
    public int getItemDealType(Item item) {
        return items.get(item).get(2);
    }



    /**
     * Gets the selling price of the Item
     * @param item - The item to get the price of
     * @return Integer - The selling price
     */
    public int getItemSellingPrice(Item item) {
        return items.get(item).get(DealType.SELLING.ordinal());
    }



    /**
     * Gets the buying price of the Item
     * @param item - The item to get the price of
     * @return Integer - The buying price
     */
    public int getItemBuyingPrice(Item item) {
        return items.get(item).get(DealType.BUYING.ordinal());
    }



    /**
     * Initializes an item in the items map, with all the required values
     * @param weapon - The weapon to store
     * @param sellPrice - The selling price
     * @param buyPrice - The buying price
     * @param dealType - The dealing type
     */
    public void initWeapon(WeaponItem weapon, int sellPrice, int buyPrice, DealType dealType) {
        weapons.put(weapon, new ArrayList<>());
        weapons.get(weapon).add(sellPrice);
        weapons.get(weapon).add(buyPrice);
        weapons.get(weapon).add(dealType.ordinal());
    }



    /**
     * Gets the deal type of the item
     * @param weapon - Weapon to check
     * @return Integer - Returns the third index of the ArrayList which is where the DealType value is stored
     */
    public int getWeaponDealType(WeaponItem weapon) {
        return weapons.get(weapon).get(2);
    }



    /**
     * Gets the selling price of the Item
     * @param weapon - The weapon to get the price of
     * @return Integer - The selling price
     */
    public int getWeaponSellingPrice(WeaponItem weapon) {
        return weapons.get(weapon).get(DealType.SELLING.ordinal());
    }



    /**
     * Gets the buying price of the Item
     * @param weapon - The weapon to get the price of
     * @return Integer - The buying price
     */
    public int getWeaponBuyingPrice(WeaponItem weapon) {
        return weapons.get(weapon).get(DealType.BUYING.ordinal());
    }


}
