package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class PileOfBones extends Enemy {
    private int turnsPassed;

    public PileOfBones() {
        super("Pile of Bones", 'x', 1,0,0, null);
        this.turnsPassed = 0;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        turnsPassed += 1;
        if(turnsPassed > 3){
            Location respawnLocation = map.locationOf(this);
            map.removeActor(this);
            map.addActor(new HeavySkeletalSwordsman(), respawnLocation);
        }
        return new DoNothingAction();
    }
}
