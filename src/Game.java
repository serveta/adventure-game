import gameLocation.*;
import player.Player;

import java.util.Scanner;

public class Game {
    private Player player;
    private Location location;
    Scanner input = new Scanner(System.in);

    public Game() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void chooseLocation() {
        Location[] locations = {
                new SafeZone(getPlayer()),
                new ToolStore(getPlayer()),
                new Forest(getPlayer()),
                new Cave(getPlayer()),
                new River(getPlayer())
        };
        boolean isLocationChoose = false;
        int count = 1;

        System.out.println("*** Here it's your map! ***");
        for (Location location : locations) {
            System.out.println(count + ") " + location.getLocationName());
            count++;
        }

        while (!isLocationChoose) {
            System.out.print(" ** Where do you want to go: ");
            int location = input.nextInt();

            if (location > 0 && location < locations.length) {
                isLocationChoose = true;
            } else {
                System.out.println("You can choose between 1 and " + locations.length);
            }

            goToLocation(locations[--location]);
        }
    }

    public void goToLocation(Location location) {
        if (!location.onLocation()) {
            System.out.println("GAME OVER!");
        } else {
            chooseLocation();
        }
    }

    public void start() {
        String playerName;

        System.out.println("*** Welcome to the Adventure Game! ***");
        System.out.print("* Player name: ");
        playerName = input.next();
        this.player = new Player(playerName);

        this.player.createCharacter();

        chooseLocation();

    }
}
