package gameLocation;

import player.Player;

public class SafeZone extends SafeLocation {
    public SafeZone(Player player) {
        super(player, "Safe Zone");
    }

    @Override
    public boolean onLocation() {
        System.out.println("**************SAFE ZONE**************");
        System.out.println("* Welcome your home! When you rest your health will be better.");
        getPlayer().setHealth(getPlayer().getOriginalHealth());
        System.out.println("* You're looking better now.");

        if(isWin()){
            return false;
        }

        return true;
    }

    public boolean isWin() {
        if (getPlayer().getInventory().isFirewood() && getPlayer().getInventory().isFood() && getPlayer().getInventory().isWater()) {
            return true;
        }
        return false;
    }
}
