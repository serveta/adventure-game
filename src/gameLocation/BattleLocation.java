package gameLocation;

import player.Player;

public abstract class BattleLocation extends Location {
    public BattleLocation(Player player, String locationName) {
        super(player, locationName);
    }

    public abstract void combat();
}
