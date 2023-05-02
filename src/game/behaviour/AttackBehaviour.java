package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.utils.RandomNumberGenerator;

import java.util.Random;
import java.util.ArrayList;


public class AttackBehaviour implements Behaviour {
    private final Random random = new Random();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location toAttack = exit.getDestination();
            if (toAttack.containsAnActor()) {
                if(actor.getWeaponInventory().isEmpty()){
                    actions.add(new AttackAction(toAttack.getActor(),exit.getName()));
                } else{
                    if(RandomNumberGenerator.getRandomInt(100)<50){
                        if(actor.getWeaponInventory().get(0).getSkill(actor) != null){
                            actions.add(actor.getWeaponInventory().get(0).getSkill(actor));
                        }
                        if(actor.getWeaponInventory().get(0).getSkill(actor, exit.getName()) != null){
                            actions.add(actor.getWeaponInventory().get(0).getSkill(actor, exit.getName()));
                        }
                    } else {
                        actions.add(new AttackAction(toAttack.getActor(), exit.getName(), actor.getWeaponInventory().get(0)));
                    }

                }

            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }

    }
}
