package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

/**
 * A class representing a ground type that acts as a Door that allow player to travel between maps
 * Only Actors with the HOSTILE_TO_ENEMY capability(The player) can enter and move through the door.
 */
public class GoldenFogDoor extends Ground {
    private Location location;
    private Exit exit;
    private static final String DOOR_NAME = "GoldenFogDoor";
    /**
     * Constructor for the GoldenFogDoor class.
     */
    public GoldenFogDoor() {
        super('D');
        this.location = null;
        this.exit = null;
    }
    /**
     * Determines the allowable actions for an actor at the GoldenFogDoor exit.
     *
     * @param actor The actor in range.
     * @param location The location of the actor.
     * @param direction The direction actor is facing.
     * @return A list of allowable actions for the actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        if (exit != null && exit.getDestination() != null){
            ActionList actions = new ActionList();
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {

                actions.add(new MoveActorAction(this.exit.getDestination(), this.exit.getName()));
            }

            return actions;
        }
        return null;
    }
    /**
     * Determines whether an actor can enter the GoldenFogDoor.
     *
     * @param actor The actor attempting to enter the door.
     * @return True if the actor has the HOSTILE_TO_ENEMY capability, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
    /**
     * Get the location stored in the GoldenFogDoor (the location to move the player to).
     *
     * @return The location
     */
    public Location getLocation() {
        return location;
    }
    /**
     * Set the location that GoldenFogDoor will move the actor to.
     *
     * @param location The location to set.
     */
    public void setLocation(Location location) {
        this.location = location;
        setExit(new Exit(DOOR_NAME,this.location,null));
    }

    /**
     * Get the exit that created base on the location.
     *
     * @return The exit
     */
    public Exit getExit() {
        return exit;
    }
    /**
     * Set the exit that of where golden will teleport the player to.
     *
     * @param exit The exit to set.
     */
    public void setExit(Exit exit) {
        this.exit = exit;
    }
}
