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

		// Store the map design in variable map
		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"...~.........n........#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"...&.........B........#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"........<.............######...######......................................",
				"...........................................................................",
				"...................D.......................................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
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
				"_____________#.............++.............................................."

//				"......................#.............#..........................+++.........",
//				"......................#.............#.......................+++++..........",
//				"...~..........n.......#..___....____#.........................+++++........",
//				"......................#...........__#............................++........",
//				"......................#_____........#.............................+++......",
//				"...&..................#............_#..............................+++.....",
//				"......................######...######......................................",
//				"...........................................................................",
//				"...........................=...............................................",
//				"........++++......................###___###................................",
//				"........+++++++...................________#................................",
//				"..........+++.....................#________................................",
//				"............+++...................#_______#................................",
//				".............+....................###___###................................",
//				"............++......................#___#..................................",
//				"..............+...................=........................................",
//				"..............++.................................................=.........",
//				"..............................................++...........................",
//				"..................++++......................+++...............######..##...",
//				"#####___######....++...........................+++............#....____....",
//				"_____________#.....++++..........................+..............__.....#...",
//				"_____________#.....+....++........................++.........._.....__.#...",
//				"_____________#.........+..+.....................+++...........###..__###...",
//				"_____________#.............++.............................................."

//				"..nnnn................................................~~~~~~~~~~~~~~~~~~~~~",
//				"......................#####....######..................~~~~~~~~~~~~~~~~~~~~",
//				"..nnnn................#..___....____#...................~~~~~~~~~~~~~~~~~~~",
//				"..................................__#....................~~~~~~~~~~~~~~~~~~",
//				"......................._____........#.....................~~~~~~~~~~~~~~~~~",
//				"......................#............_#......................~~~~~~~~~~~~~~~~",
//				"......................#...........###......................................",
//				"...........................................................................",
//				"...........................................................................",
//				"~~~~~~~~~~~......&&&&&............###___###................................",
//				"~~~~~~~~~~~~......................________#....nnnn........................",
//				"~~~~~~~~~~~~~.....................#________................................",
//				"~~~~~~~~~~~~......................#_______#....nnnn........................",
//				"~~~~~~~~~~~.......................###___###................................",
//				"~~~~~~~~~~..........................#___#..................................",
//				"...........................................................................",
//				"...........................................................................",
//				"...........................................................................",
//				"..####__##...........................................&&&......######..##...",
//				"..#.....__...........................................&&&......#....____....",
//				"..#___..............&&&..............................&&&........__.....#...",
//				"..####__###.........&&&......................................._.....__.#...",
//				"....................&&&.......................................###..__###...",
//				"..........................................................................."
				);


		// Store the map design in variable map
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


		// Store the map design in variable map
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

		// Store the map design in variable map
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


		// Display the ELDEN RING title card
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		GoldenFogDoor goldenFogDoor1 = new GoldenFogDoor();
		goldenFogDoor1.setLocation(roundtableHoldMap.at(5, 5));

		GoldenFogDoor goldenFogDoor2 = new GoldenFogDoor();
		goldenFogDoor2.setLocation(stormveilCastleMap.at(5, 5));
		// Create Merchant Kale
		limgraveMap.at(37, 10).setGround(goldenFogDoor1);
		limgraveMap.at(39, 10).setGround(goldenFogDoor2);


		// Create Merchant Kale
		limgraveMap.at(40, 12).addActor(new MerchantKale());

		// Create a Rune for testing
		limgraveMap.at(38, 15).addItem(new Rune(100));
		limgraveMap.at(38, 16).addItem(new Rune(100));

		// Create a Player
		Player player = new Player("Tarnished", '@', 300);
		// Add the Player to the World
		world.addPlayer(player, limgraveMap.at(player.getStartingX(), player.getStartingY()));


		// Start the World game loop
		world.run();
	}
}
