package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DespawnAction;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * The DespawnBehaviour class represents the behaviour of an Actor to despawn with a certain probability.
 * If the random number generated is less than 10,
 * the Actor will perform the DespawnAction and will be removed from the GameMap.
 * If not, the Actor will not perform any action.
 * Created by: Ruilin
 * Modified by: Ruilin
 * @see Action
 * @see Actor
 * @see GameMap
 */

public class DespawnBehaviour implements Behaviour {
    private final Random random = new Random();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (RandomNumberGenerator.getRandomInt(100)<10) {
            return new DespawnAction(actor);
        }
        else {
            return null;
        }

    }
}
