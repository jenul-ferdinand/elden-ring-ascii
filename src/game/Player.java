package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.Club;
import game.items.FlaskOfCrimsonTears;
import game.items.RuneManager;
import game.utils.Resettable;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by: Adrian Kristanto
 * @author Adrian Kristanto
 * Modified by: Jenul Ferdinand
 *
 */
public class Player extends edu.monash.fit2099.engine.actors.Actor implements Resettable {
	private final int startingX;
	private final int startingY;

	private final Menu menu = new Menu();

	private RuneManager runeManager;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		// Super class attributes
		super(name, displayChar, hitPoints);

		// Starting location
		this.startingX = 38;
		this.startingY = 9;

		// Capability that the player is hostile to enemies
		this.addCapability(Status.HOSTILE_TO_ENEMY);

		// Add the Club to the weapon inventory
		this.addWeaponToInventory(new Club());

		// Add the Flask of Crimson Tears to the inventory
		this.addItemToInventory(new FlaskOfCrimsonTears());

		// RuneManager initialisation
		this.runeManager = RuneManager.getRuneManager();
		runeManager.setRunes(this, 0 );
	}

	public int getStartingX() {
		return startingX;
	}

	public int getStartingY() {
		return startingY;
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Colours as variables
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_YELLOW = "\u001B[33m";

		// Print the HP in red
		display.println(ANSI_RED + "HP: " + printHp() + ANSI_RESET);

		// Print the Runes balance in green
		display.println(ANSI_YELLOW + "Runes: " + RuneManager.getRuneManager().getRunes(this) + ANSI_RESET);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void reset() {

	}

	/**
	 * The default weapon
	 * @return IntrinsicWeapon The player's fists
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches");
	}


	public ActionList allowableActions(edu.monash.fit2099.engine.actors.Actor otherActor, String direction, GameMap map) {
		// Create a new list of actions
		ActionList actions = new ActionList();

		return actions;
	}
}
