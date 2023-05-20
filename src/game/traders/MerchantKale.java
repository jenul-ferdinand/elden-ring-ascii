package game.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.*;


/**
 * The first trader the player encounters.
 * This trader buys and sells weapons to trade capable Actors.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class MerchantKale extends Trader {



    /**
     * Constructor.
     */
    public MerchantKale() {
        // Superclass attributes
        super("Merchant Kale", 'K', 999);

        // Weapons
        initWeapon(new Club(),          600,    100,    DealType.BOTH);
        initWeapon(new Scimitar(),      600,    100,    DealType.BOTH);
        initWeapon(new Grossmesser(),   0,      100,    DealType.BUYING);
        initWeapon(new AxeOfGodrick(), 0, 1000, DealType.BUYING);
        initWeapon(new GraftedDragon(), 0, 1000, DealType.BUYING);

        // Items
        initItem(new GoldenSeed(), 0, 500, DealType.BUYING);
        initItem(new RemembranceOfTheGrafted(), 0, 20000, DealType.BUYING);
    }



    /**
     * The merchant does not move
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action DoNothingAction()
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Do nothing
        return new DoNothingAction();

    }
}
