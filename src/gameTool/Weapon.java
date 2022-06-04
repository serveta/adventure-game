package gameTool;

public class Weapon extends Tool {
    private static int count = -1;
    private int damage;

    public Weapon(String name, int damage, int price) {
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
