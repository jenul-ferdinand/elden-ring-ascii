package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAOEAction;
import game.utils.Abilities;
import game.utils.RandomNumberGenerator;

import java.util.Random;

public class AttackAOEBehaviour implements Behaviour {

    private final Random random = new Random();

    /**
     * A factory for creating actions. Chaining these together can result in an actor performing more complex tasks.
     * <p>
     * A Behaviour represents a kind of objective that an Actor can have.  For example
     * it might want to seek out a particular kind of object, or follow another Actor,
     * or run away and hide.  Each implementation of Behaviour returns an Action that the
     * Actor could take to achieve its objective, or null if no useful options are available.
     * method that determines which Behaviour to perform.  This allows the Behaviour's logic
     * to be reused in other Actors via delegation instead of inheritance.
     * <p>
     * An Actor's {@code playTurn()} method can use Behaviours to help decide which Action to
     * perform next.  It can also simply create Actions itself, and for simpler Actors this is
     * likely to be sufficient.  However, using Behaviours allows
     * us to modularize the code that decides what to do, and that means that it can be
     * reused if (e.g.) more than one kind of Actor needs to be able to seek, follow, or hide.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     * @author Riordan D. Alfredo
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
//do surrounding check in action
        if (actor.hasCapability(Abilities.AOE) && RandomNumberGenerator.getRandomInt(100) < 50) {
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location toAttack = exit.getDestination();
                if (toAttack.containsAnActor()) {
                    if (toAttack.getActor().getClass().getSuperclass() != actor.getClass().getSuperclass()) {
                        return new AttackAOEAction(toAttack.getActor());
                    }
                }
            }
        }
        return null;
    }
}
