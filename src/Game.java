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

        boolean isGameOver = false;
        int selectedLocation = 0;

        while (!isGameOver) {
            boolean isLocationChoose = false;
            int count = 1;


            repeatHyphen(110);
            playerInfo();
            repeatHyphen(110);

            System.out.println("*** Here it's your map! ***");
            for (Location location : locations) {
                System.out.println(count + ") " + location.getLocationName());
                count++;
            }

            System.out.println((locations.length + 1) + "- Exit");

            repeatHyphen(30);

            while (!isLocationChoose) {
                System.out.print(" ** Where do you want to go: ");
                selectedLocation = input.nextInt();

                if (selectedLocation == (locations.length + 1)) {
                    isLocationChoose = true;
                } else if (selectedLocation > 0 && selectedLocation <= locations.length) {
                    isLocationChoose = true;
                } else {
                    System.out.println("* You can choose between 1 and " + locations.length);
                }
            }

            if (selectedLocation == (locations.length + 1)) {
                break;
            } else if (!locations[--selectedLocation].onLocation()) {
                playerInfo();
                System.out.println("*** GAME OVER! ***");
                isGameOver = true;
            }
        }

        if (selectedLocation == (locations.length + 1)){
            System.out.println("* Game terminated.");
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

    private void playerInfo() {
        System.out.print("!! Player INFO : ");
        System.out.println("Health: " + player.getHealth() +
                " | " + "Damage: " + player.getDamage() +
                " | " + "Coin: " + player.getCoin() +
                " | " + "Weapon: " + player.getInventory().getWeapon().getName() +
                " | " + "Armor: " + player.getInventory().getArmor().getName() +
                " | " + "Defence: " + player.getInventory().getArmor().getDefence());
    }

    private void repeatHyphen(int times) {
        while (times-- > 0) {
            System.out.print("-");
        }
        System.out.println();
    }
}
