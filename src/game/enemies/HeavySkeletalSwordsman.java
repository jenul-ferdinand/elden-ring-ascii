package game.enemies;

import game.items.Grossmesser;

public class HeavySkeletalSwordsman extends Enemy {

    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153,0,0, null);
        this.addWeaponToInventory(new Grossmesser());
    }
}
