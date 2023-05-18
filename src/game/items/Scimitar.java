package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.utils.Status;

public class Scimitar extends WeaponItem {



    /**
     * Constructor
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "bonks", 88);

        this.addCapability(Status.WEAPON);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AttackAction(target, "surrounding", this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}



}
