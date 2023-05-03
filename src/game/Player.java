package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.SellAction;
import game.items.Club;
import game.utils.Resettable;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor implements Resettable {
	private int runes = 0;

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addWeaponToInventory(new Club());
	}

	public void addRunes(int amount) {
		this.runes += amount;
	}
	public int getRunes() { return runes; }

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Colours as variables
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_YELLOW = "\u001B[33m";

		// Print the HP in red
		display.println(ANSI_RED + "HP: " + printHp() + ANSI_RESET);

		// Print the Runes balance in green
		display.println(ANSI_YELLOW + "Runes: " + runes + ANSI_RESET);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		// Create a new list of actions
		ActionList actions = new ActionList();



		return actions;
	}

	@Override
	public void reset() {}
}
