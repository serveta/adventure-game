import gameCharacter.Archer;
import gameCharacter.GameCharacter;
import gameCharacter.Knight;
import gameCharacter.Samurai;

import java.util.Scanner;

public class Player {
    private String playerName;
    private String character;
    private int health;
    private int coin;
    private int damage;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void createCharacter() {
        Scanner input = new Scanner(System.in);
        int character;
        boolean isCharacterChoose = false;

        System.out.println("* Choose your character; ");


        GameCharacter[] gameCharacters = {new Samurai(), new Archer(), new Knight()};

        for (GameCharacter gameCharacter : gameCharacters) {
            System.out.print(gameCharacter.getId() + ") " + gameCharacter.getCharacterName() + " \t Damage: " + gameCharacter.getDamage() + " \t Health: " + gameCharacter.getHealth() + " \t Coin: " + gameCharacter.getCoin() + "\n");
        }

        while (!isCharacterChoose) {
            System.out.print(" ** Your choose: ");
            character = input.nextInt();

            if (character>0 && character<4){
                initCharacter(gameCharacters[--character]);
                isCharacterChoose = true;
            } else {
                System.out.println("You can choose between 1 and " + gameCharacters.length);
            }
        }
    }

    public void initCharacter(GameCharacter gameCharacter) {
        setCharacter(gameCharacter.getCharacterName());
        setHealth(gameCharacter.getHealth());
        setDamage(gameCharacter.getDamage());
        setCoin(gameCharacter.getCoin());
    }
}
