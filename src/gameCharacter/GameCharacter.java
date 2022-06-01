package gameCharacter;

public abstract class GameCharacter {
    private String characterName;
    private final int id;
    private static int count = 0;
    private int damage;
    private int health;
    private int coin;

    public GameCharacter(String characterName, int damage, int health, int coin) {
        this.characterName = characterName;
        this.damage = damage;
        this.health = health;
        this.coin = coin;
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
}
