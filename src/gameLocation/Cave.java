package gameLocation;

import gameEnemy.Bear;
import player.Player;

public class Cave extends BattleLocation {
    public Cave(Player player) {
        super(player, "Cave", new Bear(),3, "food");
    }
}
