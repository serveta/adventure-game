package gameLocation;

import player.Player;

public class Forest extends BattleLocation {
    public Forest(Player player) {
        super(player, "Forest");
    }

    @Override
    public void combat() {

    }

    @Override
    public boolean onLocation() {
        System.out.println("**************FOREST**************");
        return false;
    }
}
