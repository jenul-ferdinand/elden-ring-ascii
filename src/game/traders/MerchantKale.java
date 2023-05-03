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
import game.items.Scimitar;
import game.utils.Status;

import java.util.ArrayList;

public class MerchantKale extends Trader {

    /**
     * A list for the items in the merhant's inventory
     */
    private final ArrayList<Item> inventory = new ArrayList<>();

    /**
     * A list for the prices that the merchant will sell for
     */
    private final ArrayList<Integer> sellPrices = new ArrayList<>();

    /**
     * A list for the prices that the merchant will buy for
     */
    private final ArrayList<Integer> buyPrices = new ArrayList<>();



    /**
     * Constructor.
     */
    public MerchantKale() {

        // Superclass attributes
        super("Merchant Kale", 'K', 999);

        // Initial Inventory
        // Club
        this.inventory.add(new Club());
        this.sellPrices.add(600);

        // Scimitar
        this.inventory.add(new Scimitar());
        this.sellPrices.add(600);

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

        // If the other actor has capability HOSTILE_TO_ENEMY, assuming this is the player.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {

            // Purchasing
            for (int i = 0; i < inventory.size(); i++) {

                // Add the purchase action to the action list
                actions.add(new PurchaseAction(otherActor, inventory.get(i), sellPrices.get(i)));

            }


            // Selling
            for (int k = 0; k < otherActor.getWeaponInventory().size(); k++) {

                // Weapon item
                Item weaponItem = otherActor.getWeaponInventory().get(k);

                // Switch for weapon buying prices
                switch (weaponItem.toString()) {
                    case "Club", "Grossmesser", "Scimitar" -> buyPrices.add(100);
                }

                // Add the sell action to the actions list
                actions.add(new SellAction(weaponItem, buyPrices.get(k)));

            }

        }

        // Return the action list
        return actions;
    }



}
