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

    /**
     * Constructor
     * @param aliveVersion
     */
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

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
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

    /**
     * Get the amount of turns passed
     * @return int The amount of turns passed
     */
    public int getTurnsPassed() {
        return turnsPassed;
    }

    /**
     * Set the amount of turns passed
     * @param turnsPassed The value to set
     */
    public void setTurnsPassed(int turnsPassed) {
        this.turnsPassed = turnsPassed;
    }

    /**
     * Get the alive version
     * @return Skeletal
     */
    public Skeletal getAliveVersion() {
        return aliveVersion;
    }

    /**
     * Set the alive version
     * @param aliveVersion The alive verison Skeletal to set
     */
    public void setAliveVersion(Skeletal aliveVersion) {
        this.aliveVersion = aliveVersion;
    }
}
