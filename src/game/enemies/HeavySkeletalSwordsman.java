package game.enemies;

import game.enemies.type.Skeletal;
import game.items.Grossmesser;

public class HeavySkeletalSwordsman extends Skeletal {

    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153,0,0, null);
        this.addWeaponToInventory(new Grossmesser());
    }
}