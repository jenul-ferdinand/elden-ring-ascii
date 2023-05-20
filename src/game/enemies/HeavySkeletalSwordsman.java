package game.enemies;

import game.enemies.type.Skeletal;
import game.items.Grossmesser;
import game.utils.Status;

public class HeavySkeletalSwordsman extends Skeletal {

    /**
     * Constructor
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153,0,0);

        // Add the Grossmesser to inventory
        this.addWeaponToInventory(new Grossmesser());
    }
}
