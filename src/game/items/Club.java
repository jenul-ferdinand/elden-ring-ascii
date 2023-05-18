package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;


/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends WeaponItem {



    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);

        this.addCapability(Status.WEAPON);
    }



    @Override
    public void tick(Location currentLocation, Actor actor) {}



}
