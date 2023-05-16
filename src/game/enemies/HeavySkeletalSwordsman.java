package game.enemies;

import game.enemies.type.Skeletal;
import game.items.Grossmesser;

public class HeavySkeletalSwordsman extends Skeletal {

    /**
     * Constructor
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153,0,0, null);

        // Add Grossmesser to inventory
        this.addWeaponToInventory(new Grossmesser());
    }
}
