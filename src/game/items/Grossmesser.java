package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAOEAction;

public class Grossmesser extends WeaponItem {
    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "bonks", 85);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AttackAOEAction(target, this);
    }
}
