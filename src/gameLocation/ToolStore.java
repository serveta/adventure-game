package gameLocation;

import gameTool.*;
import player.Player;

public class ToolStore extends SafeLocation {
    String[] mainManuTitle = {
            "WEAPONS",
            "ARMORS",
            "Return"
    };

    Weapon[] weapons = {
            new Weapon("Sword", 2, 25),
            new Weapon("Pistol", 3, 35),
            new Weapon("Rifle", 7, 45)
    };
    Armor[] armors = {
            new Armor("Light", 1, 15),
            new Armor("Medium", 3, 25),
            new Armor("Heavy", 5, 40),
    };

    int selectedTool;

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        int selectedMenu;

        System.out.println("**************TOOL STORE**************");
        System.out.println("* Welcome, " + player.getCharacter() + " " + player.getPlayerName());
        System.out.println("* How can I help you?");

        do {
            mainMenu();
            selectedMenu = selectedMenu();

            switch (selectedMenu) {
                case 1:
                    printTool(this.weapons);
                    System.out.print(" ** Select: ");
                    setSelectedTool(Tool.input.nextInt());
                    if (getSelectedTool() != weapons.length) {
                        showOrder(this.weapons[getSelectedTool()]);
                        buy(orderConfirm(), this.weapons[getSelectedTool()]);
                    }
                    break;
                case 2:
                    printTool(this.armors);
                    System.out.print(" ** Select: ");
                    setSelectedTool(Tool.input.nextInt());
                    if (getSelectedTool() != armors.length) {
                        showOrder(this.armors[getSelectedTool()]);
                        buy(orderConfirm(), this.armors[getSelectedTool()]);
                    }
                    break;
                default:
                    setSelectedTool(-1);
                    break;
            }


        } while (getSelectedTool() == weapons.length || getSelectedTool() == armors.length);

        return true;
    }

    private void mainMenu() {
        int count = 0;
        System.out.println("----------------TOOLS----------------");

        for (String menu : this.mainManuTitle) {
            System.out.println(++count + "- " + menu);
        }

    }

    private int selectedMenu() {
        int selectedMenu = 0;
        do {
            System.out.print(" ** Select: ");
            selectedMenu = Tool.input.nextInt();
        } while (selectedMenu <= 0 || selectedMenu > this.mainManuTitle.length);

        return selectedMenu;
    }

    private void printTool(Weapon[] weapons) {
        for (Weapon weapon : weapons) {
            System.out.println("< " + weapon.getId() +
                    "- " + weapon.getName() +
                    "\tDamage: " + weapon.getDamage() +
                    "\tPrice: " + weapon.getPrice() + " >");
        }
        returnButton(weapons);
    }

    private void printTool(Armor[] armors) {
        for (Armor armor : armors) {
            System.out.println("< " + armor.getId() +
                    "- " + armor.getName() +
                    "\tDefence: " + armor.getDefence() +
                    "\tPrice: " + armor.getPrice() + " >");
        }
        returnButton(armors);
    }

    private void returnButton(Weapon[] weapons) {
        System.out.println("< " + (weapons.length + 1) + "- Return >");
    }

    private void returnButton(Armor[] armors) {
        System.out.println("< " + (armors.length + 1) + "- Return >");
    }

    private int getSelectedTool() {
        return selectedTool;
    }

    private void setSelectedTool(int selectedTool) {
        this.selectedTool = selectedTool - 1;
    }

    private void showOrder(Weapon weapon) {
        System.out.println("----------------ORDER----------------");
        System.out.print("You will buy : < " + weapon.getName() + " >");
        System.out.println("< Price: " + weapon.getPrice() + " >");

    }

    private void showOrder(Armor armor) {
        System.out.println("----------------ORDER----------------");
        System.out.print("You will buy : < " + armor.getName() + " >");
        System.out.println("< Price: " + armor.getPrice() + " >");
    }

    private boolean orderConfirm() {
        int confirm;
        do {
            System.out.println("1- Buy");
            System.out.println("2- Cancel");
            System.out.print(" ** Confirm: ");
            confirm = Tool.input.nextInt();
        } while (confirm < 0 || confirm > 2);

        return confirm == 1;
    }

    private void buy(boolean confirm, Weapon weapon) {
        if (confirm) {
            if (!player.getInventory().getWeapon().getName().equals(weapon.getName())) {
                if (player.getCoin() >= weapon.getPrice()) {
                    int balance = player.getCoin() - weapon.getPrice();
                    player.setCoin(balance);
                    player.getInventory().setWeapon(weapon);
                    System.out.println("* " + weapon.getName() + " bought.");
                }
                else {
                    System.out.println("* Are you kidding me? You don't have enough coins!");
                }
            } else {
                System.out.println("* You have one " + weapon.getName() + " already!");
            }
        } else {
            System.out.println("* Order canceled.");
        }
    }

    private void buy(boolean confirm, Armor armor) {
        if (confirm) {
            if (!player.getInventory().getArmor().getName().equals(armor.getName())) {
                if (player.getCoin() >= armor.getPrice()) {
                    int balance = player.getCoin() - armor.getPrice();
                    player.setCoin(balance);
                    player.getInventory().setArmor(armor);
                    System.out.println("* " + armor.getName() + " bought.");
                }
                else {
                    System.out.println("* Are you kidding me? You don't have enough coins!");
                }
            } else {
                System.out.println("* You have one " + armor.getName() + " already!");
            }
        } else {
            System.out.println("* Order canceled.");
        }
    }

}
