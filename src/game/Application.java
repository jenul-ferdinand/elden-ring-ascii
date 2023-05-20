package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.defaultGrounds.Dirt;
import game.defaultGrounds.Floor;
import game.defaultGrounds.Wall;
import game.enemies.GiantCrab;
import game.environments.Graveyard;
import game.environments.GustOfWind;
import game.environments.PuddleOfWater;
import game.items.*;
import game.traders.FingerReaderEnia;
import game.environments.*;
import game.items.Rune;
import game.traders.MerchantKale;
import game.utils.FancyMessage;



/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {
		// Create a new World (It contains the main game loop)
		World world = new World(new Display());

		// Create the ground factory
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(),
				new GustOfWind(), new PuddleOfWater(), new Barrack(), new Cage(), new Cliff(), new GoldenFogDoor());

		// Limgrave map
		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"...~.........n........#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"...&.........B........#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"........<.............######...######......................................",
				"...........................................................................",
				"...........................................................................",
				"........++++......................###+__###...............&&...............",
				"........+++++++...................________#...............&&...............",
				"..........+++.....................#________................................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+............................................................",
				"..............++...........................................................",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");

		// Stormveil Castle map
		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		// Roundtable Hold map
		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");

		// Boss Room map
		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");

		// Create an instance of the game map
		GameMap limgraveMap = new GameMap(groundFactory, limgrave);
		GameMap stormveilCastleMap = new GameMap(groundFactory, stormveilCastle);
		GameMap roundtableHoldMap = new GameMap(groundFactory, roundtableHold);
		GameMap bossRoomMap = new GameMap(groundFactory, bossRoom);

		// Add the new map to the world
		world.addGameMap(limgraveMap);
		world.addGameMap(stormveilCastleMap);
		world.addGameMap(roundtableHoldMap);
		world.addGameMap(bossRoomMap);




		// ==================================== GRAPHICS ======================================

		// Display the ELDEN RING title card
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}



		// ============================== DOOR TARGET LOCATIONS ===============================

		// Limgrave -> Roundtable Hold
		GoldenFogDoor goldenFogDoor1 = new GoldenFogDoor();
		goldenFogDoor1.setLocation(roundtableHoldMap.at(5, 5));

		// Limgrave -> Stormveil Castle
		GoldenFogDoor goldenFogDoor2 = new GoldenFogDoor();
		goldenFogDoor2.setLocation(stormveilCastleMap.at(5, 5));

		// Stormveil Castle -> Limgrave
		GoldenFogDoor goldenFogDoor3 = new GoldenFogDoor();
		goldenFogDoor3.setLocation(limgraveMap.at(39, 10));

		// Stormveil Castle -> Boss room
		GoldenFogDoor goldenFogDoor4 = new GoldenFogDoor();
		goldenFogDoor4.setLocation(bossRoomMap.at(5,5));

		// Roundtable Hold -> Limgrave
		GoldenFogDoor goldenFogDoor5 = new GoldenFogDoor();
		goldenFogDoor5.setLocation(limgraveMap.at(37, 10));

		// ========================= CREATE DOOR AT ORIGINAL LOCATION =========================

		// Create Limgrave doors
		limgraveMap.at(37, 10).setGround(goldenFogDoor1);
		limgraveMap.at(39, 10).setGround(goldenFogDoor2);
		// Create Stormveil Castle doors
		stormveilCastleMap.at(5, 5).setGround(goldenFogDoor3);
		stormveilCastleMap.at(5, 7).setGround(goldenFogDoor4);
		// Create Roundtable Hold doors
		roundtableHoldMap.at(5, 5).setGround(goldenFogDoor5);



		// ================================== CREATE ACTORS ===================================

		// Merchant Kale
		limgraveMap.at(40, 12).addActor(new MerchantKale());

		// Finger Reader Enia
		roundtableHoldMap.at(9, 4).addActor(new FingerReaderEnia());

		// Player
		Player player = new Player("Tarnished", '@', 300);
		world.addPlayer(player, limgraveMap.at(38, 9));



		// ================================== CREATE ITEMS ====================================

		// Remembrance of the Grafted
		roundtableHoldMap.at(7, 2).addItem(new RemembranceOfTheGrafted());
		roundtableHoldMap.at(8, 2).addItem(new RemembranceOfTheGrafted());

		// Rune
		limgraveMap.at(38, 15).addItem(new Rune(100));
		limgraveMap.at(38, 16).addItem(new Rune(100));

		// Golden Rune
		limgraveMap.at(38,17).addItem(new GoldenRune());

		// Golden Seed
		limgraveMap.at(38, 18).addItem(new GoldenSeed());

		// Weapons
		limgraveMap.at(36, 11).addItem(new Grossmesser());
		limgraveMap.at(36, 12).addItem(new Scimitar());




		// ================================== START THE GAME ==================================

		// Start the World game loop
		world.run();
	}
}
