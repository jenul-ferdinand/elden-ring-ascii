package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.environments.SiteOfLostGrace;
import game.utils.Status;

public class ResetAction extends Action {
    private SiteOfLostGrace site;

    public ResetAction(SiteOfLostGrace site) {
        this.site = site;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // Since we now know that this is the player
            Player player = (Player) actor;

            // Move the player to the original location
            Location location = new Location(map, player.getStartingX(), player.getStartingY());
            map.moveActor(player, location);
        }

        return actor + " rested at The First Step Lost Site of Grace";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at The First Step Lost Site of Grace";
    }
}
