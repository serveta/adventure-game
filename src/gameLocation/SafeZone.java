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
        return true;
    }
}
