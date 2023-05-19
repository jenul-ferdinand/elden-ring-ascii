package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import edu.monash.fit2099.engine.displays.Display;

public class Cliff extends Ground {
    /**
     * Constructor for the Cliff class.
     */
    public Cliff() {
        super('+');
    }
    /**
     * Executes the tick action for the Cliff ground.
     * If there is an actor present on the location, it triggers a DeathAction on the actor.
     *
     * @param location The location of the Cliff ground.
     */
    @Override
    public void tick(Location location) {
        Display display = new Display();
        if(location.containsAnActor()){
            display.println(new DeathAction(location.getActor()).execute(location.getActor(), location.map()));
        }
    }
}
