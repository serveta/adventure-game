package gameLocation;

import gameEnemy.Enemy;
import player.Player;

import java.util.Random;
import java.util.Scanner;

public abstract class BattleLocation extends Location {
    Scanner input = new Scanner(System.in);
    Random random = new Random();
    private Enemy enemy;
    private String prize;
    private int maxEnemy;
    private int enemyNumber;
    private boolean isFlee = false;

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

    public int getEnemyNumber() {
        return enemyNumber;
    }

    public void setEnemyNumber(int enemyNumber) {
        this.enemyNumber = enemyNumber;
    }

    @Override
    public boolean onLocation() {
        if (getEnemy().getHealth() > 0) {
            String select;
            setEnemyNumber(randomEnemyNumber());

            System.out.println("* You can obtain " + getPrize() + " here.");
            System.out.println("* WARNING: There are " + getEnemyNumber() + " " + getEnemy().getEnemyName() + "s here.");

            System.out.print(" ** Do you want to continue (y/n): ");
            select = input.next();

            if (select.toLowerCase().trim().equals("y")) {
                if (combat(getEnemyNumber())) {
                    if (!this.isFlee) {
                        getPlayer().setCoin(getPlayer().getCoin() + (getEnemy().getLoot() * getEnemyNumber()));
                        // Here will add the prize to the player inventory...
                        System.out.println("* You win and loot: " + (getEnemy().getLoot() * getEnemyNumber()) + " coins + " + getPrize());
                    }
                } else {
                    return false;
                }
            }
        } else {
            System.out.println("* You cleaned it up here.");
        }
        return true;
    }

    private int randomEnemyNumber() {
        int rnd = random.nextInt(getMaxEnemy());
        rnd += 1;
        return (rnd > 1) ? rnd : 2;
    }

    public boolean combat(int enemyNumber) {
        int enemyHealth = getEnemy().getHealth();
        boolean starter = whoWillStart();
        this.isFlee = false;

        for (int i = 1; i <= enemyNumber; i++) {
            getEnemy().setHealth(enemyHealth);

            getCombatInfo(i);

            while (getPlayer().getHealth() > 0 && getEnemy().getHealth() > 0 && !isFlee) {

                if (starter) {
                    if (isAttackOrFlee()) {
                        playerHitTheEnemy();
                        getCombatInfo(i);
                    } else {
                        isFlee = true;
                    }
                } else {
                    if (getEnemy().getHealth() > 0) {
                        enemyHitThePlayer();
                        getCombatInfo(i);
                    }
                }

                if (starter) {
                    if (getEnemy().getHealth() > 0) {
                        enemyHitThePlayer();
                        getCombatInfo(i);
                    }
                } else {
                    if (getPlayer().getHealth() > 0) {
                        if (isAttackOrFlee()) {
                            playerHitTheEnemy();
                            getCombatInfo(i);
                        } else {
                            isFlee = true;
                        }
                    }
                }
            }

            if (getPlayer().getHealth() <= 0) {
                return false;
            }

            if (isFlee && getPlayer().getHealth() > 0) {
                repeatHyphen(15);
                System.out.println("* You ran away!");
                repeatHyphen(15);
                return true;
            }
        }
        return true;
    }

    private void getCombatInfo(int enemyNum) {
        System.out.println("- Your health: " + player.getHealth());
        System.out.println("- " + enemyNum + ". " + enemy.getEnemyName() + " health: " + enemy.getHealth());
        if (getPlayer().getHealth() <= 0) {
            System.out.println("* You died.");
        }
        if (getEnemy().getHealth() <= 0) {
            System.out.println("* " + enemyNum + ". " + enemy.getEnemyName() + " died.");
        }
        repeatHyphen(20);
    }

    private boolean whoWillStart() {
        return (random.nextInt(10) < 5);
    }

    private void playerHitTheEnemy() {
        System.out.println("* You hit.");
        getEnemy().setHealth(getEnemy().getHealth() - getPlayer().getDamage());
    }

    private void enemyHitThePlayer() {
        System.out.println("* " + getEnemy().getEnemyName() + " hit.");
        int enemyHit = getEnemy().getDamage() - getPlayer().getInventory().getArmor().getDefence();
        if (enemyHit < 0) {
            enemyHit = 0;
        }
        getPlayer().setHealth(getPlayer().getHealth() - enemyHit);
    }

    private boolean isAttackOrFlee() {
        System.out.print("<A>ttack OR <F>lee: ");
        if (input.next().toLowerCase().trim().equals("a")) {
            return true;
        }
        return false;
    }

    private void repeatHyphen(int times) {
        while (times-- > 0) {
            System.out.print("-");
        }
        System.out.println();
    }
}
