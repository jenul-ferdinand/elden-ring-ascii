package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;


/**
 * Axe of Godrick.
 * This is Godrick the Grafted's primary weapon.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class AxeOfGodrick extends WeaponItem {



    /**
     * Constructor
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "slashes", 84);

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