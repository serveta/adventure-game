package gameLocation;

import gameEnemy.Zombie;
import player.Player;

public class River extends BattleLocation {
    public River(Player player) {
        super(player, "River", new Zombie(), 5,"water");
    }
}
