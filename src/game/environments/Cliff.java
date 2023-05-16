package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import edu.monash.fit2099.engine.displays.Display;

public class Cliff extends Ground {
    /**
     * Constructor.
     *
     */
    public Cliff() {
        super('+');
    }
    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Display display = new Display();
        if(location.containsAnActor()){
            display.println(new DeathAction(location.getActor()).execute(location.getActor(), location.map()));
        }
    }

    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}
