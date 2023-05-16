package game.traders;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
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
        BOTH
    }



    /**
     * This will store the inventory of items for the Trader, and it will also store specific details for that item
     */
    protected final HashMap<Item, ArrayList<Integer>> items;



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
     * @return Integer - Returns the third index of the ArrayList which is where the DealType is stored
     */
    public int getDealType(Item item) {
        return items.get(item).get(2);
    }



    /**
     * Gets the selling price of the Item
     * @param item - The item to get the price of
     * @return Integer - The selling price
     */
    public int getSellingPrice(Item item) {
        return items.get(item).get(DealType.SELLING.ordinal());
    }



    /**
     * Gets the buying price of the Item
     * @param item - The item to get the price of
     * @return Integer - The buying price
     */
    public int getBuyingPrice(Item item) {
        return items.get(item).get(DealType.BUYING.ordinal());
    }


}
