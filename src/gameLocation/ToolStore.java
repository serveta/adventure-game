package gameLocation;

import player.Player;

public class ToolStore extends SafeLocation {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("**************TOOL STORE**************");
        System.out.println("* Welcome, " + player.getCharacter() + " " + player.getPlayerName());
        System.out.println("* How can I help you?");
        System.out.println("----------------TOOLS----------------");

        return true;
    }
}
