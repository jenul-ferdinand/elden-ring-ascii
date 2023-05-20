package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Abilities;

public class HeavyCrossbow extends WeaponItem {
    /**
     * Constructor.
     *
     */
    public HeavyCrossbow() {
        super("Heavy Crossbow", '}', 64, "bonks", 57);
        this.addCapability(Abilities.LONGRANGE);
    }
    //overide tick to addaction then it will give to the player
}
