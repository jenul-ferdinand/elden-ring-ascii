package game.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.TradeAction;
import game.items.*;
import game.utils.Status;



/**
 * The first trader the player encounters.
 * This trader buys and sells weapons to trade capable Actors.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class FingerReaderEnia extends Trader {



    /**
     * Constructor.
     */
    public FingerReaderEnia() {
        // Superclass attributes
        super("Finger Reader Enia", 'E', 999);

        // Weapons
        initWeapon(new AxeOfGodrick(),  0, 100, DealType.TRADING);
        initWeapon(new GraftedDragon(), 0, 100, DealType.TRADING);
        initWeapon(new Club(),          0, 40, DealType.BUYING);
        initWeapon(new Grossmesser(),   0, 70, DealType.BUYING);
        initWeapon(new Scimitar(),      0, 70, DealType.BUYING);

        // Items
        initItem(new GoldenSeed(), 0, 7500, DealType.BUYING);
        initItem(new RemembranceOfTheGrafted(), 0, 20000, DealType.BUYING);
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

        if (otherActor.hasCapability(Status.TRADE_CAPABLE)) {

            // ===== Trading for Remembrance of the Grafted =====
            // Loop through the other actor's inventory
            for (Item otherItem : otherActor.getItemInventory()) {
                // Loop through our items inventory
                for (WeaponItem ourWeapon : weapons.keySet()) {

                    // Check if the otherItem is the Remembrance of the Grafted
                    if (otherItem.toString() == "Remembrance of the Grafted") { // Or you can create a TRADABLE capability for extensibility

                        // Check if ourItem is tradable
                        if (getWeaponDealType(ourWeapon) == DealType.TRADING.ordinal()) {

                            // Add the TradeAction for this item
                            actions.add(new TradeAction(otherItem, ourWeapon));

                        }
                    }
                }
            }
        }

        // Selling to actor functionality
        sellingToActor(otherActor, actions);

        // Buying from actor functionality
        buyingFromActor(otherActor, actions);

        // Return the action list
        return actions;
    }
}

