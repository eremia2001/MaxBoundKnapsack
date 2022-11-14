import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class MaxBoundKnapsack {
    public static ArrayList<Item> items = new ArrayList<Item>();
    public static Knapsack knapsack;

    public static void main(String[] args) {
        // Deklarieren der Variablen
        String data = "";
        int n = 0;
        int capacity = 0;
        int id = 0;
        int size = 0;
        int cost = 0;
        int quantity = 0;
        String trimmedItemData = "";
        String[] itemStringData = new String[4];
        int[] itemData = new int[4];

        // Datei lesen
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            while (scanner.hasNext()) {
                data = scanner.nextLine();
                switch (data.charAt(0)) // Das erste Zeichen in Jeder Zeile
                {
                    case 'n':
                        n = Integer.parseInt(data.substring(2));
                        break;
                    case 'k':
                        capacity = Integer.parseInt(data.substring(2));
                        break;
                    case 'i':
                        Item item = new Item();
                        trimmedItemData = data.substring(2);
                        itemStringData = trimmedItemData.split(" ");
                        // Init Item with data
                        initItem(itemStringData, itemData, item);
                        items.add(item);

                        break;

                }

            }

            // Error Handling, wenn keine Datei gefunden worden ist.
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");
            e.printStackTrace();
        }
        // Sort the List
        // Initiallisieren des Rucksacks

        sortItems();
        knapsack = new Knapsack(capacity);

        greedyAlg();

        for (Item i : knapsack.bagItems) {
            System.out.println(i);
        }

        System.out.println("Kapazitaet beträgt : " + knapsack.capacity);
        System.out.println("Gesamtkosten betragen : " + knapsack.totalCost);

        // Starte Timer
        long start = System.currentTimeMillis();

        // Finde Optimalen Wert

        long end = System.currentTimeMillis();
        // Beende Timer
        long erg = end - start;

        // Laufzeit Ausgabe
        System.out.println("Operation took " + erg + " ms.");

    }

    // Initializes Item with the right data
    public static void initItem(String[] itemStringData, int[] itemData, Item item) {
        for (int i = 0; i < itemData.length; i++) {
            itemData[i] = Integer.parseInt(itemStringData[i]);
            switch (i) {
                case 0:
                    item.id = itemData[0];
                    break;
                case 1:
                    item.size = itemData[1];
                    break;
                case 2:
                    item.cost = itemData[2];
                    break;
                case 3:
                    item.quantity = itemData[3];
                    break;
            }
        }
    }

    // Ordne die Items in der Liste auf Basis der Kosten
    public static void sortItems() {

        int prevNumber;
        int currentNumber;

        for (int i = 1; i < items.size(); i++) {
            currentNumber = items.get(i).cost;
            prevNumber = i - 1;

            while (prevNumber >= 0 && items.get(prevNumber).cost > currentNumber) {
                items.get(prevNumber + 1).cost = items.get(prevNumber).cost;
                prevNumber = prevNumber - 1;
            }
            items.get(prevNumber + 1).cost = currentNumber;
        }
    }

    public static void greedyAlg() {
        // wir fangen mit dem Item mit den größten Kosten
        // packen ihn solange rein bis entweder :
        // die kapazität des Rucksacks voll ist oder
        // es keine Items mehr gibt

        Item currentItem;
        for (int i = items.size() - 1; i > 0; i--) { // beginnt ab den grö0ten Gegenstand
            currentItem = items.get(i);
            while (knapsack.capacity > 0 && currentItem.quantity > 0) {
                // packt ein Item in Rucksack, falls es reinpasst
                if (isFitting(i)) {
                    currentItem.quantity -= 1;
                    knapsack.bagItems.add(currentItem);
                    knapsack.capacity -= currentItem.size; // Kapazität wird kleiner gemacht
                    knapsack.totalCost += currentItem.cost; // die Gesamtkosten werden dazuaddiert
                } else {
                    break;
                }

            }

        }

    }

    // Überprüft ob es noch reinpasst von der Größe UND der Quantität her
    public static boolean isFitting(int itemIndex) {
        int diff = knapsack.capacity - items.get(itemIndex).size;
        int itemQuant = items.get(itemIndex).quantity;

        if (diff > 0 && itemQuant > 0) {
            return true;
        }
        return false;
    }

}
