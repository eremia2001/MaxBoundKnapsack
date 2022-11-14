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
                        item.profit = (double) item.cost / item.size;
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
        Collections.sort(items);
        // Initiallisieren des Rucksacks
        knapsack = new Knapsack(capacity);
        // Optimalwert mit Greedy-Algorithmus - erste untere Schranke

        branchNBound();

        // System.out.println("Kapazitaet beträgt : " + knapsack.capacity);
        // System.out.println("Gesamtkosten betragen : " + knapsack.totalCost);

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

    public static int greedyAlg() {
        // wir fangen mit dem Item mit den größten Kosten
        // packen ihn solange rein bis entweder :
        // die kapazität des Rucksacks voll ist oder
        // es keine Items mehr gibt
        // nimmt eine Kopie vom Knapsackproblem
        Knapsack greedyKnapsack = new Knapsack(knapsack.capacity);
        boolean itemAdded = false;
        Item currentItem;
        for (int i = items.size() - 1; i > 0; i--) { // beginnt ab den größten Gegenstand
            currentItem = items.get(i);
            while (greedyKnapsack.capacity > 0 && currentItem.quantity > 0) {
                // packt ein Item in Rucksack, falls es reinpasst
                itemAdded = greedyKnapsack.addItem(currentItem);
                if (!itemAdded) {
                    break;
                }
            }
        }

        System.out.println(
                "Greedy Kosten: " + greedyKnapsack.totalCost + " Greedy Kapazitaet: " +
                        greedyKnapsack.capacity);

        return greedyKnapsack.totalCost;
    }

    public static int branchNBound() {
        int lowerBound = greedyAlg(); // mit Greedy untere Grenze berechnet
        ArrayList<Item> knapsackBag = knapsack.bagItems;
        Item currentItem;
        boolean itemAdded = false;

        for (int i = knapsackBag.size() - 1; i > 0; i--) {
            currentItem = items.get(i);
            while (knapsack.capacity > 0 && currentItem.quantity > 0) {
                itemAdded = knapsack.addItem(currentItem);
                if (!itemAdded && currentItem.quantity > 0) {
                    break;
                }
            }
        }
        return knapsack.totalCost;
    }

    // Jedes Teilproblem ist eine neue Instanz von Knapsack
    // Rekursive Funktion :
    // Gegeben ist ein Knapsack
    // mit diesen Belegungen soll es den optimale zustzäliche Belegungen rausfinden
    // Wenn eine Kommazahl, da ist soll ein rekursiv das Problem teilen
    // Und das Problem verzweigen in der Kommazahl für die Nächst-Größere bzw.
    // Kleinere Zahl

    // TODO: Greedy-Algorithmus benutzt die gleichen items wie Branch n Bound -> das
    // führt dazu, dass der Branch-Algorithmus verfälschte Daten benutzt
    // TODO: Gesamtkapazität als globale Variable speichern.
    // TODO: Zeile 143 vervollständingen : Da wollte ich, dass wenn eine ganze Zahl
    // nicht ganz rein passt, dass diese geborchen wird und gucken wie oft es im
    // Bruchteil reinpasst.
    // dieses Item speichere ich dann mit der NEUEN Größe rein ! .

}
