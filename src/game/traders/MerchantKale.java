package game.traders;

import edu.monash.fit2099.demo.mars.behaviours.SpitBehaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviour.Behaviour;

import java.util.HashMap;
import java.util.Map;

public class MerchantKale extends Trader {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 999);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Loop through the actions
        for (Behaviour behaviour : behaviours.values()) {

            // Get action from the behaviours list
            Action action = behaviour.getAction(this, map);

            // If there is an action return it
            if (action != null) {
                return action;
            }
        }

        // Otherwise, do nothing.
        return new DoNothingAction();
    }
}
