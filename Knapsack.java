import java.util.ArrayList;

public class Knapsack {
    public double capacity;
    public ArrayList<Item> bagItems = new ArrayList<Item>();
    public int totalCost;

    public Knapsack(double capacity) {
        this.capacity = capacity;
        this.totalCost = 0;
    }

    // fügt ein Item in der Liste hinzu. Beachtet dabei aber ob es noch reinpasst
    // gibt true zurürck wenns geklappt
    public boolean addItem(Item item) {
        if (isFitting(item)) {
            item.quantity -= 1; // Anzahl der Items verkleinert
            this.bagItems.add(item); // Item wird in der Liste hinzugefügt
            this.capacity -= item.size; // Kapazität wird kleiner gemacht
            totalCost += item.cost; // die Gesamtkosten werden dazuaddiert
            return true;
        }
        return false;

    }

    // Überprüft ob es noch reinpasst von der Größe UND der Quantität her
    public boolean isFitting(Item item) {
        double diff = this.capacity - item.size;

        if (diff > 0 && item.quantity > 0) {
            return true;
        }
        return false;
    }

}
