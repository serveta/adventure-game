package gameTool;

public abstract class Armor extends Tool {
    private static int count = 0;
    int defence;

    public Armor(String name, int price, int defence) {
        super(++count, name, price);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
