public class Item implements Comparable {
    public int id;
    public int size; // Größe des items
    public int cost; // Kosten des Items
    public int quantity; // Anzahl an vorhandenen Gegenstände

    public Item(int id, int size, int cost, int quantity) {
        this.id = id;
        this.size = size;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Item() {
        this.id = 0;
        this.size = 0;
        this.cost = 0;
        this.quantity = 0;
    }

    @Override
    public String toString() {
        return "ID : " + this.id + " SIZE: " + this.size + " COST: " + this.cost + " QUANTITY: " + this.quantity;
    }

    @Override
    public int compareTo(Object compareItem) {
        int compareQuantity = ((Item) compareItem).cost;

        // ascending order
        return this.quantity - compareQuantity;
    }

}
