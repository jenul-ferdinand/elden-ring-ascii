package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.LoneWolf;
import game.enemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

public class Graveyard extends Ground {

    private int spawnChance = 27;
    public Graveyard() {
        super('n');
    }

    @Override
    public void tick(Location location){
        //spawnChance = 0;
        if(location.containsAnActor()){
            return;
        } else if (RandomNumberGenerator.getRandomInt(100) < spawnChance) {
            if(location.x() < (location.map().getXRange().max() / 2)){
                location.addActor(new HeavySkeletalSwordsman());
            } else{
                location.addActor(new SkeletalBandit());
            }

        }
    }
}
