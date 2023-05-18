package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;


/**
 * Remembrance of the Grafted.
 * This is an item dropped by Godrick the Grafted.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class RemembranceOfTheGrafted extends Item {



    /**
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        // Superclass attributes
        super("Remembrance of the Grafted", 'O', true);
    }



    /**
     * Inform a carried Item of the passage of time.
     * @param currentLocation - The location of the actor carrying this Item.
     * @param actor - The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {}



}
