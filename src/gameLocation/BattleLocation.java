package gameLocation;

import gameEnemy.Enemy;
import gameTool.Armor;
import gameTool.Weapon;
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
    private int enemyHealth;
    private int originalDamage;

    public BattleLocation(Player player, String locationName, Enemy enemy, int maxEnemy, String prize) {
        super(player, locationName);
        this.enemy = enemy;
        this.maxEnemy = maxEnemy;
        this.prize = prize;
        this.enemyHealth = getEnemy().getHealth();
        this.originalDamage = getEnemy().getDamage();
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
                        if (getPrize().equals("water")) {
                            getPlayer().getInventory().setWater(true);
                        } else if (getPrize().equals("food")) {
                            getPlayer().getInventory().setFood(true);
                        } else if (getPrize().equals("firewood")) {
                            getPlayer().getInventory().setFirewood(true);
                        } else if (getPrize().equals("secret")) {
                            secretPrize();
                            getEnemy().setHealth(this.enemyHealth);
                        }
                        if (!getPrize().equals("secret")) {
                            System.out.println("* You win and loot: " + (getEnemy().getLoot() * getEnemyNumber()) + " coins + " + getPrize());
                        }
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

                if (!isFlee) {
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
        int enemyHit;
        System.out.println("* " + getEnemy().getEnemyName() + " hit.");

        if (getEnemy().getEnemyName().equals("Snake")) {
            getEnemy().setDamage(this.originalDamage);
            enemyHit = getEnemy().getDamage() - random.nextInt(4);
            getEnemy().setDamage(enemyHit);
        }

        enemyHit = getEnemy().getDamage() - getPlayer().getInventory().getArmor().getDefence();
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

    private void secretPrize() {
        int randomPrize = random.nextInt(100);
        if (randomPrize < 15) {
            System.out.println("* Your prize: " + randomPrize("weapon"));
        } else if (randomPrize < 30) {
            System.out.println("* Your prize: " + randomPrize("armor"));
        } else if (randomPrize < 55) {
            System.out.println("* Your prize: " + randomPrize("coin"));
        } else {
            System.out.println("* There doesn't seem to be anything to loot around.");
        }
    }

    private String randomPrize(String prizeName) {
        int rndPrize = random.nextInt(100);
        if (prizeName.equals("weapon")) {
            if (rndPrize < 50) {
                getPlayer().getInventory().setWeapon(new Weapon("Sword", 2, 25));
                return "sword";
            } else if (rndPrize < 80) {
                getPlayer().getInventory().setWeapon(new Weapon("Pistol", 3, 35));
                return "pistol";
            } else {
                getPlayer().getInventory().setWeapon(new Weapon("Rifle", 7, 45));
                return "rifle";
            }
        } else if (prizeName.equals("armor")) {
            if (rndPrize < 50) {
                getPlayer().getInventory().setArmor(new Armor("Light", 1, 15));
                return "light";
            } else if (rndPrize < 80) {
                getPlayer().getInventory().setArmor(new Armor("Medium", 3, 25));
                return "medium";
            } else {
                getPlayer().getInventory().setArmor(new Armor("Heavy", 5, 40));
                return "heavy";
            }
        } else if (prizeName.equals("coin")) {
            if (rndPrize < 50) {
                getPlayer().setCoin(getPlayer().getCoin() + 1);
                return "1 coin";
            } else if (rndPrize < 80) {
                getPlayer().setCoin(getPlayer().getCoin() + 5);
                return "5 coins";
            } else {
                getPlayer().setCoin(getPlayer().getCoin() + 10);
                return "10 coins";
            }
        }
        return "";
    }

    private void repeatHyphen(int times) {
        while (times-- > 0) {
            System.out.print("-");
        }
        System.out.println();
    }
}
