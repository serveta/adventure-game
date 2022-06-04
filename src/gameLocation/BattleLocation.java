package gameLocation;

import gameEnemy.Enemy;
import player.Player;

import java.util.Random;
import java.util.Scanner;

public abstract class BattleLocation extends Location {
    Scanner input = new Scanner(System.in);
    private Enemy enemy;
    private String prize;
    private int maxEnemy;

    public BattleLocation(Player player, String locationName, Enemy enemy, int maxEnemy, String prize) {
        super(player, locationName);
        this.enemy = enemy;
        this.maxEnemy = maxEnemy;
        this.prize = prize;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getMaxEnemy() {
        return maxEnemy;
    }

    public void setMaxEnemy(int maxEnemy) {
        this.maxEnemy = maxEnemy;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    @Override
    public boolean onLocation() {
        String select;
        System.out.println("* You can obtain " + getPrize() + " here.");
        System.out.println("* WARNING: There are " + randomEnemyNumber() + " " + getEnemy().getEnemyName() + "s here.");

        System.out.print(" ** Do you want to continue (y/n): ");
        select = input.next();

        if (select.toLowerCase().trim().equals("y")) {
            // War algorithm will be start here...
        }

        return true;
    }

    private int randomEnemyNumber() {
        Random random = new Random();
        int rnd = random.nextInt(getMaxEnemy());
        rnd += 1;
        return (rnd > 1) ? rnd : 2;
    }

    public void combat() {

    }
}
