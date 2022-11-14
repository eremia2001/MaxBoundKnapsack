import java.util.ArrayList;

public class Knapsack {
    public int capacity;
    public ArrayList<Item> bagItems = new ArrayList<Item>();
    public int totalCost;

    public Knapsack(int capacity) {
        this.capacity = capacity;
        this.totalCost = 0;
    }
}
