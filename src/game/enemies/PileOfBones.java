package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enemies.type.Skeletal;

public class PileOfBones extends Skeletal {
    private int turnsPassed;

    private Skeletal aliveVersion;

    public PileOfBones(Skeletal aliveVersion) {
        super("Pile of Bones", 'x', 1,0,0, null);
        this.turnsPassed = 0;

        this.aliveVersion = aliveVersion;
        this.aliveVersion.heal(10000);

        for (Item item : this.aliveVersion.getItemInventory())
            this.addItemToInventory(item);
        for (WeaponItem weapon : this.aliveVersion.getWeaponInventory())
            this.addWeaponToInventory(weapon);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        turnsPassed += 1;
        if(turnsPassed > 3){
            Location respawnLocation = map.locationOf(this);
            map.removeActor(this);
            map.addActor(aliveVersion, respawnLocation);
        }
        return new DoNothingAction();
    }
    public int getTurnsPassed() {
        return turnsPassed;
    }

    public void setTurnsPassed(int turnsPassed) {
        this.turnsPassed = turnsPassed;
    }

    public Skeletal getAliveVersion() {
        return aliveVersion;
    }

    public void setAliveVersion(Skeletal aliveVersion) {
        this.aliveVersion = aliveVersion;
    }
}
