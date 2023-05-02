package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GiantCrab;
import game.enemies.GiantCrayfish;
import game.enemies.GiantDog;
import game.enemies.LoneWolf;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

public class PuddleOfWater extends Ground {
    int spawnChanceCrab = 2;
    int spawnChanceCrayfish = 1;
    public PuddleOfWater() {
        super('~');
    }

    @Override
    public void tick(Location location){
        //spawnChance = 0;
        if(location.containsAnActor()){
            return;
        } else if (RandomNumberGenerator.getRandomInt(100) < spawnChanceCrab &&
                (location.x() < (location.map().getXRange().max() / 2))) {
            location.addActor(new GiantCrab());
        } else if (RandomNumberGenerator.getRandomInt(100) < spawnChanceCrayfish &&
                !(location.x() < (location.map().getXRange().max() / 2))) {
            location.addActor(new GiantCrayfish());
        }
    }
}
