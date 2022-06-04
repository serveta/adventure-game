package gameTool;

public class Armor extends Tool {
    private static int count = -1;
    int defence;

    public Armor(String name, int defence, int price) {
        super(++count, name, price);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public Armor getObjById(int id) {
        return this.getId() == id ? this : null;
    }
}
