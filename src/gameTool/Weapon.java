package gameTool;

public abstract class Weapon extends Tool {
    private static int count = 0;
    private int damage;

    public Weapon(String name, int price, int damage) {
        super(++count, name, price);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
