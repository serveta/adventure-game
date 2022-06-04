package gameLocation;

import gameEnemy.Vampire;
import player.Player;

public class Forest extends BattleLocation {
    public Forest(Player player) {
        super(player, "Forest", new Vampire(), 4,"firewood");
    }
}
