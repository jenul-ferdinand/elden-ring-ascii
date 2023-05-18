package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;


/**
 * Grafted Dragon.
 * This is Godrick the Grafted's secondary weapon.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class GraftedDragon extends WeaponItem {



    /**
     * Constructor
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "bites", 90);

        this.addCapability(Status.WEAPON);
    }



    /**
     * Inform a carried Item of the passage of time.
     * @param currentLocation - The location of the actor carrying this Item.
     * @param actor - The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {}



}