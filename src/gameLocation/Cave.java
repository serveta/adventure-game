package gameLocation;

import player.Player;

public class Cave extends BattleLocation {
    public Cave(Player player) {
        super(player, "Cave");
    }

    @Override
    public void combat() {

    }

    @Override
    public boolean onLocation() {
        System.out.println("**************CAVE**************");
        return false;
    }
}
