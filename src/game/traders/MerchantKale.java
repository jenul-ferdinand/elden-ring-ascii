package game.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.items.Club;
import game.items.Grossmesser;
import game.items.Scimitar;
import game.utils.Status;



/**
 * The first trader the player encounters.
 * This trader buys and sells weapons to trade capable Actors.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class MerchantKale extends Trader {



    /**
     * Constructor.
     */
    public MerchantKale() {
        // Superclass attributes
        super("Merchant Kale", 'K', 999);

        // Initialise the items we have
        initItem(new Club(), 10, 150, DealType.BOTH);
        initItem(new Scimitar(), 600, 100, DealType.BOTH);
        initItem(new Grossmesser(), 0, 100, DealType.BUYING);
    }



    /**
     * The merchant does not move
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action DoNothingAction()
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Do nothing
        return new DoNothingAction();

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

        // Check if the other actor has the capability to trade with us
        if(otherActor.hasCapability(Status.TRADE_CAPABLE)) {

            // ==== SELLING TO ACTOR ====
            // Loop through the items of the items map
            for (Item item : items.keySet()) {

                // Check if the current item is up for sale
                if (getDealType(item) == DealType.SELLING.ordinal() || getDealType(item) == DealType.BOTH.ordinal()) {

                    // Add the purchase action for that item
                    actions.add(new PurchaseAction(otherActor, item, getSellingPrice(item)));
                }

            }

            // ==== BUYING FROM ACTOR ====
            // Loop through other actor's weapons
            for (Item weaponItem : otherActor.getWeaponInventory())  {
                // Loop through our items
                for (Item item : items.keySet()) {

                    // Check if the name's of both items are the same
                    if (item.toString() == weaponItem.toString()) {

                        // Add the sell action to the actions list
                        actions.add(new SellAction(weaponItem, getBuyingPrice(item)));

                    }

                }
            }


        }

        // Return the action list
        return actions;
    }



}
