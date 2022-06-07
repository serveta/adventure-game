package gameLocation;

import gameEnemy.Enemy;
import gameEnemy.Snake;
import player.Player;

import java.util.Random;

public class Pit extends BattleLocation {
    public Pit(Player player) {
        super(player, "Pit", new Snake(), 5, "secret");
    }
}
