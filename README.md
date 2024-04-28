Implement a hash table

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneDirectory {
    private final int MAX = 10;
    private ArrayList<ArrayList<Pair<String, Integer>>> arrSeparate;
    private Pair<String, Integer>[] arrLinear;

    public PhoneDirectory() {
        arrSeparate = new ArrayList<>(MAX);
        arrLinear = new Pair[MAX];
        for (int i = 0; i < MAX; i++) {
            arrSeparate.add(new ArrayList<>());
            arrLinear[i] = null;
        }
    }

    private int getHash(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += (int) c;
        }
        return hash % MAX;
    }

    // Separate Chaining
    public void setSeparate(String key, int value) {
        int h = getHash(key);
        boolean found = false;

        for (int index = 0; index < arrSeparate.get(h).size(); index++) {
            Pair<String, Integer> element = arrSeparate.get(h).get(index);
            if (element.getKey().equals(key)) {
                arrSeparate.get(h).set(index, new Pair<>(key, value));
                found = true;
                break;
            }
        }

        if (!found) {
            arrSeparate.get(h).add(new Pair<>(key, value));
        }
    }

    public Integer lookSeparate(String key) {
        int comparison = 0;
        int h = getHash(key);
        for (Pair<String, Integer> element : arrSeparate.get(h)) {
            comparison++;
            if (element.getKey().equals(key)) {
                System.out.println("Comparison Required: " + comparison);
                return element.getValue();
            }
        }
        return null;
    }

    // Linear Probing
    public void setLinear(String key, int value) {
        int index = getHash(key);
        while (arrLinear[index] != null) {
            if (arrLinear[index].getKey().equals(key)) {
                arrLinear[index] = new Pair<>(key, value);
                return;
            }
            index = (index + 1) % MAX;
        }
        arrLinear[index] = new Pair<>(key, value);
    }

    public Integer lookLinear(String key) {
        int comparison = 0;
        int index = getHash(key);
        while (arrLinear[index] != null) {
            comparison++;
            if (arrLinear[index].getKey().equals(key)) {
                System.out.println("Comparison required: " + comparison);
                return arrLinear[index].getValue();
            }
            index = (index + 1) % MAX;
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneDirectory d1 = new PhoneDirectory();

        while (true) {
            System.out.println("\n1.Add Contact using Separate Chaining");
            System.out.println("\n2.Add Contact using Linear Probing");
            System.out.println("\n3.Display Contact using Separate Chaining");
            System.out.println("\n4.Display Contact using Linear Probing");
            System.out.println("\n5.Exit");
            System.out.print("Enter the choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nInserting using Separate Chaining");
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    int number = scanner.nextInt();
                    d1.setSeparate(name, number);
                    System.out.println("\n" + d1.arrSeparate);
                    System.out.println("\n--------------------------------------------------------------------------\n");
                    break;
                case 2:
                    System.out.println("\nInserting using Linear Probing");
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    number = scanner.nextInt();
                    d1.setLinear(name, number);
                    System.out.println("\n" + d1.arrLinear);
                    System.out.println("\n--------------------------------------------------------------------------\n");
                    break;
                case 3:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.println("Phone number of " + name + " is " + d1.lookSeparate(name));
                    System.out.println("\n--------------------------------------------------------------------------\n");
                    break;
                case 4:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.println("Phone number of " + name + " is " + d1.lookLinear(name));
                    System.out.println("\n--------------------------------------------------------------------------\n");
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("!! Wrong choice entered !!");
            }
        }
    }

    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}


