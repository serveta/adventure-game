package gameLocation;

import player.Player;

public class River extends BattleLocation {
    public River(Player player) {
        super(player, "River");
    }

    @Override
    public void combat() {

    }

    @Override
    public boolean onLocation() {
        System.out.println("**************RIVER**************");
        return false;
    }
}
