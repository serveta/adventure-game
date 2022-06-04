package gameEnemy;

public class Enemy {
    private String enemyName;
    private int health;
    private int damage;
    private int loot;

    public Enemy(String enemyName, int health, int damage, int loot) {
        this.enemyName = enemyName;
        this.health = health;
        this.damage = damage;
        this.loot = loot;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }
}
