import java.util.Scanner;

public class Game {
    private Player player;

    public Game() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void start() {
        Scanner input = new Scanner(System.in);
        String playerName;

        System.out.println("*** Welcome to the Adventure Game! ***");
        System.out.print("* Player name: ");
        playerName = input.next();
        Player player = new Player(playerName);

        player.createCharacter();

        System.out.println("So you are " + player.getCharacter() + " " + playerName);
        System.out.println("Damage: " + player.getDamage() + ", Health: " + player.getHealth() + ", Coin: " + player.getCoin());
    }
}
