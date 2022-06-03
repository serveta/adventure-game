package gameLocation;

import gameTool.*;
import player.Player;

public class ToolStore extends SafeLocation {
    Weapon[] weapons = {new Sword(), new Pistol(), new Rifle()};
    Armor[] armors = {new LightArmor(), new MediumArmor(), new HeavyArmor()};
    String[] mainManuTitle;

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        int chooseTool = 0;
        int toolLength = 0;
        int mainMenu;

        System.out.println("**************TOOL STORE**************");
        System.out.println("* Welcome, " + player.getCharacter() + " " + player.getPlayerName());
        System.out.println("* How can I help you?");

        do {
            mainMenu = mainMenu();
            if (mainMenu == 1) {
                toolLength = weapons.length;
                for (Weapon weapon : weapons) {
                    System.out.println("< " + weapon.getId() +
                            "- " + weapon.getName() +
                            "\tDamage: " + weapon.getDamage() +
                            "\tPrice: " + weapon.getPrice() + " >");
                }
            } else if (mainMenu == 2) {
                toolLength = armors.length;
                for (Armor armor : armors) {
                    System.out.println("< " + armor.getId() +
                            "- " + armor.getName() +
                            "\tDefence: " + armor.getDefence() +
                            "\tPrice: " + armor.getPrice() + " >");
                }
            } else if (mainMenu == this.mainManuTitle.length) {
                break;
            }

            if (toolLength > 0) {
                do {
                    System.out.println("< " + (toolLength + 1) + "- Return >");
                    System.out.print(" ** Choice: ");
                    chooseTool = Tool.input.nextInt();
                } while (chooseTool <= 0 || chooseTool > (toolLength + 1));
                if (chooseTool != (toolLength + 1)) {
                    if (mainMenu == 1){
                        buy(weapons[--chooseTool]);
                    } else if (mainMenu == 2) {
                        buy(armors[--chooseTool]);
                    }
                    chooseTool = (toolLength + 1);
                }
            }
        } while (chooseTool == (toolLength + 1));
        return true;
    }

    private int mainMenu() {
        this.mainManuTitle = new String[]{"WEAPONS", "ARMORS", "Exit"};
        int count = 0;
        int chooseMainMenu = 0;
        System.out.println("----------------TOOLS----------------");

        for (String menu : this.mainManuTitle) {
            System.out.println(++count + "- " + menu);
        }

        do {
            System.out.print(" ** Choice: ");
            chooseMainMenu = Tool.input.nextInt();
        } while (chooseMainMenu <= 0 || chooseMainMenu > this.mainManuTitle.length);

        return chooseMainMenu;
    }

    private void buy(Tool tool) {
        int confirm;
        System.out.println("----------------ORDER----------------");
        System.out.print("You will buy : < " + tool.getName() + " >");
        System.out.println("< Price: " + tool.getPrice() + " >");

        do {
            System.out.println("1- Buy");
            System.out.println("2- Cancel");
            System.out.print(" ** Confirm: ");
            confirm = Tool.input.nextInt();
        } while (confirm < 0 || confirm > 2);

        if (confirm == 1) {
            if (tool.getPrice() > player.getCoin()) {
                System.out.println("* You've just " + player.getCoin() + " coin!");
                System.out.println("* Your coin is not enough to buy the " + tool.getName());
            } else {
                int balance = player.getCoin() - tool.getPrice();
                player.setCoin(balance);
                System.out.println("You got the " + tool.getName());
                System.out.println("You've " + player.getCoin() + " coin left.");
                /*
                Here tool will add to the inventory...
                 */
            }
        } else {
            System.out.println("* Order canceled.");
        }
    }
}
