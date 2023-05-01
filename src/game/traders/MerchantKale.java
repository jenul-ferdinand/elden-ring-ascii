package game.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.items.Club;
import game.utils.Status;

public class MerchantKale extends Trader {
    /**
     * Constructor.
     *
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 999);
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
            // Add the purchase action to the action list
            actions.add(new PurchaseAction(otherActor, new Club()));
        }

        // Return the action list
        return actions;
    }
}
