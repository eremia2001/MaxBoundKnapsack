public class Item implements Comparable<Item> {
    public int id;
    public int size; // Größe des items
    public int cost; // Kosten des Items
    public int quantity; // Anzahl an vorhandenen Gegenstände
    public double profit; // Wird in der Main Klasse initiallisiert
    public boolean processed; // Braucht man für die Rekursion

    public Item(int id, int size, int cost, int quantity) {
        this.id = id;
        this.size = size;
        this.cost = cost;
        this.quantity = quantity;
        processed = false;
    }

    public Item() {
        this.id = 0;
        this.size = 0;
        this.cost = 0;
        this.quantity = 0;
        this.profit = 0;
        processed = false;
    }

    @Override
    public String toString() {
        return "ID : " + this.id + " SIZE: " + this.size + " COST: " + this.cost + " QUANTITY: " + this.quantity
                + " PROFIT :" + this.profit;
    }

    @Override
    public int compareTo(Item item) {
        if (profit == item.profit) {
            return 0;
        } else if (profit > item.profit) {
            return 1;
        } else {
            return -1;
        }
    }

}
