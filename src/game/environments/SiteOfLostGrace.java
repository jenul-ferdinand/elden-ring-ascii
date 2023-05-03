package game.environments;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ResetAction;
import game.utils.Status;

public class SiteOfLostGrace extends Ground {
    private final Action resetAction;

    /**
     * Constructor
     */
    public SiteOfLostGrace() {
        super('U');

        resetAction = new ResetAction(this);

    }

    /**
     * Allow the player to rest at the site and reset the game
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList Returns the list of actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(resetAction);
        }

        return actions;
    }
}
