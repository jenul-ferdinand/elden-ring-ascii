package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

public class Grossmesser extends WeaponItem {



    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "bonks", 85);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AttackAction(target, "surrounding", this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}



}
