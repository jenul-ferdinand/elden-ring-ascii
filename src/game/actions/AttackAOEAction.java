package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

public class AttackAOEAction extends AttackAction {
    /**
     * Constructor for AttackAction with target, direction and weapon.
     *
     * @param target    the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     * @param weapon    the weapon used for the attack
     */
    public AttackAOEAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target    the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAOEAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
